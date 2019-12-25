package function.etc;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.yy.app.R;

public class StatusBar2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBar3();

        findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Window window = getWindow();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.setStatusBarColor(getResources().getColor(R.color.colorAccent));

                    //设置白底黑字
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        getWindow().getDecorView()
                                .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                    }

                }

            }
        });
        findViewById(R.id.button5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Window window = getWindow();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.setStatusBarColor(getResources().getColor(R.color.colorAccent));

                    //设置白底黑字
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        getWindow().getDecorView()
                                .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                    }

                }

            }
        });


    }


    /**
     * 需求四、不同Fragment中对StatusBar的处理不一样
     */
    public void setStatusBar4() {

    }


    /**
     * 3.需求三、标题栏与状态栏颜色一致 xml中配置
     *
     * <style name="status_toolbar_same_color" parent="Theme.AppCompat.Light.DarkActionBar">
     * <!-- Customize your theme here. -->
     * <item name="colorPrimary">@color/status_toolBar_same_color</item>
     * <item name="colorPrimaryDark">@color/status_toolBar_same_color</item>
     * <item name="colorAccent">@color/colorAccent</item>
     * </style>
     */
    public void setStatusBar3() {


        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.colorAccent));

            //设置白底黑字
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                getWindow().getDecorView()
                        .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }

        } else {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            ViewGroup systemContent = (ViewGroup) findViewById(android.R.id.content);
            View statusBarView = new View(this);
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                    , getStatusBarHeight());
            statusBarView.setBackgroundColor(getResources().getColor(R.color.titleColorSelected));
            systemContent.getChildAt(0).setFitsSystemWindows(true);
            systemContent.addView(statusBarView, 0, lp);
        }


    }


    /**
     * 需求二、全屏保留状态栏文字(页面上部有Banner图)
     */
    public void setStatusBar2() {

        //现在项目，大部分向下支持到19，所以先不考虑太低版本的情况
        Window window = getWindow();
        //默认API 最低19
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR2) {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            ViewGroup contentView =
                    (ViewGroup) window.getDecorView().findViewById(Window.ID_ANDROID_CONTENT);
            Log.e("mmmm", "count:" + contentView.getChildCount() + "");
            contentView.getChildAt(0).setFitsSystemWindows(false);
        }

    }


    /**
     * 1. 全屏，不保留状态栏文字(Splash页面，欢迎页面)
     * 首先在style.xml中设置为noActionBar的主题
     *
     * <style name="fullScreen" parent="Theme.AppCompat.DayNight.NoActionBar">
     * </style>
     * <p>
     * //方式一
     * //getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
     * //方式二
     * //getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
     * //方式三 style.xml中配置
     * //<style name="fullScreen" parent="Theme.AppCompat.DayNight.NoActionBar">
     * //        <item name="android:windowFullscreen">true</item>
     * //</style>
     */
    public void setStatusBar1() {


    }


    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }


}
