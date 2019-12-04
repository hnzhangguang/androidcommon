package com.yy.app.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.view.View;

import com.yy.app.R;
import com.yy.app.base.BaseActivity;
import com.yy.app.base.Logger;


/**
 * 服务activity
 */
public class ServiceActivity extends BaseActivity implements View.OnClickListener, ServiceConnection {


    Intent intent = null;

    @Override
    public void initContentViewXml() {
        setContentView(R.layout.activity_service);
    }

    @Override
    public void initView() {
        super.initView();


        findViewById(R.id.btnStartService).setOnClickListener(this);
        findViewById(R.id.btnStopService).setOnClickListener(this);
        findViewById(R.id.btnBindService).setOnClickListener(this);
        findViewById(R.id.btnUnBindService).setOnClickListener(this);


    }

    @Override
    public void initListener() {
        super.initListener();
    }

    @Override
    public void onClick(View v) {

        intent = new Intent(ServiceActivity.this, MyService.class);

        switch (v.getId()) {

            case R.id.btnStartService:
                startService(intent);
                break;
            case R.id.btnStopService:
                stopService(intent);
                break;

            case R.id.btnBindService:

                bindService(intent, this, Context.BIND_AUTO_CREATE);
                break;


            case R.id.btnUnBindService:

                unbindService(this);
                break;
            default:

                break;
        }

    }


    // 绑定成功的时候调用
    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        Logger.e("ServiceActivity - onServiceConnected");
        System.out.println("ServiceActivity - onServiceConnected");
    }


    // 解绑成功的时候调用
    @Override
    public void onServiceDisconnected(ComponentName name) {
        Logger.e("ServiceActivity - onServiceDisconnected");
    }
}
