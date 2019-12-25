package function.java.ref;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import java.lang.ref.WeakReference;

public class WeakRefActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //        setContentView(R.layout.activity_weak_ref);
    }


    private static final int MSG_ID = 0x00;

    public void testSafeHandler() {
        SafeHandler handler = new SafeHandler(this);
        handler.sendEmptyMessage(MSG_ID);
    }

    public static class SafeHandler extends Handler {

        private WeakReference<AppCompatActivity> mActivityRef;

        public SafeHandler(AppCompatActivity activity) {
            mActivityRef = new WeakReference<>(activity);
        }

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_ID:
                    AppCompatActivity activity = mActivityRef.get();
                    if (activity != null) {
                        activity.finish();
                    }
            }
        }
    }


}
