package com.yy.app.drawable;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.ExternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator;
import com.bumptech.glide.module.GlideModule;


/**
 * 自定义
 * 通过Modules定制Glide
 */
public class XiayuGlideModule implements GlideModule {


    @Override
    public void applyOptions(Context context, GlideBuilder builder) {

        //1, 自定义缓存
        //配置内存缓存大小 10MB
        builder.setMemoryCache(new LruResourceCache(10 * 1024 * 1024));
        //配置图片池大小   20MB
        builder.setBitmapPool(new LruBitmapPool(20 * 1024 * 1024));


        //但是内存缓存的大小数值其实不应该是随便配置的,
        // Glide的内部的默认值是通过一系列的计算获取的,比如说判断手机是否高配置手机等
        //这样有个取巧的办法,就是获取Glide默认的设置,然后在这个设置的基础上进行修改
        //获取内存计算器
        MemorySizeCalculator calculator = new MemorySizeCalculator(context);
        //        //获取Glide默认内存缓存大小
        int defaultMemoryCacheSize = calculator.getMemoryCacheSize();
        //        //获取Glide默认图片池大小
        int defaultBitmapPoolSize = calculator.getBitmapPoolSize();
        //        //将数值修改为之前的1.1倍
        int myMemoryCacheSize = (int) (1.1 * defaultMemoryCacheSize);
        int myBitmapPoolSize = (int) (1.1 * defaultBitmapPoolSize);
        //        //修改默认值
        builder.setMemoryCache(new LruResourceCache(myMemoryCacheSize));
        builder.setBitmapPool(new LruBitmapPool(myBitmapPoolSize));

        // 2, 自定义磁盘缓存
        //        - 私有缓存 (自己本app可以使用,目录在data/data/应用包名 下面)
        //        - 外部缓存(都可以访问,目录在扩展空间内,如SD卡)
        // 2.1私有缓存
        //设置磁盘缓存大小
        int size = 100 * 1024 * 1024;
        //设置磁盘缓存
        builder.setDiskCache(new InternalCacheDiskCacheFactory(context, size));
        //如果需求修改缓存路径的话,需要增加一下参数即可(变为data/data/应用包名/cache/xiayu)
        //设置磁盘缓存大小
        int size2 = 100 * 1024 * 1024;
        String dir = "xiayu";
        // 2.2设置磁盘缓存
        builder.setDiskCache(new InternalCacheDiskCacheFactory(context, dir, size2));
        //外部缓存
        //设置磁盘缓存大小
        int size3 = 100 * 1024 * 1024;
        //设置磁盘缓存
        builder.setDiskCache(new ExternalCacheDiskCacheFactory(context, size3));


    }

    @Override
    public void registerComponents(Context context, Glide glide) {

    }
}
