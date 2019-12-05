package com.yy.app.components.animator;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yy.app.R;
import com.yy.app.base.BaseActivity;

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

                ObjectAnimator objectAnimator1 = ObjectAnimator.ofArgb(mTextView, "backgroundColor", Color.WHITE, Color.GREEN);
                ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(mTextView, "scaleX", 0.1f, 1.2f);
                ObjectAnimator objectAnimator3 = ObjectAnimator.ofFloat(mTextView, "scaleY", 0.5f, 1.0f);
                ObjectAnimator objectAnimator4 = ObjectAnimator.ofFloat(mTextView, "translationY", 0, 250);

                // together
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playTogether(objectAnimator1, objectAnimator2, objectAnimator3, objectAnimator4);
                animatorSet.setDuration(3000);
                animatorSet.start();


                // seq...
                AnimatorSet animatorSet2 = new AnimatorSet();
                animatorSet2.playSequentially(objectAnimator1, objectAnimator2, objectAnimator3, objectAnimator4);
                animatorSet2.setDuration(1000);
                animatorSet2.start();


                // before
                AnimatorSet animatorSet3 = new AnimatorSet();
                animatorSet3.play(objectAnimator1).before(objectAnimator2).after(objectAnimator3);
                ;
                animatorSet3.setDuration(3000);
                animatorSet3.start();


            }
        });
    }

    @Override
    public void initData() {


    }
}
