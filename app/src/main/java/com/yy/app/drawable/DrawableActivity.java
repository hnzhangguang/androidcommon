package com.yy.app.drawable;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.cunoraz.gifview.library.GifView;
import com.squareup.picasso.Picasso;
import com.yy.app.R;
import com.yy.app.base.BaseActivity;
import com.yy.app.base.Logger;

import java.io.IOException;
import java.io.InputStream;

import uk.co.senab.photoview.PhotoView;


/**
 * 图片主界面
 * ,1 PhotoView
 * 2, gifView
 */
public class DrawableActivity extends BaseActivity {


    ImageView imageView3;
    PhotoView iv_photo;  // 可缩放的imageview
    GifView gifView;
    Button btn_picasso;
    ImageView iv_picasso;
    private static final String photo_url = "http://photocdn.sohu.com/20110927/Img320705637.jpg";

    @Override
    public void initContentViewXml() {
        setContentView(R.layout.activity_drawable);
    }

    @Override
    public void initView() {
        super.initView();
        imageView3 = findViewById(R.id.imageView3);
        iv_photo = findViewById(R.id.iv_photo);
        btn_picasso = findViewById(R.id.btn_picasso);
        iv_picasso = findViewById(R.id.iv_picasso);
        gifView = findViewById(R.id.gifView);


        //PhotoView的使用
        try {
            // 获取图片流
            InputStream is = getAssets().open("photoview.jpg");
            Bitmap bm = BitmapFactory.decodeStream(is);
//            imageView3.setImageBitmap(bm);
            iv_photo.setImageBitmap(bm);
        } catch (IOException e) {
            Logger.e(e);
            e.printStackTrace();
        }


        //
        //设置图片源
        gifView.setGifResource(R.drawable.gif187);//本地图片
        //位置设置
        gifView.setX(150);
        gifView.setY(300);


    }

    @Override
    public void initListener() {
        super.initListener();

        // picasso 基本用法
        btn_picasso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 基本用法
//                Picasso.with(DrawableActivity.this)
//                        .load(photo_url)
//                        .into(iv_picasso);

                // 固定大小
                Picasso.with(DrawableActivity.this)
                        .load(photo_url)
                        .resize(50, 50).into(iv_picasso);

                // 默认错误时候的图片
                Picasso.with(DrawableActivity.this)
                        .load(photo_url)
                        .error(R.drawable.add).into(iv_picasso);
            }
        });
    }

    @Override
    protected void onDestroy() {
        if (gifView != null) {
            gifView.destroyDrawingCache();
            gifView = null;
        }
        if (imageView3 != null) {
            imageView3 = null;
        }
        System.gc();
        Logger.e("DrawableActivity->onDestroy");
        super.onDestroy();
    }
}
