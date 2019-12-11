package com.yy.app.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.yy.app.R;
import com.yy.app.activity.fragment.FragmentActivity;
import com.yy.app.base.BaseActivity;


/**
 * activity主界面
 */
public class ActivityMainActivity extends BaseActivity {

    Button btn_switchLayout;

    @Override
    public void initContentViewXml() {
        setContentView(R.layout.activity_main2);

    }

    @Override
    public void initView() {
        super.initView();
        btn_switchLayout = findViewById(R.id.btn_switchLayout);
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
