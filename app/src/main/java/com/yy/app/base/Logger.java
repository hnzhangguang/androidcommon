package com.yy.app.base;

import com.app.logger.LogUtils;

/**
 * 日志工具类
 */
public class Logger {

    public static int level = 0;
    private static final String TAG = "mmmm";

    public static void e(String msg) {
        LogUtils.e(msg);
    }

    public static void w(String msg) {
        LogUtils.w(TAG, msg);
    }

    public static void e(Object msg) {
        LogUtils.e(TAG, msg);
    }

    public static void e(String tag, String msg) {
        LogUtils.e(tag, msg);
    }


}
