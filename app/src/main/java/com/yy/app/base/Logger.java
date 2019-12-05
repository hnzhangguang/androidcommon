package com.yy.app.base;

/**
 * 日志工具类
 */
public class Logger {

    public static int level = 0;
    private static final String TAG = "mmmm";

    public static void e(String msg) {
        com.yy.app.lib.logger.Logger.e(TAG, msg);
    }

    public static void w(String msg) {
        com.yy.app.lib.logger.Logger.w(TAG, msg);
    }

    public static void e(Object msg) {
        com.yy.app.lib.logger.Logger.e(TAG, msg);
    }

    public static void e(String tag, String msg) {
        com.yy.app.lib.logger.Logger.e(tag, msg);
    }


}
