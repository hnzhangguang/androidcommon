package com.yy.app.data;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.yy.app.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;


/**
 * 1,
 * * 使用 Jsoup 载入 Html 数据
 * *
 * * 使用 Jsoup 解析并提取 HTML 元素
 * *
 * * 使用 Jsoup 修改数据
 * *
 * * 使用 Jsoup HTML 文档清理
 * *
 * * 可以使用Jsoup解析Html，Epub，自定义格式文本
 * <p>
 * 2,
 * * 使用Jsoup解析Hmtl新闻列表
 * *
 * * 使用Jsoup解析Epub
 */
public class JsoupActivity extends AppCompatActivity {

    private String html = "<html><head><title>Jsoup用法</title></head>"
            + "<body><p><a href='http://www.baidu.com'>这里是 jsoup 项目的相关文章</a></p></body></html>";
    private String url = "http://www.baidu.com";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jsoup);


    }


    /**
     * 1,
     */
    public void firstHandler() {

        // html文本，url，本地html
        Document doc = Jsoup.parse(html);
        String title = doc.title(); // 获取html 的title
        Elements eles = doc.getElementsByTag("a"); // 获取所有标签
        for (Element link : eles) {
            String linkHref = link.attr("href"); // a标签里面的href属性的值
            String text = link.text();
        }
        Elements elements = doc.select("a[href]");
        Elements elements2 = doc.select("img[src$=.png]");
        Element element3 = doc.select("div.className").first();

        doc.select("div.className").attr("key", "value");
        doc.select("div.className").addClass("myclass");// class="myclass"
        doc.select("img").removeAttr("onclick");

        String htmls = "";// 不安全的
        String safe = Jsoup.clean(htmls, Whitelist.basic());// 安全的

        try {
            Document doc2 = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Document doc3 = Jsoup.connect(url).data("key", "value")
                    .timeout(3000).post();
        } catch (IOException e) {
            e.printStackTrace();
        }

        File input = new File(Environment.getExternalStorageDirectory()
                + "/index.html");
        try {
            Document doc4 = Jsoup.parse(input, "utf-8", "http://baidu.com");
        } catch (IOException e) {
            e.printStackTrace();
        }
        // ../baidu.png -> http://baidu.com/baidu.png


    }


    /**
     * 解析 epub 文件
     */
    private void parseEpub() {
        try {
            InputStream is = getAssets().open("fb.ncx");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String epubText = new String(buffer, "utf-8");
            Document doc = Jsoup.parse(epubText);
            String docTitle = doc.getElementsByTag("docTitle").first().text();
            Log.i("info", docTitle);
            Elements elements = doc.getElementsByTag("navPoint");
            for (Element ele : elements) {
                String title = ele.text();
                String href = ele.getElementsByTag("content").first()
                        .attr("src");
                Log.i("info", title + ":" + href);
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    /**
     * 解析 url 文件 (html)
     */
    private void parseHtml() {
        try {
            Document doc = Jsoup.connect(url).get();
            Elements elements = doc.select("div.unit");
            for (Element ele : elements) {
                String title = ele.getElementsByTag("h1").first().text();
                String href = ele.getElementsByTag("h1").first()
                        .getElementsByTag("a").first().attr("href");
                Log.i("info", title + ":" + href);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


}
