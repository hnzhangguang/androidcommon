package com.yy.app.components;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.yy.app.R;
import com.yy.app.base.BaseActivity;


/**
 * 其他零碎知识点
 */
public class OtherActivity extends BaseActivity {


    @Override
    public void initContentViewXml() {
        setContentView(R.layout.activity_other);
    }

    @Override
    public void initView() {
        super.initView();
        Button toastBtn = findViewById(R.id.toast);
        toastBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toast();
            }
        });
    }

    public void toast() {

        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast,
                (ViewGroup) findViewById(R.id.custom_toast_container));

        Button btn_toast = (Button) layout.findViewById(R.id.btn_toast);
        btn_toast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("你点击了我~, 你个坏人~");
            }
        });


        TextView text = (TextView) layout.findViewById(R.id.tv_toast);
        text.setText("这就是我,自定义toast~");

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();


    }

}
