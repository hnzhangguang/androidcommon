package com.yy.app.dialog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.yy.app.R;

public class DialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog2);


        new MyDialogFragment().show(getSupportFragmentManager(), "dialog_fragment");


    }
}
