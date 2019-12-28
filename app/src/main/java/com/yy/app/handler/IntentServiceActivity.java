package com.yy.app.handler;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.yy.app.R;

public class IntentServiceActivity extends AppCompatActivity implements MyIntentService.UpdateUI {
    /**
     * 图片地址集合
     */
    private String url[] = {
            "图片地址1",
            "图片地址2",
            "图片地址3",
            "图片地址4",
            "图片地址5",
            "图片地址6",
            "图片地址7"
    };

    private static ImageView imageView;
    private static final Handler mUIHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            imageView.setImageBitmap((Bitmap) msg.obj);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //        setContentView(R.layout.activity_intent_service);
        imageView = (ImageView) findViewById(R.id.image);

        Intent intent = new Intent(this, MyIntentService.class);
        for (int i = 0; i < 7; i++) {//循环启动任务
            intent.putExtra(MyIntentService.DOWNLOAD_URL, url[i]);
            intent.putExtra(MyIntentService.INDEX_FLAG, i);
            startService(intent);
        }
        MyIntentService.setUpdateUI(this);
    }

    //必须通过Handler去更新，该方法为异步方法，不可更新UI
    @Override
    public void updateUI(Message message) {
        mUIHandler.sendMessageDelayed(message, message.what * 1000);
    }
}