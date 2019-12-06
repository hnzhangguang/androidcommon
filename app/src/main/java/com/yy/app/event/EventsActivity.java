package com.yy.app.event;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yy.app.R;
import com.yy.app.base.BaseActivity;
import com.yy.app.base.Logger;

import de.greenrobot.event.EventBus;


/**
 * 1, eventBus:
 * 1.1 EventBus.getDefault().register(this);
 * 1.2 EventBus.getDefault().unregister(this);
 */
public class EventsActivity extends BaseActivity {


    Button btn_eventBus;
    TextView tv_content;

    @Override
    public void initContentViewXml() {

        setContentView(R.layout.activity_events);
        EventBus.getDefault().register(this);

    }

    @Override
    public void initView() {
        super.initView();
        btn_eventBus = findViewById(R.id.btn_eventBus);
        tv_content = findViewById(R.id.tv_content);

    }

    @Override
    public void initListener() {
        super.initListener();
        btn_eventBus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                MyEvent my = new MyEvent();
                my.setType("1");
                my.setContent("1内容");
                EventBus.getDefault().post(my);


            }
        });
    }

    public void onEvent(MyEvent event) {
        Logger.e("onEvent->" + event);
        if (event.getType().equals("0")) {
            tv_content.setText(event.getContent());
        }
    }

    public void onEventMainThread(MyEvent event) {
        Logger.e("onEventMainThread->" + event);

        if (event.getType().equals("1")) {
            tv_content.setText(event.getContent());
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


}
