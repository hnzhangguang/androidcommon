package com.yy.app.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.app.logger.LogUtil;

/** */
public class MyService extends Service {
  public MyService() {}

  @Override
  public IBinder onBind(Intent intent) {

    LogUtil.e("MyService - onBind");

    return mBinder; // 先返回一个实现了IBinder接口的对象
  }

  @Override
  public void onCreate() {
    super.onCreate();
    LogUtil.e("MyService - onCreate");
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

  /** binder实例 */
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
