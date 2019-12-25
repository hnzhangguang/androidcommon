package function.etc;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class configChangesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //        setContentView(R.layout.activity_config_changes);
    }


    /**
     * 横竖屏改变
     *
     * @param newConfig
     */
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == this.getResources().getConfiguration().ORIENTATION_PORTRAIT) {//
            // 切换为竖屏

        } else if (newConfig.orientation == this.getResources().getConfiguration()
                .ORIENTATION_LANDSCAPE) {// 切换为横屏

        }
    }


}