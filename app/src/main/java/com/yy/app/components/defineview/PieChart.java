package com.yy.app.components.defineview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.yy.app.R;


/**
 * 自定义饼状图view
 * <p>
 * 类型	定义
 * 自定义组合控件	多个控件组合成为一个新的控件，方便多处复用
 * 继承系统View控件	继承自TextView等系统控件，在系统控件的基础功能上进行扩展
 * 继承View	不复用系统控件逻辑，继承View进行功能定义
 * 继承系统ViewGroup	继承自LinearLayout等系统控件，在系统控件的基础功能上进行扩展
 * 继承ViewViewGroup	不复用系统控件逻辑，继承ViewGroup进行功能定义
 * <p>
 * View的绘制基本由measure()、layout()、draw()这个三个函数完成
 * 函数	        作用	                相关方法
 * measure()	测量View的宽高	    measure(),setMeasuredDimension(),onMeasure()
 * layout()	    计算当前View以及子View的位置	layout(),onLayout(),setFrame()
 * draw()	    视图的绘制工作	    draw(),onDraw()
 * <p>
 * 自定义属性
 * Android自定义属性可分为以下几步:
 * 1, 自定义一个View
 * 2, 编写values/attrs.xml，在其中编写styleable和item等标签元素
 * 3, 在布局文件中View使用自定义的属性（注意namespace）
 * 4, 在View的构造方法中通过TypedArray获取
 */
class PieChart extends View {

    boolean mShowText = false;
    int textColor;
    Paint textPaint;
    Paint piePaint;
    Paint shadowPaint;
    float textHeight;

    public PieChart(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.PieChart,
                0, 0);

        try {
            mShowText = a.getBoolean(R.styleable.PieChart_showText, false); // 文本内容
            textColor = a.getColor(R.styleable.PieChart_textColor, 0);   // 文字颜色
        } finally {
            a.recycle();
        }

        // 开始真正的绘制
        init();

    }

    private void init() {
        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(textColor);
        if (textHeight == 0) {
            textHeight = textPaint.getTextSize();
        } else {
            textPaint.setTextSize(textHeight);
        }

        piePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        piePaint.setStyle(Paint.Style.FILL);
        piePaint.setTextSize(textHeight);

        shadowPaint = new Paint(0);
        shadowPaint.setColor(0xff101010);
        shadowPaint.setMaskFilter(new BlurMaskFilter(8, BlurMaskFilter.Blur.NORMAL));
    }

    public boolean isShowText() {
        return mShowText;
    }

    public void setShowText(boolean showText) {
        mShowText = showText;
        invalidate();
        requestLayout();
    }


    /**
     * 计算布局
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        // Try for a width based on our minimum
        int minw = getPaddingLeft() + getPaddingRight() + getSuggestedMinimumWidth();
        int w = resolveSizeAndState(minw, widthMeasureSpec, 1);

        // Whatever the width ends up being, ask for a height that would let the pie
        // get as big as it can
        int h = resolveSizeAndState(MeasureSpec.getSize(w) - 100, heightMeasureSpec, 0);

        setMeasuredDimension(w, h);

    }

    /**
     * 放置具体布局
     *
     * @param changed
     * @param left
     * @param top
     * @param right
     * @param bottom
     */
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }


    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


    }


    /**
     * 使得view 可以交互
     *
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }


    /**
     * 监听
     */
    class MyListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }
    }


}