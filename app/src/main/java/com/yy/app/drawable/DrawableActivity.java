package com.yy.app.drawable;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.app.logger.LogUtil;
import com.app.logger.PrettyFormatStrategy;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.cunoraz.gifview.library.GifView;
import com.squareup.picasso.Picasso;
import com.yy.app.R;
import com.yy.app.base.BaseActivity;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import uk.co.senab.photoview.PhotoView;


/**
 * 图片主界面
 * ,1 PhotoView
 * 2, gifView
 * 3, glide ->
 */
public class DrawableActivity extends BaseActivity {


    ImageView imageView3;
    PhotoView iv_photo;  // 可缩放的imageview
    GifView gifView;
    Button btn_picasso;
    ImageView iv_picasso;

    Button btn_glide;
    ImageView iv_glide;
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
            LogUtil.e(e);
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

                LogUtil.w("ddd");
                PrettyFormatStrategy.methodCount = 3;
                LogUtil.e("jfkljffj");

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


    public void test() {

        String url = photo_url;

        // 0 -> 绑定生命周期
        // Glide与activity和fragment绑定生命周期很简单,只用在with的时候传入想绑定生命周期的Context就行.
        // 比如通常在MainActivity中传入this,或者MainActivity即可
        // (在Glide内部会根据你Context的实际类型做不同的处理,具体的分析会在以后的源码分析中展示)
        Glide.with(this).load(url).into(imageView3);

        //1, 占位符的使用
        Glide.with(this).load(url).placeholder(R.mipmap.ic_launcher).into(imageView3);
        //2, 设置错误图片(error)
        Glide.with(this).load(url).placeholder(R.mipmap.ic_launcher).error(R.mipmap.yangmi).into(imageView3);
        // 3, 设置动画(crossFade) -> Glide默认是包含淡入淡出动画的时间为300ms(毫秒),我们可以修改这个动画的时间
        Glide.with(this).load(url).placeholder(R.mipmap.ic_launcher).error(R.mipmap.yangmi).crossFade(5000).into(imageView3);
        //4.取消动画(dontAnimate)
        Glide.with(this).load(url).placeholder(R.mipmap.ic_launcher).error(R.mipmap.yangmi).dontAnimate().into(imageView3);

        //5, 加载本地图片: 需要权限 <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
        //本地文件
        File file = new File(Environment.getExternalStorageDirectory(), "xiayu.png");
        //加载图片
        Glide.with(this).load(file).into(imageView3);

        // 6 加载gif图片
        //如果只是简单加载gif,其实跟加载普通图片一样
        Glide.with(this).load("url...gif...").placeholder(R.mipmap.ic_launcher).into(imageView3);
        // 6.2 把gif当作普通图片加载(asBitmap)
        // ->如果希望加载gif时只加载gif的第一帧,把gif当作普通图片一样加载,那么只需要加上asBitmap方法即可
        Glide.with(this).load("url...gif...").asBitmap().placeholder(R.mipmap.ic_launcher).into(imageView3);
        // 6.3 检查是否gif(asGif)
        Glide.with(this).load("url...gif...").asGif().placeholder(R.mipmap.ic_launcher).error(R.mipmap.yangmi).into(imageView3);
        // 6.4 加载本地视频缩略图
        //Glide只会加载本地视频的第一帧,也就是缩略图,而且其实加载缩略图的时候也无需转化为Uri,直接把File丢进去就行了
        File mVideoFile = new File(Environment.getExternalStorageDirectory(), "xiayu.mp4");
        Glide.with(this).load(mVideoFile).placeholder(R.mipmap.ic_launcher).error(R.mipmap.yangmi).into(imageView3);
        // 6.5 其他 ->在大多数情况下，当你使用diskCacheStrategy（DiskCacheStrategy.SOURCE）时，Gif的加载速度会显着提高
        // (其实就是把gif资源缓存到磁盘)
        Glide.with(this).load("url...").diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .placeholder(R.mipmap.ic_launcher).error(R.mipmap.yangmi).into(imageView3);

        // 7, 内存缓存与磁盘缓存
        // 7,1缓存的资源 (1.原图(SOURCE) :原始图片 ; 2.处理图(RESULT) :经过压缩和变形等处理后的图片 )
        // 7.2 内存缓存策略(skipMemoryCache)
        //    Glide默认是会在内存中缓存处理图(RESULT)的.
        //    可以通过调用skipMemoryCache(true)来设置跳过内存缓存
        //    跳过内存缓存
        Glide.with(this).load(url).skipMemoryCache(true).into(imageView3);

        // 7.3 磁盘缓存策略(diskCacheStrategy)
        //        Glide磁盘缓存策略分为四种,默认的是RESULT(默认值这一点网上很多文章都写错了,但是这一点很重要):
        //        1.ALL:缓存原图(SOURCE)和处理图(RESULT)
        //        2.NONE:什么都不缓存
        //        3.SOURCE:只缓存原图(SOURCE)
        //        4.RESULT:只缓存处理图(RESULT) —默认值
        Glide.with(this).load(url).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView3);

        // 8 缓存大小及路径
        // 8.1 内存缓存最大空间
        //        内存缓存最大空间(maxSize)=每个进程可用的最大内存 * 0.4
        //        (低配手机的话是: 每个进程可用的最大内存 * 0.33)
        // 8.2 磁盘缓存大小
        //        磁盘缓存大小: 250 * 1024 * 1024(250MB)
        // 8.3 磁盘缓存目录
        //        磁盘缓存目录: 项目/cache/image_manager_disk_cache
        // 8.4 清除缓存 -> 注:在使用中的资源不会被清除
        //   清除所有内存缓存(需要在Ui线程操作)
        Glide.get(this).clearMemory();
        // 清除所有磁盘缓存(需要在子线程操作)
        Glide.get(this).clearDiskCache();

        // 9. 通过Modules定制Glide

        // 10
        //        图片尺寸的压缩或者拉伸(override)
        Glide.with(this).load(url).override(300, 300).into(imageView3);

        // 11 图片预处理(圆角,高斯模糊等)
        Glide.with(this).load(url).transform(new CornersTransform2(this, 50)).into(imageView3);

        // 12 使用多个transform
        Glide.with(this).load(url)
                .transform(new CornersTransform2(this), new CornersTransform2(this, 50))
                .into(imageView3);


        // 图片转换为bitmap
        Glide.with(this).load("").asBitmap().listener(new RequestListener<String, Bitmap>() {
            @Override
            public boolean onException(Exception e, String s, Target<Bitmap> target, boolean b) {
                // 加载失败
                return false;
            }

            @Override
            public boolean onResourceReady(Bitmap bitmap, String s, Target<Bitmap> target, boolean b,
                                           boolean b1) {
                if (target != null) {

                }
                return false;
            }
        }).into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap bitmap, GlideAnimation<? super Bitmap> glideAnimation) {
                if (bitmap != null) {
                    // 此时拿到了bitmap对象!!!!
                }
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
