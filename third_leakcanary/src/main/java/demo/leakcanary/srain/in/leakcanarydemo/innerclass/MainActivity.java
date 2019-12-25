package demo.leakcanary.srain.in.leakcanarydemo.innerclass;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import demo.leakcanary.srain.in.leakcanarydemo.ExampleApplication;
import demo.leakcanary.srain.in.leakcanarydemo.R;


/**
 * 代码看着没有任何问题，其实内部类都会持有一个外部类引用，这里这个外部类就是MainActivity，
 * 然而内部类实例又是static静态变量其生命周期与Application生命周期一样，
 * 所以在MainActivity关闭的时候，内部类静态实例依然持有对MainActivity的引用，
 * 导致MainActivity无法被回收释放，引发内存泄漏。
 */
public class MainActivity extends AppCompatActivity {
    private static Config mConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //模拟内存泄露
        if (mConfig == null) {
            mConfig = new Config();
            mConfig.setSize(18);
            mConfig.setTitle("老九门");
        }
        finish();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ExampleApplication.getRefWatcher().watch(this);
    }

    class Config {
        private int size;
        private String title;

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}