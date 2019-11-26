package com.yy.app;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.yy.app.base.BaseActivity;

public class AnimActivity extends BaseActivity {


    @Override
    public void initContentViewXml() {
        setContentView(R.layout.activity_anim);
    }

    @Override
    public void initView() {


        ImageView spaceshipImage = (ImageView) findViewById(R.id.spaceshipImage);
        Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(this, R.anim.anim_set);
        spaceshipImage.startAnimation(hyperspaceJumpAnimation);


    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }
}
