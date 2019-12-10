package com.yy.app.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.app.logger.LogUtil;
import com.yy.app.R;

/**
 *
 */
public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {

        LogUtil.e("MyService - onBind");

        return mBinder; // 先返回一个实现了IBinder接口的对象
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtil.e("MyService - onCreate");


        // 前台服务
        Intent intent = new Intent(this, ServiceActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);
        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle("This is content title")
                .setContentText("This is content text")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setContentIntent(pi)
                .build();
        startForeground(1, notification);


    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        LogUtil.e("MyService - onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        LogUtil.e("MyService - onDestroy");
        ServiceActivity.isStart = false;
        super.onDestroy();
    }

    /**
     * binder实例
     */
    private DownloaderBinder mBinder = new DownloaderBinder();

    class DownloaderBinder extends Binder {
        public void startDownload() {
            LogUtil.e("startDownload: ");
        }

        public int getProgress() {
            LogUtil.e("getProgress: ");
            return 0;
        }
    }
}
