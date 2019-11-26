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

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.yy.app.R;

import java.util.ArrayList;
import java.util.List;

public class ShortCutMainActivity extends ListActivity implements OnClickListener {
    static final String TAG = "ShortcutSample";

    private static final String ID_ADD_WEBSITE = "add_website";

    private static final String ACTION_ADD_WEBSITE =
            "com.example.android.shortcutsample.ADD_WEBSITE";

    private MyAdapter mAdapter;

    private ShortcutHelper mHelper;

    @Override
    public Intent getIntent() {
        return super.getIntent();
    }

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.shortcut_main);

        mHelper = new ShortcutHelper(this);

        mHelper.maybeRestoreAllDynamicShortcuts();

        mHelper.refreshShortcuts(/*force=*/ false);

        if (ACTION_ADD_WEBSITE.equals(getIntent().getAction())) {
            // Invoked via the manifest shortcut.
            addWebSite();
        }

        mAdapter = new MyAdapter(this.getApplicationContext());
        setListAdapter(mAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshList();
    }

    /**
     * Handle the add button.
     */
    public void onAddPressed(View v) {
        addWebSite();
    }

    @SuppressLint("NewApi")
    private void addWebSite() {
        Log.i(TAG, "addWebSite");

        // This is important.  This allows the launcher to build a prediction model.
        mHelper.reportShortcutUsed(ID_ADD_WEBSITE);

        final EditText editUri = new EditText(this);

        editUri.setHint("http://www.baidu.com/");
        editUri.setText("http://www.baidu.com/");
        editUri.setInputType(EditorInfo.TYPE_TEXT_VARIATION_URI);

        new AlertDialog.Builder(this)
                .setTitle("Add new website")
                .setMessage("Type URL of a website")
                .setView(editUri)
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final String url = editUri.getText().toString().trim();
                        if (url.length() > 0) {
                            addUriAsync(url);
                        }
                    }
                })
                .show();
    }

    private void addUriAsync(final String uri) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.CUPCAKE) {
            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... params) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        mHelper.addWebSiteShortcut(uri);
                    }
                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    refreshList();
                }
            }.execute();
        }
    }

    private void refreshList() {
        Log.e(TAG, "refreshList: " + Build.VERSION.SDK_INT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
            mAdapter.setShortcuts(mHelper.getShortcuts());
        }
    }

    @Override
    public void onClick(View v) {
        final ShortcutInfo shortcut;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N_MR1) {
            shortcut = (ShortcutInfo) ((View) v.getParent()).getTag();
            switch (v.getId()) {
                case R.id.disable:
                    if (shortcut.isEnabled()) {
                        mHelper.disableShortcut(shortcut);
                    } else {
                        mHelper.enableShortcut(shortcut);
                    }
                    refreshList();
                    break;
                case R.id.remove:
                    mHelper.removeShortcut(shortcut);
                    refreshList();
                    break;
            }
        }
    }

    private static final List<ShortcutInfo> EMPTY_LIST = new ArrayList<>();

    private String getType(ShortcutInfo shortcut) {
        final StringBuilder sb = new StringBuilder();
        String sep = "";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
            if (shortcut.isDynamic()) {
                sb.append(sep);
                sb.append("Dynamic");
                sep = ", ";
            }
            if (shortcut.isPinned()) {
                sb.append(sep);
                sb.append("Pinned");
                sep = ", ";
            }
            if (!shortcut.isEnabled()) {
                sb.append(sep);
                sb.append("Disabled");
                sep = ", ";
            }
        }

        return sb.toString();
    }

    private class MyAdapter extends BaseAdapter {
        private final Context mContext;
        private LayoutInflater mInflater;
        private List<ShortcutInfo> mList = EMPTY_LIST;

        public MyAdapter(Context context) {
            mContext = context;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                mInflater = mContext.getSystemService(LayoutInflater.class);
            }
        }

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public Object getItem(int position) {
            return mList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public boolean areAllItemsEnabled() {
            return true;
        }

        @Override
        public boolean isEnabled(int position) {
            return true;
        }

        public void setShortcuts(List<ShortcutInfo> list) {
            mList = list;
            notifyDataSetChanged();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final View view;
            if (convertView != null) {
                view = convertView;
            } else {
                view = mInflater.inflate(R.layout.shortcut_list_item, null);
            }

            bindView(view, position, mList.get(position));

            return view;
        }

        public void bindView(View view, int position, ShortcutInfo shortcut) {
            view.setTag(shortcut);

            final TextView line1 = (TextView) view.findViewById(R.id.line1);
            final TextView line2 = (TextView) view.findViewById(R.id.line2);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
                line1.setText(shortcut.getLongLabel());
            }

            line2.setText(getType(shortcut));

            final Button remove = (Button) view.findViewById(R.id.remove);
            final Button disable = (Button) view.findViewById(R.id.disable);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
                disable.setText(
                        shortcut.isEnabled() ? R.string.disable_shortcut : R.string.enable_shortcut);
            }

            remove.setOnClickListener(ShortCutMainActivity.this);   // 点击事件
            disable.setOnClickListener(ShortCutMainActivity.this);  // 点击事件
        }
    }
}
