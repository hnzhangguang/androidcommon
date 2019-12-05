package com.yy.app.base;

import android.util.Log;

/**
 * 日志工具类
 */
public class Logger {

    public static int level = 0;
    public static final String TAG = "mmmm";

    public static void e(String msg) {
        com.yy.app.lib.logger.Logger.e(TAG, msg);
    }

    public static void e(Object msg) {
        com.yy.app.lib.logger.Logger.e(TAG, msg);
    }

    public static void e(String tag, String msg) {
        com.yy.app.lib.logger.Logger.e(tag, msg);
    }

    public static void d(String msg) {
        com.yy.app.lib.logger.Logger.d(TAG, msg);
    }

}
