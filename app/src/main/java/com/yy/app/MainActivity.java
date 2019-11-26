package com.yy.app;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.yy.app.base.BaseActivity;
import com.yy.app.base.BaseTextWatcher;
import com.yy.app.components.ComponentMainActivity;
import com.yy.app.components.materialdesign.MaterialDesignActivity;

import function.shortcut.ShortCutMainActivity;


/**
 * 主要功能:
 */
public class MainActivity extends BaseActivity {

    // log tag
    private final static String TAG = "MainActivity";
    // intent extra
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    TextView textView; // 界面文本显示
    Button toSecondButton;   // 按钮: 跳转到第二个activity
    Button toMainActivityByIntent;   // 按钮: 跳转到第二个activity
    Button toShortCutButton;
    Button btn_components;
    Button btn_material;


    @Override
    public void initContentViewXml() {
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initView() {
        textView = findViewById(R.id.textView);
        toSecondButton = findViewById(R.id.toSecondButton);
        toMainActivityByIntent = findViewById(R.id.toMainActivityByIntent);
        toShortCutButton = findViewById(R.id.toShortCutButton);
        btn_components = findViewById(R.id.btn_components);
        btn_material = findViewById(R.id.btn_material);
    }

    @Override
    public void initListener() {
        btn_material.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MaterialDesignActivity.class);
                startActivity(intent);
            }
        });
        btn_components.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ComponentMainActivity.class);
                startActivity(intent);
            }
        });
        toShortCutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ShortCutMainActivity.class);
                startActivity(intent);
            }
        });
        toSecondButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 界面跳转方法
                sendMessage(view);
            }
        });
        toMainActivityByIntent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessageByIntent(); // action 的使用
            }
        });
        // 文本改变事件
        textView.addTextChangedListener(new BaseTextWatcher() {
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                super.onTextChanged(charSequence, i, i1, i2);
            }
        });
    }

    @Override
    public void initData() {

    }


    /**
     * 界面跳转逻辑
     *
     * @param view
     */
    public void sendMessage(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message); // 可以传递普通的参数(字符串,数字,,,,)
        intent.putExtras(new Bundle()); // 可以传递bundle对象
        intent.putExtras(new Intent()); // 可以传递intent对象
//        startActivity(intent);
        startActivityForResult(intent, 333);

    }


    /**
     * 界面跳转逻辑 (action 方式)
     */
    public void sendMessageByIntent() {
        // Create the text message with a string
        Intent sendIntent = new Intent();
        sendIntent.setAction("android.intent.action.define");
        sendIntent.setType("text/plain");
        //intent.putExtra("media_id", "a1b2c3");
        sendIntent.putExtra(Intent.EXTRA_TEXT, "我是使用Intent action 参数调起的activity");
        sendIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);   // 设置不同的launchMode
        // Start the activity
        startActivity(sendIntent);
    }


    /**
     * 其他方式启动activity 设置launcMode
     *
     * @param view
     */
    public void openOtherActivity(View view) {
        Intent intent = new Intent();
        ComponentName componentName = new ComponentName("cn.test.server", "cn.test.server.MainActivity");
        intent.setComponent(componentName);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


    // 参照字段的回调方法
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult: ");

        if (requestCode == 333) {

        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: ");
    }


}
