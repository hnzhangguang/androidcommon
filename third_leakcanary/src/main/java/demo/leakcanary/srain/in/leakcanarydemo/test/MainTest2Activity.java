package demo.leakcanary.srain.in.leakcanarydemo.test;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import demo.leakcanary.srain.in.leakcanarydemo.ExampleApplication;
import demo.leakcanary.srain.in.leakcanarydemo.R;

public class MainTest2Activity extends AppCompatActivity {


    private Handler mHandler = new Handler();
    TextView mTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_test2);


        mTextView = (TextView) findViewById(R.id.textView);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mTextView.setText("lcj");
            }
        }, 10 * 1000);


        //        SystemClock.sleep(2000);
        //finish();


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ExampleApplication.getRefWatcher().watch(this);
    }


}
