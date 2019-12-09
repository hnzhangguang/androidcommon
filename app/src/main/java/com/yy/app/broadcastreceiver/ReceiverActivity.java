package com.yy.app.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.Toast;

import com.app.logger.LogUtil;
import com.yy.app.R;
import com.yy.app.base.BaseActivity;


/**
 * 1, 广播类型 : 1, 静态注册, 2, 动态注册
 * 2, action 匹配
 * 3, 优先级 (有序广播而言)
 * 4, 本地广播
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
        findViewById(R.id.btn_LocalReceiver).setOnClickListener(this);


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

            case R.id.btn_LocalReceiver:

                // 初始化本地广播
                initLocalReceiver();

                break;
            default:

                break;
        }

    }

    private LocalBroadcastManager lm;
    private TestReceiver testReceiver;

    /**
     * 初始化本地广播
     */
    private void initLocalReceiver() {
        //获取实例
        lm = LocalBroadcastManager.getInstance(this);
        IntentFilter intentFilter = new IntentFilter("com.android.Test");
        testReceiver = new TestReceiver();
        //绑定
        lm.registerReceiver(testReceiver, intentFilter);
    }

    //本地广播
    private class TestReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Toast.makeText(ReceiverActivity.this, "action:" + action, Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onDestroy() {
        try {
            //解绑
            lm.unregisterReceiver(testReceiver);
        } catch (Exception e) {
            LogUtil.e(e);
        }


        super.onDestroy();
    }

    private MyReceiver2 myReceiver2 = null;

}
