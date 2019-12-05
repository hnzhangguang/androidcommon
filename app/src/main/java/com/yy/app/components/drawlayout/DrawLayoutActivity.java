package com.yy.app.components.drawlayout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.SlidingDrawer;
import android.widget.TextView;
import android.widget.Toast;

import com.yy.app.R;
import com.yy.app.base.BaseActivity;

import org.w3c.dom.Text;


/**
 * 抽屉布局的使用:
 * 1,  android:layout_gravity="start" 是作用在DrawLayout 的左侧根布局上的
 * 2, fragment -> fragment.setArguments(bundle);
 * 3, main_DrawLayout.closeDrawer(left_listView);
 */
public class DrawLayoutActivity extends BaseActivity {


    DrawerLayout main_DrawLayout;
    ListView left_listView;
    FrameLayout mainLayout;
    ActionBarDrawerToggle toggle;
    String title = "我是title";

    @Override
    public void initContentViewXml() {
        setContentView(R.layout.activity_draw_layout);

    }

    @Override
    public void initView() {
        super.initView();
        main_DrawLayout = findViewById(R.id.main_DrawLayout);
        mainLayout = findViewById(R.id.mainLayout);
        left_listView = findViewById(R.id.left_listView);

    }

    @Override
    public void initListener() {
        super.initListener();
        left_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (view instanceof TextView) {
                    TextView tv = (TextView) view;
//                    showToast(tv.getText().toString());

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
            }
        };


        //4,  已经废弃掉了
        main_DrawLayout.setDrawerListener(toggle);

    }


    @Override
    public void initData() {
        super.initData();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.planets_array));//新建并配置ArrayAapeter
        left_listView.setAdapter(adapter);

    }
}


