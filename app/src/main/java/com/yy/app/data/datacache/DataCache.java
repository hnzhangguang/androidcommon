package com.yy.app.data.datacache;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;


/**
 * 1,DiskLruCache 的使用
 * 1, 缓存图片
 */
public class DataCache extends AppCompatActivity {


    private final static String uniqueName = "uniqueName";
    private final static long cacheSize = 10 * 1024 * 1024; // 10MB
    private final static String url = "";
    ImageView imageView = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        try {
            // 1, 初始化DiskLruCache的时候调用它的open方法
            //四个参数分别为，1.缓存的路径目录 2.版本号 3.每个节点对应的数据个数，4.缓存的大小，10 * 1024 * 1024 = 10M
            //            要传入四个参数：
            //            File directory:sdcard缓存的目录。
            //            int appVersion：版本号，一般传1即可
            //            int valueCount：缓存的数据由key对应着，表示一个key对应多少个数据，一般传1即可
            //            long maxSiz:缓存的大小 10 * 1024 * 1024 = 10M
            //            传入sdcard缓存的目录的时候，记得先判断sdcard是否存在，或者sdcard是否正在移除。
            //            如果是这两种情况。缓存目录就设置为getCacheDir().getPath();在内存中缓存。
            final DiskLruCache diskLruCache = DiskLruCache.open(getCachFile(DataCache.this,
                    uniqueName), 1, 1,
                    cacheSize);

            // 2,写入缓存

            new Thread() {
                @Override
                public void run() {
                    DiskLruCache.Editor editor = null;
                    try {
                        //创建 Editor 对象
                        editor = diskLruCache.edit(hashKeyForDisk(url));
                        if (editor != null) {
                            //创建输出流
                            OutputStream outputStream = editor.newOutputStream(0);
                            //url 也就是 下载图片的地址
                            //outputStream 的作用在于，
                            //从网络下载图片的时候，图片通过该输出流写到文件系统，
                            //也就是说，图片下载到了磁盘缓存中。
                            if (downloadUrlToStream(url, outputStream)) {
                                editor.commit();
                            } else {
                                //释放编辑锁
                                editor.abort();
                            }
                            diskLruCache.flush();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }.start();


            // 3, 读取缓存
            DiskLruCache.Snapshot snapshot = diskLruCache.get(hashKeyForDisk(url));
            if (snapshot != null) {
                InputStream inputStream = snapshot.getInputStream(0);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                //如果不为空，则直接展示缓存中的bitmap
                imageView.setImageBitmap(bitmap);
            }

            // 4, 移除缓存 ->调用remove方法，移除指定的数据。


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /**
     * 从网络中下载图片，并写到缓存中
     *
     * @param urlString
     * @param outputStream
     * @return
     */
    private boolean downloadUrlToStream(String urlString, OutputStream outputStream) {
        HttpURLConnection urlConnection = null;
        BufferedOutputStream out = null;
        BufferedInputStream in = null;
        try {
            final URL url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedInputStream(urlConnection.getInputStream(), 8 * 1024);
            out = new BufferedOutputStream(outputStream, 8 * 1024);
            int b;
            while ((b = in.read()) != -1) {
                out.write(b);
            }
            return true;
        } catch (final IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }


    /**
     * 将key进行加密
     *
     * @param key
     * @return
     */
    public String hashKeyForDisk(String key) {
        String cacheKey;
        try {
            final MessageDigest mDigest = MessageDigest.getInstance("MD5");
            mDigest.update(key.getBytes());
            cacheKey = bytesToHexString(mDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            cacheKey = String.valueOf(key.hashCode());
        }
        return cacheKey;
    }

    private String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }


    /**
     * 获取缓存目录
     *
     * @param context
     * @param uniqueName 指定目录下的文件名
     */
    private File getCachFile(Context context, String uniqueName) {
        String catchPath;
        //有内存卡，并且内存卡没有正在移除，就把文件缓存到内存卡中
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) || !Environment.isExternalStorageRemovable()) {
            catchPath = Objects.requireNonNull(context.getExternalCacheDir()).getPath();
        } else {
            catchPath = context.getCacheDir().getPath();
        }
        return new File(catchPath + File.separator + uniqueName);
    }


}
