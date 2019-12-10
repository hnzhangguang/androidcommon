package com.yy.app.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

/** IntentService: 1, 工作完成后自动关闭 */
public class MyIntentService extends IntentService {
  public MyIntentService(String param) {
    super(param);
  }

  @Override
  protected void onHandleIntent(@Nullable Intent intent) {}
}
