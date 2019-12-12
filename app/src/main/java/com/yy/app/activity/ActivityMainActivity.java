package com.yy.app.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.yy.app.R;
import com.yy.app.activity.fragment.FragmentActivity;
import com.yy.app.activity.fragmenttabhost.FragmentTabHostActivity;
import com.yy.app.activity.tabhost.MainTabhostActivity;
import com.yy.app.activity.tabhost.TabLayoutViewPagerActivity;
import com.yy.app.base.BaseActivity;


/**
 * activity主界面
 */
public class ActivityMainActivity extends BaseActivity {

    Button btn_switchLayout;
    Button btn_tabhost;
    Button btn_fragment_tabhost;
    Button btn_TabLayoutViewPager;

    @Override
    public void initContentViewXml() {
        setContentView(R.layout.activity_main2);

    }

    @Override
    public void initView() {
        super.initView();
        btn_tabhost = findViewById(R.id.btn_tabhost);
        btn_TabLayoutViewPager = findViewById(R.id.btn_TabLayoutViewPager);
        btn_switchLayout = findViewById(R.id.btn_switchLayout);
        btn_fragment_tabhost = findViewById(R.id.btn_fragment_tabhost);

        btn_TabLayoutViewPager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityMainActivity.this, TabLayoutViewPagerActivity.class);
                startActivity(intent);
            }
        });
        btn_tabhost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityMainActivity.this, MainTabhostActivity.class);
                startActivity(intent);
            }
        });
        btn_fragment_tabhost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityMainActivity.this, FragmentTabHostActivity.class);
                startActivity(intent);
            }
        });
        btn_switchLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityMainActivity.this, SwitchLayoutActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btn_fragment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityMainActivity.this, FragmentActivity.class);
                startActivity(intent);
            }
        });
    }
}
