package com.yy.app.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.yy.app.base.Logger;

public class MyReceiver2 extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Logger.e("MyReceiver2 - onReceive");

        abortBroadcast();  // 有序广播的时候, 优先级高的可以废弃掉优先级底的广播
    }
}
