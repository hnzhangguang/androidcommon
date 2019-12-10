package com.yy.app.components.dialog;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.yy.app.R;
import com.yy.app.base.BaseActivity;

public class DialogActivity extends BaseActivity implements View.OnClickListener {


    @Override
    public void initContentViewXml() {
        setContentView(R.layout.activity_dialog);
    }


    @Override
    public void initView() {
        super.initView();
        findViewById(R.id.button3).setOnClickListener(this);
    }

    @Override
    public void initListener() {
        super.initListener();
    }

    @Override
    public void initData() {
        super.initData();
    }

    @Override
    public void onClick(View v) {


        if (v.getId() == R.id.button3) {
            ProgressDialog progressDialog = new ProgressDialog(DialogActivity.this);
            progressDialog.setTitle("This is ProgressDialog");
            progressDialog.setMessage("Loading...");
            progressDialog.setCancelable(true);
            progressDialog.show();
        }


    }
}
