package demo.leakcanary.srain.in.leakcanarydemo.AsyncTask;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import demo.leakcanary.srain.in.leakcanarydemo.ExampleApplication;
import demo.leakcanary.srain.in.leakcanarydemo.R;


/**
 * 在处理一个比较耗时的操作时，可能还没处理结束MainActivity就执行了退出操作，
 * 但是此时AsyncTask依然持有对MainActivity的引用就会导致MainActivity无法释放回收引发内存泄漏。
 */
public class MainActivity extends AppCompatActivity {
    private AsyncTask<Void, Void, Integer> asyncTask;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = (TextView) findViewById(R.id.text);
        testAsyncTask();
        finish();
    }

    private void testAsyncTask() {
        asyncTask = new AsyncTask<Void, Void, Integer>() {
            @Override
            protected Integer doInBackground(Void... params) {
                int i = 0;
                //模拟耗时操作
                while (!isCancelled()) {
                    i++;
                    if (i > 1000000000) {
                        break;
                    }
                    Log.e("LeakCanary", "asyncTask---->" + i);
                }
                return i;
            }

            @Override
            protected void onPostExecute(Integer integer) {
                super.onPostExecute(integer);
                mTextView.setText(String.valueOf(integer));
            }
        };
        asyncTask.execute();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ExampleApplication.getRefWatcher().watch(this);
    }

}