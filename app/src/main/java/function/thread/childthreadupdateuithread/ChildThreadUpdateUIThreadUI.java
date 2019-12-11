package function.thread.childthreadupdateuithread;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;


/**
 * Android在子线程中更新UI的方法汇总(共七种)
 * 1, handler.sendMessage(msg);
 * 2, Handler handler1 = new Handler(callback);  -> handler1.sendMessage(msg);
 * 3, handler.post(runnable)
 * 4, handler.postDelayed()
 * 5, runOnUiThread()
 * 6, view.post(runnable)
 * 7, view.postDelayed(runnable, milliseconds)
 */
@SuppressLint("HandlerLeak")
public class ChildThreadUpdateUIThreadUI extends AppCompatActivity {


    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1000) {
                int arg1 = msg.arg1; // 获取参数
            }
            super.handleMessage(msg);
        }
    };

    // 第一种方式 -> 常规写法：new Handler()的handleMessage()和handler.sendMessage(msg)
    public void firstMethod() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                Message msg = Message.obtain();
                msg.what = 1000;
                msg.arg1 = 10;
                handler.sendMessage(msg);
            }
        }).start();
    }


    /**
     * 第二种方式, 另外的一种handler
     */
    public void secondMethod() {

        // 使用callback构造handler对象
        final Handler handler1 = new Handler(callback);
        new Thread(new Runnable() {
            @Override
            public void run() {
                Message msg = Message.obtain();
                msg.what = 1001;
                msg.arg1 = 11;
                handler1.sendMessage(msg);
            }
        }).start();

    }

    private Handler.Callback callback = new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            return true;
        }
    };


    /**
     * 第三种方式 -> handler.post(runnable)
     */
    public void thridMethod() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                // post()
                handler.post(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
            }
        }).start();

    }


    /**
     * 第四种 -> postDelayed()
     */
    public void fourMethod() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                    }
                }, 3000);
            }
        }).start();


    }


    /**
     * 第五种方式 -> runOnUiThread()
     */
    public void fiveMethod() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
            }
        }).start();
    }

    Button button = null;

    /**
     * 第六种 -> view.post(runnable)
     */
    public void sixMethod() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                // btn.post()
                button.post(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
            }
        }).start();
    }

    /**
     * 7 , view.postDelayed(runnable, milliseconds)
     */
    public void sevenMethod() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                button.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                    }
                }, 3000);
            }
        }).start();
    }

}
