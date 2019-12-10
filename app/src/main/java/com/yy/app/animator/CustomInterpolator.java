package com.yy.app.animator;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.BaseInterpolator;

/**
 * 自定义插补器:
 * 三角函数
 */
public class CustomInterpolator extends BaseInterpolator {


    float mCycles = 1;

    public CustomInterpolator() {

    }

    public CustomInterpolator(float cycles) {
        mCycles = cycles;
    }


    public CustomInterpolator(Context context, AttributeSet attrs) {

    }

    @Override
    public float getInterpolation(float input) {
        //        return (float) (Math.sqrt(input) * input);
        return (float) (Math.sin(2 * mCycles * Math.PI * input)); //sin(2 * 1* π * x)
    }
}
