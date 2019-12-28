package com.yy.app.handler;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.yy.app.R;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * HandlerThread 的使用
 */
public class HandlerThreadActivity extends AppCompatActivity {


    private ImageView imageView;

    private HandlerThread handlerThread;

    /**
     * 图片地址数组
     */
    private String url[] = {
            "图片地址1", "图片地址2", "图片地址3", "图片地址4", "图片地址5"
    };

    private Handler uiHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            //加载图片
            imageView.setImageBitmap((Bitmap) msg.obj);


        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.image2);

        handlerThread = new HandlerThread("downloadImage");
        //必须开启HandlerThread线程
        handlerThread.start();

        //获取子Handler
        Handler childHandler = new Handler(handlerThread.getLooper(), new ChildCallback());

        for (int i = 0; i < url.length; i++) {
            childHandler.sendEmptyMessageDelayed(i, 1000 * i);
        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handlerThread.quit();//注意释放资源
    }

    private Bitmap downloadUrlBitmap(String urlString) {
        HttpURLConnection urlConnection = null;
        BufferedInputStream in = null;
        Bitmap bitmap = null;
        try {
            final URL url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedInputStream(urlConnection.getInputStream(), 8 * 1024);
            bitmap = BitmapFactory.decodeStream(in);
        } catch (final IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            try {
                if (in != null) {
                    in.close();
                }
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }
        return bitmap;
    }


    class ChildCallback implements Handler.Callback {
        @Override
        public boolean handleMessage(Message message) {
            //在子线程中进行网络请求
            Bitmap bitmap = downloadUrlBitmap(url[message.what]);
            Message message1 = new Message();
            message1.obj = bitmap;
            //通知主线程去更新UI
            uiHandler.sendMessage(message1);
            return false;
        }
    }


}


