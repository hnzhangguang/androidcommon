package com.yy.app.network;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.yy.app.R;
import com.yy.app.base.BaseActivity;
import com.yy.app.base.Logger;

import org.json.JSONObject;

/**
 * Volley是Android平台网络通信库：更快。更简单。更健壮 volley提供的功能：
 * 1.JSON、图片（异步）
 * 2.网络请求的排序
 * 3.网络请求的优先级处理
 * 4.缓存
 * 5.多级别的取消请求
 * 6.与Activity生命周期联动
 * * 获取Volley git clone
 * * https://android.googlesource.com/platform/frameworks/volley
 */
public class NetworkActivity extends BaseActivity {
    Button btn_volley;
    Button btn_volley_img;

    ImageView iv1;
    NetworkImageView iv2;

    @Override
    public void initContentViewXml() {

        setContentView(R.layout.activity_network);

    }

    @Override
    public void initView() {
        super.initView();
        btn_volley = findViewById(R.id.btn_volley);
        btn_volley_img = findViewById(R.id.btn_volley_img);
        iv1 = findViewById(R.id.imageView2);
        iv2 = findViewById(R.id.image2);


    }

    @Override
    public void initListener() {
        super.initListener();
        btn_volley_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadImageVolley();
            }
        });
        btn_volley.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getJSONVolley();
//                NetWorkImageViewVolley();
            }
        });
    }

    private void getJSONVolley() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String JSONDateUrl = "https://www.cnblogs.com/dongweiq/p/11504533.html";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, JSONDateUrl, null,
                new Response.Listener<JSONObject>() {
                    public void onResponse(JSONObject response) {
                        System.out.println("response=" + response);
                    }
                }, new Response.ErrorListener() {
            public void onErrorResponse(
                    com.android.volley.VolleyError arg0) {
                System.out.println("对不起，有问题");
                Logger.e("对不起，有问题: -> " + arg0);
            }
        });
        requestQueue.add(jsonObjectRequest);
    }


    // http://photocdn.sohu.com/20110927/Img320705637.jpg
    public void loadImageVolley() {
        String imageurl = "http://photocdn.sohu.com/20110927/Img320705637.jpg";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        final LruCache<String, Bitmap> lurcache = new LruCache<String, Bitmap>(
                20);
        ImageLoader.ImageCache imageCache = new ImageLoader.ImageCache() {

            @Override
            public void putBitmap(String key, Bitmap value) {
                lurcache.put(key, value);
            }

            @Override
            public Bitmap getBitmap(String key) {

                return lurcache.get(key);
            }
        };
        ImageLoader imageLoader = new ImageLoader(requestQueue, imageCache);
        ImageLoader.ImageListener listener = imageLoader.getImageListener(iv1,
                R.drawable.add, R.drawable.ic_launcher_foreground);
        imageLoader.get(imageurl, listener);
    }

    public void NetWorkImageViewVolley() {
        String imageUrl = "http://photocdn.sohu.com/20110927/Img320705637.jpg";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        final LruCache<String, Bitmap> lruCache = new LruCache<String, Bitmap>(20);
        ImageLoader.ImageCache imageCache = new ImageLoader.ImageCache() {

            @Override
            public void putBitmap(String key, Bitmap value) {
                lruCache.put(key, value);
            }

            @Override
            public Bitmap getBitmap(String key) {
                return lruCache.get(key);
            }
        };
        ImageLoader imageLoader = new ImageLoader(requestQueue, imageCache);
        iv2.setTag("url");
        iv2.setImageUrl(imageUrl, imageLoader);
    }


}
