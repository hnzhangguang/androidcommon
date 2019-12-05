package com.yy.app.components.drawlayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.yy.app.R;
import com.yy.app.base.BaseActivity;
import com.yy.app.base.Logger;


/**
 * 抽屉布局的使用:
 * 1,  android:layout_gravity="start" 是作用在DrawLayout 的左侧根布局上的
 * 2, fragment -> fragment.setArguments(bundle);
 * 3, main_DrawLayout.closeDrawer(left_listView);
 * 4, 设置图标, menu 等
 */
public class DrawLayoutActivity extends BaseActivity {


    DrawerLayout main_DrawLayout;
    ListView left_listView;
    FrameLayout mainLayout;
    ActionBarDrawerToggle toggle;
    Toolbar mToolbar;

    @Override
    public void initContentViewXml() {
        setContentView(R.layout.activity_draw_layout);

    }

    @Override
    public void initView() {
        super.initView();
        mToolbar = findViewById(R.id.toolbar_drawLayout);
        setSupportActionBar(mToolbar);//利用Toolbar代替ActionBar -> 移动要是v7包下面的

        main_DrawLayout = findViewById(R.id.main_DrawLayout);
        mainLayout = findViewById(R.id.mainLayout);
        left_listView = findViewById(R.id.left_listView);

    }

    @Override
    public void initListener() {
        super.initListener();

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DrawLayoutActivity.this, "点击导航栏", Toast.LENGTH_SHORT).show();
            }
        });

        //填充menu
        mToolbar.inflateMenu(R.menu.drawlayout_menu);
        // 你发现设置了这一对之后，action menu 依然没有显示出来，因为你还没有重写onCreateOptionsMenu，让action menu显示出来。


        //设置点击事件
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_settings:
                        Toast.makeText(DrawLayoutActivity.this, "action_settings", Toast.LENGTH_SHORT).show();

                        break;
                    case R.id.action_share:
                        Toast.makeText(DrawLayoutActivity.this, "action_share", Toast.LENGTH_SHORT).show();

                        break;
                    case R.id.action_search:
                        Toast.makeText(DrawLayoutActivity.this, "action_search", Toast.LENGTH_SHORT).show();

                        break;
                    default:
                        break;
                }
                return false;


            }
        });


        left_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (view instanceof TextView) {
                    TextView tv = (TextView) view;

                    //1,  实例化fragment
                    BlankFragment fragment = new BlankFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("key", tv.getText().toString());
                    fragment.setArguments(bundle);

                    //2,  获取manager
                    FragmentManager manager = getSupportFragmentManager();
                    manager.beginTransaction().replace(R.id.mainLayout, fragment).commit();   // 替换一定要提交

                    //3,  关闭 drawlayout
                    main_DrawLayout.closeDrawer(left_listView);
                }


            }
        });


        toggle = new ActionBarDrawerToggle(this, main_DrawLayout, null, R.string.open_drawlayout, R.string.close_drawlayout) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                showToast("请选择");
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                showToast("你已经操作结束~~~");
                // 是不是 开着的
                boolean isDrawerOpen = main_DrawLayout.isDrawerOpen(left_listView);
                Logger.e(isDrawerOpen);
            }
        };


        //4,  已经废弃掉了
        main_DrawLayout.setDrawerListener(toggle);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.drawlayout_menu, menu);
        return true;
    }


    /**
     * 隐式启动页面
     */
    public void openNetAddress() {

        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri uri = Uri.parse("http://www.baidu.com");
        intent.setData(uri);
        startActivity(intent);

    }


    @Override
    public void initData() {
        super.initData();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.planets_array));//新建并配置ArrayAapeter
        left_listView.setAdapter(adapter);

    }
}


