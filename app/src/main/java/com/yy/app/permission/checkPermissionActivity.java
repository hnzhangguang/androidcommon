package com.yy.app.permission;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.Telephony;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.yy.app.R;


/**
 * Android 权限问题
 */
public class checkPermissionActivity extends AppCompatActivity {

    private final static int requestPermissionCode = 221;
    private final static int requestSmsCode = 2212;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_permission);
    }


    /**
     * 检查权限
     */
    public void checkPermission() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_CALENDAR)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_CONTACTS},
                    requestPermissionCode);


        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case requestPermissionCode: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {
                }
                return;
            }
        }
    }


    public void test() {


        //Permissions used only in default handlers
        //仅在默认处理程序中使用的权限
        Intent setSmsAppIntent =
                new Intent(Telephony.Sms.Intents.ACTION_CHANGE_DEFAULT);
        setSmsAppIntent.putExtra(Telephony.Sms.Intents.EXTRA_PACKAGE_NAME,
                getPackageName());
        startActivityForResult(setSmsAppIntent, requestSmsCode);


        //Define a Custom App Permission
        // 自定义权限


    }


}
