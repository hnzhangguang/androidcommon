package function.webview;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.yy.app.R;
import com.yy.app.base.BaseActivity;

public class WebviewActivity extends BaseActivity {


    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void initContentViewXml() {
        setContentView(R.layout.activity_webview);

        WebView webView = (WebView) findViewById(R.id.web_view);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://www.baidu.com");
    }
}
