package com.yy.app.components.animator;

import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.yy.app.R;
import com.yy.app.base.BaseActivity;

public class AnimActivity extends BaseActivity {

    AnimationDrawable rocketAnimation;

    @Override
    public void initContentViewXml() {
        setContentView(R.layout.activity_anim);
    }

    @Override
    public void initView() {


    }

    public void test() {

        // 加载drawable里面的动画文件
        ImageView rocketImage = (ImageView) findViewById(R.id.rocket_image);
        rocketImage.setBackgroundResource(R.drawable.animation1);
        rocketAnimation = (AnimationDrawable) rocketImage.getBackground();

        rocketImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rocketAnimation.start();
            }
        });


        // 设置view 动画
        rocketImage.animate()
                .alpha(1f)
                .setDuration(2)
                .setListener(null);


    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }
}
