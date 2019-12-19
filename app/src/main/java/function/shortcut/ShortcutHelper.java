/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package function.shortcut;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.PersistableBundle;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.yy.app.R;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.function.BooleanSupplier;

@RequiresApi(api = Build.VERSION_CODES.N)
public class ShortcutHelper {
    private static final String TAG = ShortCutMainActivity.TAG;

    private static final String EXTRA_LAST_REFRESH =
            "com.example.android.shortcutsample.EXTRA_LAST_REFRESH";

    private static final long REFRESH_INTERVAL_MS = 60 * 60 * 1000;

    private final Context mContext;

    private ShortcutManager mShortcutManager = null;


    @RequiresApi(api = Build.VERSION_CODES.N_MR1)
    public ShortcutHelper(Context context) {
        mContext = context;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mShortcutManager = mContext.getSystemService(ShortcutManager.class);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N_MR1)
    public void maybeRestoreAllDynamicShortcuts() {
        if (mShortcutManager.getDynamicShortcuts().size() == 0) {
            // NOTE: If this application is always supposed to have dynamic shortcuts, then publish
            // them here.
            // Note when an application is "restored" on a new device, all dynamic shortcuts
            // will *not* be restored but the pinned shortcuts *will*.
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N_MR1)
    public void reportShortcutUsed(String id) {
        mShortcutManager.reportShortcutUsed(id);
    }

    /**
     * Use this when interacting with ShortcutManager to show consistent error messages.
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void callShortcutManager(BooleanSupplier r) {
        try {
            if (!r.getAsBoolean()) {
                Utils.showToast(mContext, "Call to ShortcutManager is rate-limited");
            }
        } catch (Exception e) {
            Log.e(TAG, "Caught Exception", e);
            Utils.showToast(mContext, "Error while calling ShortcutManager: " + e.toString());
        }
    }

    /**
     * Return all mutable shortcuts from this app self.
     */
    @RequiresApi(api = Build.VERSION_CODES.N_MR1)
    public List<ShortcutInfo> getShortcuts() {
        // Load mutable dynamic shortcuts and pinned shortcuts and put them into a single list
        // removing duplicates.

        final List<ShortcutInfo> ret = new ArrayList<>();
        final HashSet<String> seenKeys = new HashSet<>();

        // Check existing shortcuts shortcuts
        for (ShortcutInfo shortcut : mShortcutManager.getDynamicShortcuts()) {
            if (!shortcut.isImmutable()) {
                ret.add(shortcut);
                seenKeys.add(shortcut.getId());
            }
        }
        for (ShortcutInfo shortcut : mShortcutManager.getPinnedShortcuts()) {
            if (!shortcut.isImmutable() && !seenKeys.contains(shortcut.getId())) {
                ret.add(shortcut);
                seenKeys.add(shortcut.getId());
            }
        }
        return ret;
    }

    /**
     * Called when the activity starts.  Looks for shortcuts that have been pushed and refreshes
     * them (but the refresh part isn't implemented yet...).
     */
    public void refreshShortcuts(final boolean force) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                Log.i(TAG, "refreshingShortcuts...");

                final long now = System.currentTimeMillis();
                final long staleThreshold = force ? now : now - REFRESH_INTERVAL_MS;

                // Check all existing dynamic and pinned shortcut, and if their last refresh
                // time is older than a certain threshold, update them.

                final List<ShortcutInfo> updateList = new ArrayList<>();

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
                    for (ShortcutInfo shortcut : getShortcuts()) {
                        if (shortcut.isImmutable()) {
                            continue;
                        }

                        final PersistableBundle extras = shortcut.getExtras();
                        if (extras != null && extras.getLong(EXTRA_LAST_REFRESH) >= staleThreshold) {
                            // Shortcut still fresh.
                            continue;
                        }
                        Log.i(TAG, "Refreshing shortcut: " + shortcut.getId());

                        final ShortcutInfo.Builder b = new ShortcutInfo.Builder(
                                mContext, shortcut.getId());

                        setSiteInformation(b, shortcut.getIntent().getData());
                        setExtras(b);

                        updateList.add(b.build());
                    }
                }
                // Call update.
                if (updateList.size() > 0) {
                    callShortcutManager(new BooleanSupplier() {
                        @RequiresApi(api = Build.VERSION_CODES.N_MR1)
                        @Override
                        public boolean getAsBoolean() {

                            return mShortcutManager.updateShortcuts(updateList);

                        }
                    });
                }

                return null;
            }
        }.execute();
    }

    private ShortcutInfo createShortcutForUrl(String urlAsString) {
        Log.i(TAG, "createShortcutForUrl: " + urlAsString);

        ShortcutInfo.Builder b = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
            b = new ShortcutInfo.Builder(mContext, urlAsString);
        }

        final Uri uri = Uri.parse(urlAsString);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
            b.setIntent(new Intent(Intent.ACTION_VIEW, uri));
        }

        setSiteInformation(b, uri);
        setExtras(b);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
            return b.build();
        }

        return null;
    }

    private ShortcutInfo.Builder setSiteInformation(ShortcutInfo.Builder b, Uri uri) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
            b.setShortLabel(uri.getHost());
            b.setLongLabel(uri.toString());
            Bitmap bmp = fetchFavicon(uri);
            if (bmp != null) {
                b.setIcon(Icon.createWithBitmap(bmp));
            } else {
                b.setIcon(Icon.createWithResource(mContext, R.drawable.link));
            }
        }


        return b;
    }

    private ShortcutInfo.Builder setExtras(ShortcutInfo.Builder b) {
        final PersistableBundle extras = new PersistableBundle();
        extras.putLong(EXTRA_LAST_REFRESH, System.currentTimeMillis());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
            b.setExtras(extras);
        }
        return b;
    }

    private String normalizeUrl(String urlAsString) {
        if (urlAsString.startsWith("http://") || urlAsString.startsWith("https://")) {
            return urlAsString;
        } else {
            return "http://" + urlAsString;
        }
    }

    public void addWebSiteShortcut(String urlAsString) {
        final String uriFinal = urlAsString;
        callShortcutManager(
                new BooleanSupplier() {
                    @Override
                    public boolean getAsBoolean() {
                        final ShortcutInfo shortcut = createShortcutForUrl(normalizeUrl(uriFinal));
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
                            return mShortcutManager.addDynamicShortcuts(Arrays.asList(shortcut));
                        }
                        return false;
                    }
                }
        );
    }

    public void removeShortcut(ShortcutInfo shortcut) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
            mShortcutManager.removeDynamicShortcuts(Arrays.asList(shortcut.getId()));
        }
    }

    public void disableShortcut(ShortcutInfo shortcut) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
            mShortcutManager.disableShortcuts(Arrays.asList(shortcut.getId()));
        }
    }

    public void enableShortcut(ShortcutInfo shortcut) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
            mShortcutManager.enableShortcuts(Arrays.asList(shortcut.getId()));
        }
    }

    private Bitmap fetchFavicon(Uri uri) {
        final Uri iconUri = uri.buildUpon().path("favicon.ico").build();
        Log.i(TAG, "Fetching favicon from: " + iconUri);

        InputStream is = null;
        BufferedInputStream bis = null;
        try {
            URLConnection conn = new URL(iconUri.toString()).openConnection();
            conn.connect();
            is = conn.getInputStream();
            bis = new BufferedInputStream(is, 8192);
            return BitmapFactory.decodeStream(bis);
        } catch (IOException e) {
            Log.w(TAG, "Failed to fetch favicon from " + iconUri, e);
            return null;
        }
    }
}
