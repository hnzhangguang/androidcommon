package com.yy.app.view;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.AppCompatTextView;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;

/**
 * 自定义view, 重写textview组件
 * 目的是让textview把显示内容中用#好括住和@到空格的部分变为蓝色
 */
public class WeiboTextView extends AppCompatTextView {

    public WeiboTextView(Context context) {
        super(context);
    }

    public WeiboTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WeiboTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setTextHightLight() {

        String text = (String) this.getText();
        SpannableStringBuilder spannable = new SpannableStringBuilder(text);
        // 用于可变字符
        //储存话题的“#”位置和用户的"@"与空格位置
        //查找#和@和空格的位置
        for (int i = 0; i < text.length(); i++) {
            int start = text.indexOf("#", i);
            int end = text.indexOf("#", start + 1);
            if (start != -1 && end != -1) {
                spannable.setSpan(new ForegroundColorSpan(Color.BLUE), start, end + 1,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                i = end;
            }
        }
        for (int i = 0; i < text.length(); i++) {
            int start = text.indexOf("@", i);
            int end = text.indexOf(" ", start + 1);
            if (start != -1 && end != -1) {
                spannable.setSpan(new ForegroundColorSpan(Color.BLUE), start, end + 1,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                i = end;
            }
        }


        setText(spannable);
    }
}