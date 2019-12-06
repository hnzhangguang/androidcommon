package com.yy.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.tandong.swichlayout.SwitchLayout;
import com.yy.app.R;

/**
 * 动画效果库 SwitchLayout 的使用
 */
public class SwitchLayoutActivity extends AppCompatActivity {

    Button btn_next;
    Button btn_3DRotate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch_layout);


        btn_next = (Button) this.findViewById(R.id.btn_next);
        btn_3DRotate = (Button) this.findViewById(R.id.btn_3DRotate);

        // 1, 进入此activity的时候先执行一下按钮的动画效果
        SwitchLayout.get3DRotateFromLeft(btn_next, false, null);


        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SwitchLayoutActivity.this, SwitchLayoutTestActivity.class);
                startActivity(intent);
            }
        });

        //2,  3DRotate
        btn_3DRotate.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View arg0) {
                SwitchLayout.get3DRotateFromLeft(btn_3DRotate, false, null);
            }
        });


    }
}
