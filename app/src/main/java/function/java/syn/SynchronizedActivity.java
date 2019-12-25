package function.java.syn;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.app.logger.LogUtil;
import com.yy.app.base.BaseActivity;


public class SynchronizedActivity extends BaseActivity {


    Button button4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        //        setContentView(R.layout.activity_synchronized);


        //        button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                mainsynchronized();
                //                mainLock();


            }
        });

    }

    @Override
    public void initContentViewXml() {

    }


    /**
     * synchronized  同步的实现
     */
    public void mainsynchronized() {
        final Testsynchronized test = new Testsynchronized();
        for (int i = 0; i < 10; i++) {
            new Thread() {
                public void run() {
                    for (int j = 0; j < 100; j++)
                        test.increase();
                }

                ;
            }.start();
        }

        while (Thread.activeCount() > 1)  //保证前面的线程都执行完
            Thread.yield();
        LogUtil.e(test.inc);
    }


    /**
     * synchronized  同步的实现
     */
    public void mainLock() {
        final TestLock test = new TestLock();
        for (int i = 0; i < 10; i++) {
            new Thread() {
                public void run() {
                    for (int j = 0; j < 100; j++)
                        test.increase();
                }

                ;
            }.start();
        }

        while (Thread.activeCount() > 1)  //保证前面的线程都执行完
            Thread.yield();
        LogUtil.e(test.inc);
    }


}
