/*
 * The MIT License (MIT)
 *
 */
package com.yy.app.components.listview.swipemenulistview;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.app.logger.LogUtil;
import com.yy.app.R;
import com.yy.app.base.BaseActivity;
import com.yy.app.view.swipelistview.SwipeMenu;
import com.yy.app.view.swipelistview.SwipeMenuCreator;
import com.yy.app.view.swipelistview.SwipeMenuItem;
import com.yy.app.view.swipelistview.SwipeMenuListView;

import java.util.List;

/**
 * SwipeMenuListView
 * 1, getPackageManager().getInstalledApplications(0); // 获取手机上已安装应用信息 -> ApplicationInfo对象
 * 2, mListView.setMenuCreator(creator); // 添加滑动出现的items
 * 3,setOnSwipeListener() ; // 监听滑动开始和滑动结束
 * 4,mListView.setOnMenuItemClickListener(); // 滑动条目的监听事件
 * 5,
 */
public class SimpleActivity extends BaseActivity {

    private List<ApplicationInfo> mAppList;
    private AppAdapter mAdapter;
    private SwipeMenuListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        mAppList = getPackageManager().getInstalledApplications(0);

        mListView = (SwipeMenuListView) findViewById(R.id.listView);

        mAdapter = new AppAdapter(this);
        mListView.setAdapter(mAdapter);

        // step 1. create a MenuCreator
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "open" item
                SwipeMenuItem openItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                openItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,
                        0xCE)));
                // set item width
                openItem.setWidth(dp2px(90));
                // set item title
                openItem.setTitle("Open");
                // set item title fontsize
                openItem.setTitleSize(18);
                // set item title font color
                openItem.setTitleColor(Color.WHITE);
                // add to menu
                menu.addMenuItem(openItem);

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(dp2px(90));
                // set a icon
                deleteItem.setIcon(R.drawable.ic_delete);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };
        // set creator
        mListView.setMenuCreator(creator);

        // step 2. listener item click event
        //int position, SwipeMenu menu, int index
        mListView.setOnMenuItemClickListener((position, menu, index) -> {
            ApplicationInfo item = mAppList.get(position);
            switch (index) {
                case 0:
                    // open
                    open(item);
                    break;
                case 1:
                    // delete
                    //					delete(item);
                    mAppList.remove(position);
                    mAdapter.notifyDataSetChanged();
                    break;
            }
            return false;
        });


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showToast(parent.getAdapter().getItem(position));
            }
        });
        // 监听滑动开始和滑动结束
        mListView.setOnSwipeListener(new SwipeMenuListView.OnSwipeListener() {

            @Override
            public void onSwipeStart(int position) {
                // swipe start
                LogUtil.e("swipe start");
            }

            @Override
            public void onSwipeEnd(int position) {
                // swipe end
                LogUtil.e("onSwipeEnd");
            }
        });

        // set MenuStateChangeListener
        mListView.setOnMenuStateChangeListener(new SwipeMenuListView.OnMenuStateChangeListener() {
            @Override
            public void onMenuOpen(int position) {
                LogUtil.e("onMenuOpen:" + position);   // position 为下标从0开始的条目
            }

            @Override
            public void onMenuClose(int position) {
                LogUtil.e("onMenuClose:" + position);
            }
        });

        // other setting
        //		listView.setCloseInterpolator(new BounceInterpolator());

        // test item long click
        mListView.setOnItemLongClickListener((parent, view, position, id) -> {
            Toast.makeText(getApplicationContext(), position + " long click", Toast
                    .LENGTH_SHORT).show();
            return false;
        });

    }

    @Override
    public void initContentViewXml() {

    }

    private void delete(ApplicationInfo item) {
        // delete app
        try {
            Intent intent = new Intent(Intent.ACTION_DELETE);
            intent.setData(Uri.fromParts("package", item.packageName, null));
            startActivity(intent);
        } catch (Exception e) {
            LogUtil.e(e);
        }
    }


    /**
     * 根据 ApplicationInfo 信息启动对应的 app
     *
     * @param item 要打开的应用信息对象
     */
    private void open(ApplicationInfo item) {
        // open app
        Intent resolveIntent = new Intent(Intent.ACTION_MAIN, null);
        resolveIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        resolveIntent.setPackage(item.packageName);
        List<ResolveInfo> resolveInfoList = getPackageManager()
                .queryIntentActivities(resolveIntent, 0);
        if (resolveInfoList != null && resolveInfoList.size() > 0) {
            ResolveInfo resolveInfo = resolveInfoList.get(0);
            String activityPackageName = resolveInfo.activityInfo.packageName;
            String className = resolveInfo.activityInfo.name;

            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            ComponentName componentName = new ComponentName(
                    activityPackageName, className);

            intent.setComponent(componentName);
            startActivity(intent);
        }
    }


    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_swipmenulistview, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_left) {
            mListView.setSwipeDirection(SwipeMenuListView.DIRECTION_LEFT);
            return true;
        }
        if (id == R.id.action_right) {
            mListView.setSwipeDirection(SwipeMenuListView.DIRECTION_RIGHT);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public List<ApplicationInfo> getAppList() {
        return mAppList;
    }
}
