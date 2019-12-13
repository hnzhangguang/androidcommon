package com.yy.app;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.yy.app.activity.ActivityMainActivity;
import com.yy.app.animator.AnimActivity;
import com.yy.app.asynctask.AsyncTaskActivity;
import com.yy.app.base.BaseActivity;
import com.yy.app.broadcastreceiver.ReceiverActivity;
import com.yy.app.components.ComponentMainActivity;
import com.yy.app.components.drawlayout.DrawLayoutActivity;
import com.yy.app.components.materialdesign.MaterialDesignActivity;
import com.yy.app.data.GsonJSONActivity;
import com.yy.app.drawable.DrawableActivity;
import com.yy.app.event.EventsActivity;
import com.yy.app.material.MaterialActivity;
import com.yy.app.network.NetworkActivity;
import com.yy.app.service.ServiceActivity;

import java.util.List;


/**
 * 主要功能:
 * 总结Android总入口
 */
public class MainActivity extends BaseActivity {

    // log tag
    private final static String TAG = "MainActivity";
    // intent extra
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    Button toMainActivityByIntent;   // 按钮: 跳转到第二个activity
    Button btn_components;
    Button btn_material;
    Button btn_serviceactivity;
    Button btn_DrawLayout;
    Button btn_receiver;
    Button btn_animation;
    Button btn_asynctask;
    Button btn_NetworkActivity;
    Button btn_DrawableActivity;
    Button btn_EventsActivity;
    Button btn_activity_activity;
    Button btn_Databaseactivity;
    Button btn_MaterialActivity;


    @Override
    public void initContentViewXml() {
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initView() {
        toMainActivityByIntent = findViewById(R.id.toMainActivityByIntent);
        btn_components = findViewById(R.id.btn_components);
        btn_material = findViewById(R.id.btn_material);
        btn_serviceactivity = findViewById(R.id.btn_serviceactivity);
        btn_receiver = findViewById(R.id.btn_receiver);
        btn_DrawLayout = findViewById(R.id.btn_DrawLayout);
        btn_animation = findViewById(R.id.btn_animation);
        btn_asynctask = findViewById(R.id.btn_asynctask);
        btn_NetworkActivity = findViewById(R.id.btn_NetworkActivity);
        btn_DrawableActivity = findViewById(R.id.btn_DrawableActivity);
        btn_EventsActivity = findViewById(R.id.btn_EventsActivity);
        btn_activity_activity = findViewById(R.id.btn_activity_activity);
        btn_Databaseactivity = findViewById(R.id.btn_Databaseactivity);
        btn_MaterialActivity = findViewById(R.id.btn_MaterialActivity);
    }

    @Override
    public void initListener() {
        btn_MaterialActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MaterialActivity.class);
                startActivity(intent);
            }
        });
        btn_Databaseactivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GsonJSONActivity.class);
                startActivity(intent);
            }
        });
        btn_activity_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ActivityMainActivity.class);
                startActivity(intent);
            }
        });
        btn_EventsActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EventsActivity.class);
                startActivity(intent);
            }
        });
        btn_DrawableActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DrawableActivity.class);
                startActivity(intent);
            }
        });
        btn_NetworkActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NetworkActivity.class);
                startActivity(intent);
            }
        });
        btn_asynctask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AsyncTaskActivity.class);
                startActivity(intent);
            }
        });
        btn_animation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AnimActivity.class);
                startActivity(intent);
            }
        });
        btn_material.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MaterialDesignActivity.class);
                startActivity(intent);
            }
        });
        btn_DrawLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DrawLayoutActivity.class);
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


        toMainActivityByIntent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessageByIntent(); // action 的使用
            }
        });
        btn_serviceactivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ServiceActivity.class);
                startActivity(intent);
            }
        });
        btn_receiver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ReceiverActivity.class);
                startActivity(intent);
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
        //        Intent intent = new Intent(this, SecondActivity.class);
        //        EditText editText = (EditText) findViewById(R.id.editText);
        //        String message = editText.getText().toString();
        //        intent.putExtra(EXTRA_MESSAGE, message); // 可以传递普通的参数(字符串,数字,,,,)
        //        intent.putExtras(new Bundle()); // 可以传递bundle对象
        //        intent.putExtras(new Intent()); // 可以传递intent对象
        ////        startActivity(intent);
        //        startActivityForResult(intent, 333);

    }


    /**
     * 界面跳转逻辑 (action 方式)
     */
    public void sendMessageByIntent() {

        String action = "android.intent.action.define";
        boolean actionSupport = isActionSupport(this, action);

        if (actionSupport) {
            // Create the text message with a string
            Intent sendIntent = new Intent();
            sendIntent.setAction("android.intent.action.define");
            //            sendIntent.setType("text/plain");
            //intent.putExtra("media_id", "a1b2c3");
            //            sendIntent.putExtra(Intent.EXTRA_TEXT, "我是使用Intent action 参数调起的activity");
            sendIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);   // 设置不同的launchMode
            // Start the activity
            startActivity(sendIntent);
        }

    }


    /**
     * action: -> 是否存在一个可以启动的activity
     *
     * @param context
     * @param action
     * @return
     */
    private boolean isActionSupport(Context context, String action) {
        final PackageManager packageManager = context.getPackageManager();
        final Intent intent = new Intent(action);
        List<ResolveInfo> resolveInfo =
                packageManager.queryIntentActivities(intent,
                        PackageManager.MATCH_DEFAULT_ONLY);
        if (resolveInfo.size() > 0) {
            return true;
        }
        return false;
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
