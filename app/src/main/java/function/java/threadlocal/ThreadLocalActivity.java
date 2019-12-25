package function.java.threadlocal;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Date;


/**
 * ThreadLocal和线程同步机制相比有什么优势呢？ThreadLocal和线程同步机制都是为了解决多线程中相同变量的访问冲突问题。
 * <p>
 * 在同步机制中，通过对象的锁机制保证同一时间只有一个线程访问变量。
 * 这时该变量是多个线程共享的，使用同步机制要求程序慎密地分析什么时候对变量进行读写，
 * 什么时候需要锁定某个对象，什么时候释放对象锁等繁杂的问题，程序设计和编写难度相对较大。
 * <p>
 * 而ThreadLocal则从另一个角度来解决多线程的并发访问。在编写多线程代码时，可以把不安全的变量封装进ThreadLocal。
 * <p>
 * 由于ThreadLocal中可以持有任何类型的对象，低版本JDK所提供的get()返回的是Object对象，需要强制类型转换。
 * 但JDK 5.0通过泛型很好的解决了这个问题，在一定程度地简化ThreadLocal的使用。
 * <p>
 * 概括起来说，对于多线程资源共享的问题，同步机制采用了“以时间换空间”的方式，而ThreadLocal采用了“以空间换时间”的方式。
 * 前者仅提供一份变量，让不同的线程排队访问，而后者为每一个线程都提供了一份变量，因此可以同时访问而互不影响。
 */
public class ThreadLocalActivity extends AppCompatActivity {


    //创建线程局部变量local,属于多个线程公用的一个缓存区
    public static final ThreadLocal local = new ThreadLocal();


    Button button3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //        setContentView(R.layout.activity_thread_local);
        //        button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                test();
            }
        });


    }


    Thread thread1 = new Thread() {
        @Override
        public void run() {
            local.set("thread1" + new Date());

            SystemClock.sleep(1000);

            Object o = local.get();
            Log.e("mmmmm", o + "");


        }
    };


    Thread thread2 = new Thread() {
        @Override
        public void run() {
            local.set("thread2" + new Date());
            SystemClock.sleep(2000);

            Object o = local.get();
            Log.e("mmmmm2", o + "");


        }
    };


    public void test() {


        thread2.start();
        thread1.start();


    }


}
