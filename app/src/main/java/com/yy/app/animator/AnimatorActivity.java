package com.yy.app.animator;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yy.app.R;
import com.yy.app.base.BaseActivity;


/**
 * ObjectAnimator 对象动画  -> 是直接对对象的属性值进行改变操作，从而实现动画效果
 * ValueAnimator 值动画  -> 原理是通过不断控制值的变化，然后手动赋给对象的属性，从而实现动画效果
 * <p>
 * PropertyValueHolder 用于同时执行多个动画
 * TypeEvaluator 估值器
 * AnimatorSet 动画集合
 * Interpolator 差值器
 * <p>
 * // 可以直接开始
 * //                objectAnimator1.start(); // 开始动画
 * //                objectAnimator1.setDuration(1000);  // 设置动画运行的时长
 * //                objectAnimator1.setStartDelay(1000); // 设置动画延迟播放时间
 * //                objectAnimator1.setRepeatCount(2); // 重复次数,infinite时,动画无限重复
 * //                objectAnimator1.setRepeatMode(ValueAnimator.RESTART);// 设置重复播放动画模式(ValueAnimator
 * // .RESTART(默认):正序重放,ValueAnimator.REVERSE:倒序回放)
 */
public class AnimatorActivity extends BaseActivity {


    Button btn_animator;
    TextView mTextView;


    @Override
    public void initContentViewXml() {
        setContentView(R.layout.activity_animator);
    }


    @Override
    public void initView() {

        mTextView = findViewById(R.id.mTextView);
        btn_animator = findViewById(R.id.btn_animator);


    }

    @Override
    public void initListener() {

        btn_animator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // ObjectAnimator 有指定作用对象
                ObjectAnimator objectAnimator1 = ObjectAnimator.ofArgb(mTextView, "backgroundColor",
                        Color.WHITE, Color.GREEN);
                ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(mTextView, "scaleX", 0.1f, 1.2f);
                ObjectAnimator objectAnimator3 = ObjectAnimator.ofFloat(mTextView, "scaleY", 0.5f, 1.0f);
                ObjectAnimator objectAnimator4 = ObjectAnimator.ofFloat(mTextView, "translationY", 0,
                        250);


                //
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playTogether(objectAnimator1, objectAnimator2, objectAnimator3,
                        objectAnimator4);
                animatorSet.setDuration(3000);
                animatorSet.start();

                // 设定作用对象
                //                animatorSet.setTarget(mTextView);


                // seq...
                AnimatorSet animatorSet2 = new AnimatorSet();
                animatorSet2.playSequentially(objectAnimator1, objectAnimator2, objectAnimator3,
                        objectAnimator4);
                animatorSet2.setDuration(1000);
                animatorSet2.start();


                // before
                AnimatorSet animatorSet3 = new AnimatorSet();
                animatorSet3.play(objectAnimator1).before(objectAnimator2).after(objectAnimator3);
                animatorSet3.setDuration(3000);
                animatorSet3.start();


            }
        });
    }

    @Override
    public void initData() {


    }


    /**
     * 属性动画一起运动
     */
    public void togetherObjectAnimatorPlay() {


        // 1, 方式一 (底层也是使用的 PropertyValuesHolder 实现的)
        ObjectAnimator objectAnimator1 = ObjectAnimator.ofArgb(mTextView, "backgroundColor",
                Color.WHITE, Color.GREEN);
        ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(mTextView, "scaleX", 0.1f, 1.2f);
        ObjectAnimator objectAnimator3 = ObjectAnimator.ofFloat(mTextView, "scaleY", 0.5f, 1.0f);
        ObjectAnimator objectAnimator4 = ObjectAnimator.ofFloat(mTextView, "translationY", 0,
                250);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(objectAnimator1, objectAnimator2, objectAnimator3,
                objectAnimator4);
        animatorSet.setDuration(3000);
        animatorSet.start();


        // 2, 方式2
        PropertyValuesHolder holder1 = PropertyValuesHolder.ofFloat("translationX", 200);
        PropertyValuesHolder holder2 = PropertyValuesHolder.ofFloat("translationY", 200);
        PropertyValuesHolder holder3 = PropertyValuesHolder.ofFloat("rotation", 90);
        PropertyValuesHolder holder4 = PropertyValuesHolder.ofFloat("scaleX", 1.5f);
        PropertyValuesHolder holder5 = PropertyValuesHolder.ofFloat("alpha", 0.2f);


        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(mTextView, holder1, holder2,
                holder3, holder4, holder5);
        animator.setDuration(500);
        animator.setTarget(mTextView); // 设置作用对象
        animator.start();


    }


    public void ValueAnimator() {


        // 第一种 ValueAnimator
        ValueAnimator animInt = ValueAnimator.ofInt(0, 3);
        ValueAnimator animFoat = ValueAnimator.ofFloat(0, 3);
        //设置动画运行的时长
        animInt.setDuration(500);
        //设置动画延迟播放时间
        animInt.setStartDelay(500);
        //设置动画重复播放次数=重放次数+1
        //动画播放次数=infinite时,动画无限重复
        animInt.setRepeatCount(0);
        animInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float fraction = animation.getAnimatedFraction();
                float value = (float) animation.getAnimatedValue();
                Log.i("app", "属性值：" + "fraction:" + fraction + ",value:" + value);
                //在这里设置具体的动画属性值
                mTextView.setTranslationX(value);
            }
        });
        //开启动画
        animInt.start();


        // 第二种 ValueAnimator
        ValueAnimator valueAnimator = ValueAnimator.ofObject(new TypeEvaluator() {
            @Override
            public Object evaluate(float fraction, Object startValue, Object endValue) {
                return null;
            }
        }, animFoat);


        // set
        //        AnimatorSet set = new AnimatorSet();
        //        set.playTogether(animFoat,animInt);


    }


}
