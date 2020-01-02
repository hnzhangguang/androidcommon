package com.yy.app.animator;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.yy.app.R;

public class ValueAnimatorActivity extends AppCompatActivity {

    TextView textView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_value_animator);


        textView4 = findViewById(R.id.textView4);

        ValueAnimator animation = ValueAnimator.ofFloat(0f, 100f);
        animation.setDuration(1000);
        animation.start();


        // TypeEvaluator
        animation = ValueAnimator.ofObject(new MyTypeEvaluator(), 0f, 100f);
        animation.setDuration(1000);
        animation.start();


        //AnimatorUpdateListener
        animation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator updatedAnimation) {
                // You can use the animated value in a property that uses the
                // same type as the animation. In this case, you can use the
                // float value in the translationX property.
                float animatedValue = (float) updatedAnimation.getAnimatedValue();
                textView4.setTranslationX(animatedValue);
            }
        });


        //  ObjectAnimator
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(textView4, "translationX", 100f);
        objectAnimator.setDuration(1000);
        objectAnimator.start();

        // setTarget
        AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(this,
                R.animator.property_animator);
        set.setTarget(textView4);
        set.start();


        ValueAnimator fadeAnim = ObjectAnimator.ofFloat(textView4, "alpha", 1f, 0f);
        fadeAnim.setDuration(250);
        fadeAnim.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animation) {
                // ...
            }
        });

        ObjectAnimator animX = ObjectAnimator.ofFloat(textView4, "x", 50f);
        ObjectAnimator animY = ObjectAnimator.ofFloat(textView4, "y", 100f);
        AnimatorSet animSetXY = new AnimatorSet();
        animSetXY.playTogether(animX, animY);
        animSetXY.start();


        // 加载本地xml 动画文件
        ValueAnimator xmlAnimator = (ValueAnimator) AnimatorInflater.loadAnimator(this,
                R.animator.animator1);
        xmlAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator updatedAnimation) {
                float animatedValue = (float) updatedAnimation.getAnimatedValue();
                textView4.setTranslationX(animatedValue);
            }
        });
        xmlAnimator.start();


        AnimatorSet bouncer = new AnimatorSet();
        //        bouncer.play(animX).before(animY);
        //        bouncer.play(squashAnim1).with(squashAnim2);
        //        bouncer.play(squashAnim1).with(stretchAnim1);
        //        bouncer.play(squashAnim1).with(stretchAnim2);
        //        bouncer.play(bounceBackAnim).after(stretchAnim2);
        //        ValueAnimator fadeAnim = ObjectAnimator.ofFloat(newBall, "alpha", 1f, 0f);
        //        fadeAnim.setDuration(250);
        //        AnimatorSet animatorSet = new AnimatorSet();
        //        animatorSet.play(bouncer).before(fadeAnim);
        //        animatorSet.start();


    }

    class MyTypeEvaluator implements TypeEvaluator {

        @Override
        public Object evaluate(float fraction, Object startValue, Object endValue) {
            return null;
        }
    }

    public class FloatEvaluator implements TypeEvaluator {

        public Object evaluate(float fraction, Object startValue, Object endValue) {
            float startFloat = ((Number) startValue).floatValue();
            return startFloat + fraction * (((Number) endValue).floatValue() - startFloat);
        }
    }


}
