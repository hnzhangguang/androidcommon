package net.designpattern.adapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import net.sxkeji.shixindesignpattern.R;


/**
 * 适配器模式有分三种情况: 类适配器模式, 对象适配器模式, 接口适配器模式
 */
public class AdapterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adapter);
    }
}
