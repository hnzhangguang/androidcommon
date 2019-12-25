package function.etc;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.view.Window;

public class TransitionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // 设置contentFeature,可使用切换动画
            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
            //            init_explode();// 分解
            //            init_Slide();//滑动进入
            init_fade();//淡入淡出
        }


    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void init_fade() {
        Transition transition = new Fade().setDuration(200);
        getWindow().setEnterTransition(transition);
        getWindow().setExitTransition(transition);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void init_Slide() {
        Transition transition = new Slide().setDuration(200);
        getWindow().setEnterTransition(transition);
        getWindow().setExitTransition(transition);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void init_explode() {
        Explode explode = new Explode();
        explode.setDuration(200);
        getWindow().setEnterTransition(explode);
    }


}
