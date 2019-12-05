package com.yy.app.asynctask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.yy.app.R;
import com.yy.app.base.BaseActivity;
import com.yy.app.base.Logger;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class AsyncTaskActivity extends BaseActivity {

    private ImageView mImageView;
    private ProgressBar mProgressBar;
    private Button btn_asyncTask;
    private final static String url = "http://photocdn.sohu.com/20110927/Img320705637.jpg";


    @Override
    public void initContentViewXml() {
        setContentView(R.layout.activity_async_task);
    }

    @Override
    public void initView() {
        super.initView();

        btn_asyncTask = findViewById(R.id.btn_asyncTask);
        mImageView = findViewById(R.id.imagview);
        mProgressBar = findViewById(R.id.progressbar);


    }

    @Override
    public void initListener() {
        super.initListener();

        btn_asyncTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //设置传递进去的参数
                new MyAsyncTask().execute(url);
            }
        });
    }


    //<url类型 ，进度值类型，返回值类型>
    class MyAsyncTask extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Logger.e("MyAsyncTask->onPreExecute");
            mProgressBar.setVisibility(View.VISIBLE);
        }

        @Override
        // 可变参数 只取出一个参数就是第0位
        protected Bitmap doInBackground(String... params) {
            Logger.e("MyAsyncTask->doInBackground");
            String url = params[0];// 获取传递进来的参数
            Bitmap bitmap = null;
            URLConnection connection;
            InputStream is;
            try {
                connection = new URL(url).openConnection();
                is = connection.getInputStream();
                BufferedInputStream bis = new BufferedInputStream(is);
                // 通过decodeStream解析输入流转化为Bitmap
                bitmap = BitmapFactory.decodeStream(bis);
                is.close();
                bis.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
            // 将bitmap作为返回值返回
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            Logger.e("MyAsyncTask->onPostExecute");
            super.onPostExecute(bitmap);
            mProgressBar.setVisibility(View.GONE);
            mImageView.setImageBitmap(bitmap);
        }

    }


}
