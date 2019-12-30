package com.yy.app.contentprovider;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * 读取手机里图片(利用ContentProvider)
 */
public class AllPhotoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    /**
     * 读取手机中所有图片信息
     */
    private void getAllPhotoInfo() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<MediaBean> mediaBeen = new ArrayList<MediaBean>();
                HashMap<String, List<MediaBean>> allPhotosTemp = new HashMap<>();//所有照片
                Uri mImageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                String[] projImage = {MediaStore.Images.Media._ID
                        , MediaStore.Images.Media.DATA
                        , MediaStore.Images.Media.SIZE
                        , MediaStore.Images.Media.DISPLAY_NAME};
                Cursor mCursor = getContentResolver().query(mImageUri,
                        projImage,
                        MediaStore.Images.Media.MIME_TYPE + "=? or " + MediaStore.Images.Media.MIME_TYPE + "=?",
                        new String[]{"image/jpeg", "image/png"},
                        MediaStore.Images.Media.DATE_MODIFIED + " desc");

                if (mCursor != null) {
                    while (mCursor.moveToNext()) {

                        String type = "";

                        // 获取图片的路径
                        String path =
                                mCursor.getString(mCursor.getColumnIndex(MediaStore.Images.Media.DATA));
                        int size =
                                mCursor.getInt(mCursor.getColumnIndex(MediaStore.Images.Media.SIZE)) / 1024;
                        String displayName =
                                mCursor.getString(mCursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME));
                        //用于展示相册初始化界面
                        mediaBeen.add(new MediaBean(type, path, size, displayName));
                        // 获取该图片的父路径名
                        String dirPath = new File(path).getParentFile().getAbsolutePath();
                        //存储对应关系
                        if (allPhotosTemp.containsKey(dirPath)) {
                            List<MediaBean> data = allPhotosTemp.get(dirPath);

                            data.add(new MediaBean(type, path, size, displayName));
                            continue;
                        } else {
                            List<MediaBean> data = new ArrayList<MediaBean>();
                            data.add(new MediaBean(type, path, size, displayName));
                            allPhotosTemp.put(dirPath, data);
                        }
                    }
                    mCursor.close();
                }
                //更新界面
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //...
                    }
                });
            }
        }).start();
    }


    /**
     * 获取手机中所有视频的信息
     */
    private void getAllVideoInfos() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HashMap<String, List<MediaBean>> allPhotosTemp = new HashMap<>();//所有照片
                Uri mImageUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                String[] proj = {MediaStore.Video.Thumbnails._ID
                        , MediaStore.Video.Thumbnails.DATA
                        , MediaStore.Video.Media.DURATION
                        , MediaStore.Video.Media.SIZE
                        , MediaStore.Video.Media.DISPLAY_NAME
                        , MediaStore.Video.Media.DATE_MODIFIED};
                Cursor mCursor = getContentResolver().query(mImageUri,
                        proj,
                        MediaStore.Video.Media.MIME_TYPE + "=?",
                        new String[]{"video/mp4"},
                        MediaStore.Video.Media.DATE_MODIFIED + " desc");
                if (mCursor != null) {
                    while (mCursor.moveToNext()) {
                        // 获取视频的路径
                        int videoId = mCursor.getInt(mCursor.getColumnIndex(MediaStore.Video.Media._ID));
                        String path =
                                mCursor.getString(mCursor.getColumnIndex(MediaStore.Video.Media.DATA));
                        int duration =
                                mCursor.getInt(mCursor.getColumnIndex(MediaStore.Video.Media.DURATION));
                        long size =
                                mCursor.getLong(mCursor.getColumnIndex(MediaStore.Video.Media.SIZE)) / 1024; //单位kb
                        if (size < 0) {
                            //某些设备获取size<0，直接计算
                            Log.e("dml", "this video size < 0 " + path);
                            size = new File(path).length() / 1024;
                        }
                        String displayName =
                                mCursor.getString(mCursor.getColumnIndex(MediaStore.Video.Media.DISPLAY_NAME));
                        long modifyTime =
                                mCursor.getLong(mCursor.getColumnIndex(MediaStore.Video.Media.DATE_MODIFIED));//暂未用到

                        //提前生成缩略图，再获取：http://stackoverflow
                        // .com/questions/27903264/how-to-get-the-video-thumbnail-path-and-not-the-bitmap
                        MediaStore.Video.Thumbnails.getThumbnail(getContentResolver(), videoId,
                                MediaStore.Video.Thumbnails.MICRO_KIND, null);
                        String[] projection = {MediaStore.Video.Thumbnails._ID,
                                MediaStore.Video.Thumbnails.DATA};
                        Cursor cursor =
                                getContentResolver().query(MediaStore.Video.Thumbnails.EXTERNAL_CONTENT_URI
                                        , projection
                                        , MediaStore.Video.Thumbnails.VIDEO_ID + "=?"
                                        , new String[]{videoId + ""}
                                        , null);
                        String thumbPath = "";
                        while (cursor.moveToNext()) {
                            thumbPath =
                                    cursor.getString(cursor.getColumnIndex(MediaStore.Video.Thumbnails.DATA));
                        }
                        cursor.close();
                        // 获取该视频的父路径名
                        String dirPath = new File(path).getParentFile().getAbsolutePath();

                        String videoType = "";
                        //存储对应关系
                        if (allPhotosTemp.containsKey(dirPath)) {
                            List<MediaBean> data = allPhotosTemp.get(dirPath);
                            data.add(new MediaBean(videoType, path, thumbPath, duration,
                                    size, displayName));
                            continue;
                        } else {
                            List<MediaBean> data = new ArrayList<>();
                            data.add(new MediaBean(videoType, path, thumbPath, duration,
                                    size, displayName));
                            allPhotosTemp.put(dirPath, data);
                        }
                    }
                    mCursor.close();
                }
                //更新界面
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //...
                    }
                });
            }
        }).start();
    }


}
