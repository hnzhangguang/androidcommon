package com.yy.app.drawable;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.app.logger.LogUtils;
import com.app.logger.PrettyFormatStrategy;
import com.cunoraz.gifview.library.GifView;
import com.squareup.picasso.Picasso;
import com.yy.app.R;
import com.yy.app.base.BaseActivity;
import com.yy.app.base.Logger;

import org.litepal.util.LogUtil;

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

    Button btn_glide;
    ImageView iv_glide;
    private static final String photo_url = "http://photocdn.sohu" +
            ".com/20110927/Img320705637.jpg";

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

        iv_glide = findViewById(R.id.iv_glide);
        btn_glide = findViewById(R.id.btn_glide);

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

        btn_glide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 常规基本用法
                //                Glide.with(DrawableActivity.this).load(photo_url)
                //                .into(iv_glide);

                // 加载文件
                InputStream is = null;
                try {
                    is = getAssets().open("photoview.jpg");
                    Bitmap bm = BitmapFactory.decodeStream(is);
                    // 加载本地图片
                    //                    File file = new File(is);
                    //                    Glide.with(DrawableActivity.this).load(file)
                    //                    .into
                    //                    (imageView);

                    // 加载二进制流
                    byte[] image = null;
                    //                    Glide.with(DrawableActivity.this).load(image)
                    //                    .into
                    //                    (iv_glide);

                    //// 加载Uri对象
                    //Uri imageUri = getImageUri();
                    //Glide.with(this).load(imageUri).into(imageView);


                } catch (IOException e) {
                    e.printStackTrace();
                }

                //// 加载应用资源
                int resource = R.drawable.add;
                //                Glide.with(DrawableActivity.this).load(resource).into
                //                (iv_glide);


                //加载带有占位图
                //                Glide.with(DrawableActivity.this)
                //                        .load(photo_url)
                //                        .placeholder(R.drawable.add)
                //                        .into(iv_glide);


                // 加载失败,放占位符
                //                Glide.with(DrawableActivity.this).load(photo_url)
                //                        .placeholder(R.drawable.add)
                //                        .error(R.drawable.icon_toolbar)
                //                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                //                        //关闭Glide的硬盘缓存机制
                //                        .into(iv_glide);

                //                LogUtils.e("aaaa", "fkd");

                LogUtils.w("ddd");
                PrettyFormatStrategy.methodCount = 3;
                LogUtils.e("jfkljffj");

            }
        });

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
        //        Logger.e("DrawableActivity->onDestroy");
        super.onDestroy();
    }
}
