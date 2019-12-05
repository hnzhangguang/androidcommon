package com.yy.app.broadcastreceiver;

import android.content.Intent;
import android.content.IntentFilter;
import android.view.View;

import com.yy.app.R;
import com.yy.app.base.BaseActivity;


/**
 * 1, 广播类型 : 1, 静态注册, 2, 动态注册
 * 2, action 匹配
 * 3, 优先级 (有序广播而言)
 */
public class ReceiverActivity extends BaseActivity implements View.OnClickListener {


    @Override
    public void initContentViewXml() {
        setContentView(R.layout.activity_receiver);
    }

    @Override
    public void initView() {
        super.initView();
        findViewById(R.id.btn_sendMessage).setOnClickListener(this);
        findViewById(R.id.btn_regReceiver).setOnClickListener(this);
        findViewById(R.id.btn_regUnReceiver).setOnClickListener(this);


    }


    @Override
    public void initListener() {
        super.initListener();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_sendMessage:


                // 第一种 方式 - 静态注册
//                Intent intent = new Intent(ReceiverActivity.this, MyReceiver.class);

                // 第二种方式  -- 动态注册
                // 使用动态注册的方式发送广播
                Intent intent = new Intent(MyReceiver.ACTION);
                intent.putExtra("key", "data...");
                intent.setPackage(ReceiverActivity.this.getPackageName());  // 定向广播
//                sendBroadcast(intent);
                sendOrderedBroadcast(intent, null);


                break;
            case R.id.btn_regReceiver:  // 动态注册广播
                if (myReceiver2 == null) {
                    myReceiver2 = new MyReceiver2();
                }
                registerReceiver(myReceiver2, new IntentFilter(MyReceiver.ACTION));

                break;
            case R.id.btn_regUnReceiver:  // 取消动态注册广播

                if (myReceiver2 != null) {
                    unregisterReceiver(myReceiver2);
                }
                myReceiver2 = null;  // 一定要清理

                break;
            default:

                break;
        }

    }


    private MyReceiver2 myReceiver2 = null;

}
