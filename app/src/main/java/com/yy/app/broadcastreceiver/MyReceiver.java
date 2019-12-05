package com.yy.app.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.yy.app.base.Logger;

public class MyReceiver extends BroadcastReceiver {

    public static final String ACTION = "com.yy.app.broadcastreceiver.intent.myreceiver.action";

    @Override
    public void onReceive(Context context, Intent intent) {

        Logger.e("MyReceiver - onReceive :" + intent.getStringExtra("key"));  // 接受传递的值
    }
}
