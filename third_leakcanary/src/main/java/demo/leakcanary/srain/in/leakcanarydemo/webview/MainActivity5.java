package demo.leakcanary.srain.in.leakcanarydemo.webview;

import android.os.Bundle;
import android.webkit.WebView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import demo.leakcanary.srain.in.leakcanarydemo.ExampleApplication;
import demo.leakcanary.srain.in.leakcanarydemo.R;


/**
 * 2:
 * 该如何解决呢？这个查了不少资料，其中一种就是使用getApplicationgContext作为参数构建WebView，
 * 然后动态添加到一个ViewGroup中，最后退出的时候调用webView的销毁的函数，虽然也达到了防止内存溢出的效果，
 * 但是在有些网页弹出时候需要记住密码的对话框的时候，
 * 会出现Unable to add window -- token null is not for an application 的错误，
 * 所以这里采用的解决办法是通过把使用了WebView的Activity(或者Service)放在单独的进程里。
 * 然后在检测到应用占用内存过大有可能被系统干掉或者它所在的Activity(或者Service)结束后，
 * 调用android.os.Process.killProcess(android.os.Process.myPid());，
 * 主动Kill掉进程。由于系统的内存分配是以进程为准的，进程关闭后，系统会自动回收所有内存。
 * <p>
 * manifest中对应的activity配置如下：
 * <activity
 * android:name=".MainActivity5"
 * android:process="com.whoislcj.webview"/>
 */
public class MainActivity5 extends AppCompatActivity {
    private WebView mWebView;
    LinearLayout mLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        mLinearLayout = (LinearLayout) findViewById(R.id.llayout);
        mWebView = (WebView) findViewById(R.id.web);
        mWebView.loadUrl("http://www.cnblogs.com/whoislcj/p/5720202.html");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ExampleApplication.getRefWatcher().watch(this);
    }


    // 1
    //WebView解析网页时会申请Native堆内存用于保存页面元素，当页面较复杂时会有很大的内存占用。
    // 如果页面包含图片，内存占用会更严重。并且打开新页面时，为了能快速回退，之前页面占用的内存也不会释放。
    // 有时浏览十几个网页，都会占用几百兆的内存。
    // 这样加载网页较多时，会导致系统不堪重负，最终强制关闭应用，也就是出现应用闪退或重启。
    // 及时Activity关闭时在onDestroy中调用如下代码也是没有任何作用。

    private void destroyWebView() {
        if (mWebView != null) {
            mLinearLayout.removeView(mWebView);
            mWebView.pauseTimers();
            mWebView.removeAllViews();
            mWebView.destroy();
            mWebView = null;
        }
    }

}