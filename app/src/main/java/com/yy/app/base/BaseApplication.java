package com.yy.app.base;

import android.app.Application;
import android.graphics.Bitmap;

import com.activeandroid.ActiveAndroid;
import com.app.logger.LogUtil;

import org.litepal.LitePal;


/**
 * 1, ORM  -> ActiveAndroid.initialize(this);
 */
public class BaseApplication extends Application {

    Bitmap bitmap = null;

    @Override
    public void onCreate() {
        super.onCreate();

        LitePal.initialize(this);  // LitePal

        ActiveAndroid.initialize(this);   // ORM
        //        LogUtil.e("BaseApplication->onCreate");
    }


    /**
     * OnLowMemory是Android提供的API，
     * 在系统内存不足，所有后台程序（优先级为background的进程，不是指后台运行的进程）都被杀死时，系统会调用OnLowMemory。
     */
    @Override
    public void onLowMemory() {
        super.onLowMemory();
        LogUtil.e("BaseApplication->onLowMemory");
    }

    /**
     * OnTrimMemory是Android 4.0之后提供的API，系统会根据不同的内存状态来回调。根据不同的内存状态，来响应不同的内存释放策略。
     *
     * @param level TRIM_MEMORY_COMPLETE：内存不足，并且该进程在后台进程列表最后一个，马上就要被清理
     *              TRIM_MEMORY_MODERATE：内存不足，并且该进程在后台进程列表的中部。
     *              TRIM_MEMORY_BACKGROUND：内存不足，并且该进程是后台进程。
     *              TRIM_MEMORY_UI_HIDDEN：内存不足，并且该进程的UI已经不可见了。
     *              以上4个是4.0增加
     *              TRIM_MEMORY_RUNNING_CRITICAL：内存不足(后台进程不足3个)，并且该进程优先级比较高，需要清理内存
     *              TRIM_MEMORY_RUNNING_LOW：内存不足(后台进程不足5个)，并且该进程优先级比较高，需要清理内存
     *              TRIM_MEMORY_RUNNING_MODERATE：内存不足(后台进程超过5个)，并且该进程优先级比较高，需要清理内存
     */
    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        //        LogUtil.e("BaseApplication->onTrimMemory");


        // 先判断是否已经回收
        if (bitmap != null && !bitmap.isRecycled()) {
            // 回收并且置为null
            bitmap.recycle();
            bitmap = null;
            System.gc();
        }
    }


    @Override
    public void onTerminate() {
        super.onTerminate();
        LogUtil.e("BaseApplication->onTerminate");
    }
}
