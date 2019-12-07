package com.yy.app.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.app.logger.LogUtil;


public class MyReceiver extends BroadcastReceiver {

    public static final String ACTION = "com.yy.app.broadcastreceiver.intent.myreceiver.action";

    @Override
    public void onReceive(Context context, Intent intent) {

        LogUtil.e("MyReceiver - onReceive :" + intent.getStringExtra("key"));  // 接受传递的值
    }
}
