package com.yy.app.animator;

import android.view.animation.Animation;
import android.view.animation.Transformation;

import com.app.logger.LogUtil;


/**
 * 自定义动画 animation
 * 1, 构造函数
 * 2, applyTransformation(float interpolatedTime, Transformation t);
 */
public class CustomAnim extends Animation {


    @Override
    public void initialize(int width, int height, int parentWidth,
                           int parentHeight) {
        //		System.out.println("init");
        super.initialize(width, height, parentWidth, parentHeight);
    }


    /**
     * 自定义动画必须实现的方法
     *
     * @param interpolatedTime
     * @param t
     */
    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        LogUtil.e(interpolatedTime);
        //        System.out.println(interpolatedTime);    // 0~1
        //		t.setAlpha(interpolatedTime);   // 透明度动画的实现
        //		t.getMatrix().setTranslate(200*interpolatedTime, 200*interpolatedTime);  // 位移动画的实现
        t.getMatrix().setTranslate((float) (Math.sin(interpolatedTime * 10) * 50), 10);  // 摇头动画的实现
        super.applyTransformation(interpolatedTime, t);
    }

}
