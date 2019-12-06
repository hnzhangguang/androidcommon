package com.yy.app.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;

import com.tandong.swichlayout.BaseEffects;
import com.tandong.swichlayout.SwichLayoutInterFace;
import com.tandong.swichlayout.SwitchLayout;
import com.yy.app.R;

/**
 * 1, SwichLayoutInterFace 接口
 */
public class SwitchLayoutTestActivity extends AppCompatActivity implements SwichLayoutInterFace {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch_layout_test);

        // 进入时候3DRotate动画效果
        setEnterSwichLayout();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                setExitSwichLayout();
                break;
            default:
                break;
        }
        return true;
    }


    @Override
    public void setEnterSwichLayout() {
        // 3DRotate 从右边进来
        SwitchLayout.get3DRotateFromRight(this, false,
                BaseEffects.getQuickToSlowEffect());
    }

    @Override
    public void setExitSwichLayout() {
        // 从左边出去
        SwitchLayout.getSlideToLeft(this, true, null);
    }


}
