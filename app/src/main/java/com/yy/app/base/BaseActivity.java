package com.yy.app.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.yy.app.MainActivity;


/**
 * Activity的公共基类
 */
@SuppressWarnings("JavaDoc")
public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("zhang", "onCreate: " + this.getClass().getSimpleName());
        ActivityCollector.addActivity(this);

        // 设置此用户界面布局
        initContentViewXml();
        // 初始化界面元素
        initView();
        // 给界面元素添加监听
        initListener();
        // 初始化界面数据
        initData();

    }

    /**
     * 设置Activity布局xml
     */
    public abstract void initContentViewXml();


    /**
     * 初始化界面元素
     */
    public void initView() {
    }


    /**
     * 给界面元素添加监听
     */
    public void initListener() {
    }


    /**
     * 初始化界面数据
     */
    public void initData() {
    }


    /**
     * 还原数据
     *
     * @param savedInstanceState
     */
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    /**
     * 保存数据
     *
     * @param outState
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }


    /**
     * launcher mode = 'singleTask' 的时候起作用, activity先到后台 -> 再到前台的时候调用
     *
     * @param intent
     */
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }


    /**
     * 物料返回键的时候
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    public void showToast(Object obj) {
        Toast toast = Toast.makeText(getApplicationContext(), obj == null ? "" :
                obj.toString(), Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }


    @Override
    protected void onDestroy() {
        ActivityCollector.removeActivity(this);
        super.onDestroy();
    }


    /**
     * 强制离线广播
     */
    class ForceOfflineReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(final Context context, Intent intent) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Warning");
            builder.setMessage("You are forced to be offline. Please try to login again.");
            builder.setCancelable(false);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ActivityCollector.finishAll(); // 销毁所有活动
                    Intent intent = new Intent(context, MainActivity.class);
                    context.startActivity(intent); // 重新启动LoginActivity
                }
            });
            builder.show();
        }

    }

    @Override
    public void onClick(View v) {

    }
}
