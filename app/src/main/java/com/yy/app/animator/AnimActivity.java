package com.yy.app.animator;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

import com.yy.app.R;
import com.yy.app.base.BaseActivity;


/**
 * 视图动画:
 * 1, 透明度动画
 * 2, 缩放
 * 3, 位移
 * 4, 旋转
 * 5, set
 * 6, 监听
 * 7, 自定义动画(animation)
 */
public class AnimActivity extends BaseActivity {

    Button btn_alpha;
    Button btn_rotate;
    Button btn_translate;
    Button btn_scale;
    Button btn_animation_set;
    Button btn_customer;

    @Override
    public void initContentViewXml() {
        setContentView(R.layout.activity_anim);
    }

    @Override
    public void initView() {

        btn_alpha = findViewById(R.id.btn_alpha);
        btn_rotate = findViewById(R.id.btn_rotate);
        btn_translate = findViewById(R.id.btn_translate);
        btn_scale = findViewById(R.id.btn_scale);
        btn_animation_set = findViewById(R.id.btn_animation_set);
        btn_customer = findViewById(R.id.btn_customer);

        btn_customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomAnim ca = new CustomAnim();
                ca.setDuration(1000);
                v.setAnimation(ca);
            }
        });

        btn_animation_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                AnimationSet as = new AnimationSet(true);
//        as.setDuration(1000);
//
//        AlphaAnimation aa = new AlphaAnimation(0, 1);
//        aa.setDuration(1000);
//        as.addAnimation(aa);
//
//        TranslateAnimation ta = new TranslateAnimation(200, 0, 200, 0);
//        ta.setDuration(1000);
//        as.addAnimation(ta);


                Animation a = AnimationUtils.loadAnimation(AnimActivity.this, R.anim.animation_set);

                // 动画监听事件
                a.setAnimationListener(new Animation.AnimationListener() {

                    @Override
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        Toast.makeText(AnimActivity.this, "Animation end", Toast.LENGTH_SHORT).show();
                    }
                });

                v.startAnimation(a);


            }
        });
        btn_scale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//        sa = new ScaleAnimation(0, 1, 0, 1,100,50);
//        sa = new ScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
//        sa.setDuration(1000);
//                v.setAnimation(sa);

                v.startAnimation(AnimationUtils.loadAnimation(AnimActivity.this, R.anim.scale));


            }
        });

        btn_alpha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 透明动画 代码
                //					AlphaAnimation aa = new AlphaAnimation(0, 1);
                //					aa.setDuration(1000);
                //					v.startAnimation(aa);
                // 透明动画xml
                v.startAnimation(AnimationUtils.loadAnimation(AnimActivity.this, R.anim.alpha));
            }
        });

        btn_rotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 旋转动画 代码
//        ra = new RotateAnimation(0, 360, 100, 50);
//        ra = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
//        ra.setDuration(1000);

                // 旋转动画 xml
                v.startAnimation(AnimationUtils.loadAnimation(AnimActivity.this, R.anim.rotate));

            }
        });


        btn_translate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //     TranslateAnimation   ta = new TranslateAnimation(0, 200, 0, 200);
                //        ta.setDuration(1000);
                //		  v.startAnimation(ta);
                v.startAnimation(AnimationUtils.loadAnimation(AnimActivity.this, R.anim.translate));
            }
        });


    }


    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }
}
