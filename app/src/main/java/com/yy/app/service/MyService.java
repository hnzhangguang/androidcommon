package com.yy.app.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.yy.app.base.Logger;


/**
 *
 */
public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {

        Logger.e("MyService - onBind");


        return new Binder();   // 先返回一个实现了IBinder接口的对象
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.e("MyService - onCreate");
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Logger.e("MyService - onStartCommand");

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Logger.e("MyService - onDestroy");
        super.onDestroy();
    }
}
