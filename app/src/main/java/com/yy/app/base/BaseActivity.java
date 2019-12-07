package com.yy.app.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;


/**
 * Activity的公共基类
 */
@SuppressWarnings("JavaDoc")
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("zhang", "onCreate: " + this.getClass().getSimpleName());

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
}
