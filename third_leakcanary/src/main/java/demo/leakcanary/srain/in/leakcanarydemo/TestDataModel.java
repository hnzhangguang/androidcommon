package demo.leakcanary.srain.in.leakcanarydemo;

import android.widget.TextView;


/**
 * 单例模式
 */
public class TestDataModel {

    private static TestDataModel sInstance;
    public TextView mRetainedTextView;

    public static TestDataModel getInstance() {
        if (sInstance == null) {
            sInstance = new TestDataModel();
        }
        return sInstance;
    }

    public void setRetainedTextView(TextView textView) {
        mRetainedTextView = textView;
    }
}