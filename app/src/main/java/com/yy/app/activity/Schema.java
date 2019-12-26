package com.yy.app.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;

public class Schema extends AppCompatActivity {


    public void test() {

        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("uumobile://yongche/123123123")));


        //Scheme定义Activity
        // 1）在Androidmanifest.xml中定义scheme
        //        <!-- scheme协议 -->
        //        <activity
        //        Android:name=".UI.translate.NativeAppActivity"
        //        Android:label="@string/app_name">
        //
        //            <!-- 要想在别的App上能成功调起App，必须添加intent过滤器 -->
        //            <intent-filter>
        //
        //                <!-- 协议部分，随便设置 -->
        //                <data Android:scheme="uumobile" />
        //                <!-- 下面这几行也必须得设置 -->
        //                <category Android:name="Android.intent.category.DEFAULT" />
        //                <category Android:name="Android.intent.category.BROWSABLE" />
        //
        //                <action Android:name="Android.intent.action.VIEW" />
        //            </intent-filter>
        //        </activity>

        // 2 实现NativeAppActivity
        //        Uri uri = getIntent().getData();
        //        if (uri != null)
        //        {
        //            List<String> pathSegments = uri.getPathSegments();
        //            String uriQuery = uri.getQuery();
        //            Intent intent；
        //            if (pathSegments != null && pathSegments.size() > 0) {
        //                // 解析SCHEME
        //                if (someif) {
        //                    dosomething();
        //                }
        //                else {
        //                    // 若解析不到SCHEME，则关闭NativeAppActivity；
        //                    finish();
        //                }
        //            } else {
        //                finish();
        //            }
        //        } else {
        //            finish();
        //        }

        //可以通过Intent对象获取调用的scheme的host等信息
        //
        //this.getIntent().getScheme();//获得Scheme名称
        //this.getIntent().getDataString();//获得Uri全部路径


    }


}
