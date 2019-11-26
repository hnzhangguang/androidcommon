package com.yy.app.base;

/**
 * 日志工具类
 */
public class Logger {

    public static int level = 0;
    public static final String TAG = "mmmm";

    public static void e(String msg) {
        com.yy.app.lib.logger.Logger.e(TAG, msg);
    }


    public static void d(String msg) {
        com.yy.app.lib.logger.Logger.d(TAG, msg);
    }


}
