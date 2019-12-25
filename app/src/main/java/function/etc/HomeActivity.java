package function.etc;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.yy.app.R;

public class HomeActivity extends AppCompatActivity {


    private Context context;
    private HomeKeyListener listener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //        setContentView(R.layout.activity_home);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animate();
            }
        });


        this.context = this;

        listener = new HomeKeyListener(this);

        listener.setOnHomeKeyPressListener(new HomeKeyListener.OnHomeKeyPressListener() {
            @Override
            public void onHomeKeyPress() {
                Toast.makeText(context, "按下了HOME键", Toast.LENGTH_SHORT).show();
            }
        });

        listener.setOnHomekeyLongPressListener(new HomeKeyListener.OnHomeKeyLongPressListener() {
            @Override
            public void onHomeKeyLongPress() {
                Toast.makeText(context, "长按了HOME键", Toast.LENGTH_SHORT).show();
            }
        });


    }


    /**
     * 方法1: 监听Back键按下事件
     * 注意: super.onBackPressed()会自动调用finish()方法,关闭当前Activity. 若要屏蔽Back键盘,注释该行代码即可
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        System.out.println("按下了back键   onBackPressed()");
    }

    /**
     * 方法2: 监听Back键按下事件
     * 注意: 返回值表示:是否能完全处理该事件在此处返回false,所以会继续传播该事件.在具体项目中此处的返回值视情况而定.
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            System.out.println("按下了back键   onKeyDown()");
            return false;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }


    @Override
    public void onStart() {
        listener.start();
        super.onStart();
    }

    @Override
    protected void onStop() {
        listener.stop();
        super.onStop();
    }


    public void animate() {


    }
}
