package com.yy.app.handler;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import com.yy.app.R;


/**
 * 子线程与子线程之间的通信
 */
public class HandlerActivity extends AppCompatActivity {

    //
    private Handler threadHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    class ThreadA extends Thread {

        @Override
        public void run() {
            super.run();
            Looper.prepare();

            threadHandler = new Handler() {

                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    //收到来自于ThreadB的消息，注意这里运行在ThreadA线程中

                    //......
                }
            };

            Looper.loop();

        }
    }

    class ThreadB extends Thread {

        @Override
        public void run() {
            super.run();

            Looper looper = Looper.myLooper();

            Message message = new Message();
            message.obj = "ThreadB发送消息到ThreadA";
            //......
            threadHandler.sendMessage(message);


        }
    }

}