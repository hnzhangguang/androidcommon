package com.yy.app.components.bubble;

import android.view.View;
import android.widget.Button;

import com.yy.app.R;
import com.yy.app.base.BaseActivity;


/**
 * 气泡
 */
public class BubbleActivity extends BaseActivity {


    Button btn_bubble;


    @Override
    public void initContentViewXml() {
        setContentView(R.layout.activity_bubble);
    }


    @Override
    public void initView() {
        super.initView();
        btn_bubble = findViewById(R.id.btn_bubble);

    }

    @Override
    public void initListener() {
        super.initListener();
        btn_bubble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void initData() {
        super.initData();
    }


}
