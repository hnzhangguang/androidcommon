package function.crash;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.Looper;
import android.os.Process;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;

/**
 * Created by zhangg on 17/12/16.
 */

public class CrashHandler implements Thread.UncaughtExceptionHandler {

    private static CrashHandler crashHandler;
    Thread.UncaughtExceptionHandler defaultHandler;
    public Context mContext;
    private HashMap<String, Object> msgMap = new HashMap<>();


    public void CrashHandler() {

    }

    public void init(Context context) {
        mContext = context;
        defaultHandler = Thread.getDefaultUncaughtExceptionHandler();

        Thread.setDefaultUncaughtExceptionHandler(this);


    }


    // 单例模式
    public static CrashHandler getInstance() {
        if (crashHandler == null) {
            synchronized (CrashHandler.class) {
                if (crashHandler == null) {
                    crashHandler = new CrashHandler();
                }
            }
        }
        return crashHandler;
    }


    /**
     * 当UncaughtException发生时会转入该函数来处理
     *
     * @param t
     * @param e
     */
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        // 1, 收集错误信息
        // 2, 保存错误信息
        // 3, 上传到服务器

        // 没有认为处理过次异常
        if (!handleException(e)) {

            if (defaultHandler != null) {
                // 系统默认的处理异常
                defaultHandler.uncaughtException(t, e);
            }

        } else {

            // 人为处理此异常
            // TODO
            SystemClock.sleep(2000);

            Process.killProcess(Process.myPid());
            System.exit(1);

        }


    }

    // 人为处理异常
    private boolean handleException(Throwable e) {

        if (e == null) {
            return false;
        }

        new Thread() {
            @Override
            public void run() {
                super.run();

                Looper.prepare();

                Toast.makeText(mContext, "msg", Toast.LENGTH_SHORT).show();

                Looper.loop();


            }
        }.start();

        // 收集错误信息
        collectErrorInfo();
        // 保存错误信息
        saveErrorInfo(e);


        return false;


    }

    // 保存错误信息
    private void saveErrorInfo(Throwable e) {


        StringBuilder stringbuffer = new StringBuilder();
        if (null != msgMap && msgMap.size() > 0) {
            Set<String> keys = msgMap.keySet();
            for (String key : keys) {
                stringbuffer.append(key + " = " + msgMap.get(key) + "\n");
            }
        }

        // 装饰者模式
        Writer writer = new StringWriter();
        PrintWriter printwriter = new PrintWriter(writer);
        e.printStackTrace(printwriter);

        Throwable cause = e.getCause();
        // 循环拿去所有错误信息
        while (null != cause) {
            cause.printStackTrace(printwriter);
            cause = e.getCause();
        }
        printwriter.close();

        String result = writer.toString();
        stringbuffer.append(result);

        //"yyyy-MM-dd HH:mm:ss"
        DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datestring = dateformat.format(new Date());
        stringbuffer.append(datestring);

        //Log.e("mmmm",stringbuffer.toString());

        // 保存的文件名称
        String fileName = "crash" + (datestring.replace(" ", "")) + "crash.log";

        // 判断SD卡是否存在
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            String path = "/sdcard/crash/";
            File dir = new File(path);
            // 不存在,需要创建
            if (!dir.exists()) {
                dir.mkdirs();
            }

            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(path + fileName);
                fos.write(stringbuffer.toString().getBytes());


            } catch (Exception e1) {
                Log.e("mmmm", e1.toString());
            } finally {
                try {
                    fos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

        }


    }


    // 收集错误信息
    private void collectErrorInfo() {

        // // TODO: 17/12/16
        Field[] fields = Build.class.getFields();
        if (null != fields) {

            try {
                // 把设备信息收集起来
                for (Field field : fields) {
                    field.setAccessible(true);
                    msgMap.put(field.getName(), field.get(null).toString());
                }
            } catch (Exception e) {
                Log.e("mmmm", e + "");
            }

        }

    }


}
