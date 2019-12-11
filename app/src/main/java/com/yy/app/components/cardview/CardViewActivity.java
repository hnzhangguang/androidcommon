package com.yy.app.components.cardview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.yy.app.R;


/**
 * 1, Cardview继承自FrameLayout，所以子控件布局规则和FrameLayout的一样
 * 2, card_view:cardCornerRadius="4dp"
 */
public class CardViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_view);
    }
}
