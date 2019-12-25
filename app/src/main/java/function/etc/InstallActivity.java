package function.etc;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class InstallActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //        setContentView(R.layout.activity_install);
    }


    /**
     * 启动安装好的apk
     *
     * @param url
     */
    private void openApk(String url) {
        PackageManager packageManager = getPackageManager();
        Intent intent = packageManager.getLaunchIntentForPackage("com.fungo.loveshow");
        startActivity(intent);
    }


    /**
     * 检查包是否存在
     *
     * @param packname
     * @return
     */
    private boolean checkPackInfo(String packname) {
        PackageInfo packageInfo = null;
        try {
            packageInfo = getPackageManager().getPackageInfo(packname, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return packageInfo != null;
    }


    /**
     * 注意：
     * 1.需要将目标Activity的android:exported="true"属性在所属应用AndroidMainfest里设置为true，
     * 意思是当前Activity可以被外部应用访问。
     * 2.需要在当前应用的AndroidMainfest里也声明目标Activity。
     */
    public void exportActivity() {
        Intent intent = new Intent();
        //第一种方式
        ComponentName cn = new ComponentName("com.example.fm", "com.example.fm" +
                ".MainFragmentActivity");
        intent.setComponent(cn);
        //第二种方式
        //intent.setClassName("com.example.fm", "com.example.fm.MainFragmentActivity");
        intent.putExtra("test", "intent1");
        startActivity(intent);
    }


}
