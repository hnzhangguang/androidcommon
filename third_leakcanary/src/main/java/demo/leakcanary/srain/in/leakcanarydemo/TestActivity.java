package demo.leakcanary.srain.in.leakcanarydemo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;


public class TestActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        TextView textView = (TextView) findViewById(R.id.test_text_view);

        // 单例模式 会造成内存泄漏(赋值给了一个单利模式对象了)
        TestDataModel.getInstance().setRetainedTextView(textView);
    }
}