package function.util;

import android.app.AppOpsManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Binder;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;

import java.lang.reflect.Method;

/**
 * 说明:  悬浮框 工具类
 * <p>
 * 作者 zhangg
 * 时间: 18/10/9.
 */

public class DrawOverlayUtil {

    /**
     * 判断是有悬浮框权限
     *
     * @return
     */
    public static boolean canDrawOverlays(Context context) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                return Settings.canDrawOverlays(context);
            }
        } catch (NoSuchMethodError e) {
        }
        return false;
    }

    /**
     * 判断是否缺少权限
     *
     * @param permission
     * @return
     */
    public static boolean checkSelfPermission(Context context, String permission) {
        return ContextCompat.checkSelfPermission(context, "android.permission" +
                ".SYSTEM_ALERT_WINDOW") ==
                PackageManager.PERMISSION_GRANTED;
    }


    public static boolean checkPermission(Context context, String permission) {

        return (PackageManager.PERMISSION_GRANTED ==
                context.getPackageManager().checkPermission("android.permission" +
                        ".SYSTEM_ALERT_WINDOW", context.getPackageName()));

    }


    /**
     * 4.4 以上可以直接判断准确
     * <p>
     * 4.4 以下非MIUI直接返回true
     * <p>
     * 4.4 以下MIUI 可 判断 上一次打开app 时 是否开启了悬浮窗权限
     *
     * @param context
     * @return
     */
    //OP_SYSTEM_ALERT_WINDOW=24   op = 24
    public static boolean checkOp(Context context, int op) {
        final int version = Build.VERSION.SDK_INT;

        if (version >= 19) {
            AppOpsManager manager = (AppOpsManager) context.getSystemService(Context
                    .APP_OPS_SERVICE);
            try {

                Class<?> spClazz = Class.forName(manager.getClass().getName());
                Method method = manager.getClass().getDeclaredMethod("checkOp", int.class, int
                        .class, String.class);
                int property = (Integer) method.invoke(manager, op,
                        Binder.getCallingUid(), context.getPackageName());

                if (AppOpsManager.MODE_ALLOWED == property) {
                    return true;
                } else {
                    return false;
                }
            } catch (Exception e) {

            }
        } else {

        }
        return true;
    }

    /**
     * 判断 悬浮窗口权限是否打开
     *
     * @param context
     * @return true 允许  false禁止
     */
    public static boolean getAppOps(Context context) {
        try {
            Object object = context.getSystemService(Context.APP_OPS_SERVICE);
            if (object == null) {
                return false;
            }
            Class localClass = object.getClass();
            Class[] arrayOfClass = new Class[3];
            arrayOfClass[0] = Integer.TYPE;
            arrayOfClass[1] = Integer.TYPE;
            arrayOfClass[2] = String.class;
            Method method = localClass.getMethod("checkOp", arrayOfClass);
            if (method == null) {
                return false;
            }
            Object[] arrayOfObject1 = new Object[3];
            arrayOfObject1[0] = Integer.valueOf(24);
            arrayOfObject1[1] = Integer.valueOf(Binder.getCallingUid());
            arrayOfObject1[2] = context.getPackageName();
            int m = ((Integer) method.invoke(object, arrayOfObject1)).intValue();
            return m == AppOpsManager.MODE_ALLOWED;
        } catch (Exception ex) {

        }
        return false;
    }


}
