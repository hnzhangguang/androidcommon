package function.etc;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.widget.RemoteViews;

import com.yy.app.R;


/**
 * AppWidgetProvider的定义及其使用
 */
public class AppWidgetProviderImpl extends AppWidgetProvider {


    public AppWidgetProviderImpl() {
        super();
    }


    public static final String WIDGET_BTN_ACTION = "widget_btn_action";

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);


        /**
         * 是点击的广播,更新界面widget 的textview 的值
         */
        if (intent != null && TextUtils.equals(intent.getAction(), WIDGET_BTN_ACTION)) { //当intent
            // 不为空，且action匹配成功时，就接收广播，然后点击事件成功
            Log.e(WIDGET_BTN_ACTION, "is clicked");
            //接下来开始做点击事件里面的内容
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
                    R.layout.widget_layout);//注意：需要【重新】构造一个RemoteViews
            remoteViews.setTextViewText(R.id.widget_tv, "be clicked");
            remoteViews.setTextColor(R.id.widget_tv, Color.RED);

            AppWidgetManager appWidgetManager = null;// 单例模式
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.CUPCAKE) {
                appWidgetManager = AppWidgetManager.getInstance(context);
            }
            ComponentName componentName = new ComponentName(context, AppWidgetProviderImpl.class);
            appWidgetManager.updateAppWidget(componentName, remoteViews);//setText之后，记得更新一下
        }
    }


    /**
     * @param context          上下文
     * @param appWidgetManager 管理器
     * @param appWidgetIds     布局里面的ids
     */
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);


        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
        //需要构造一个RemoteViews

        Intent intent = new Intent();
        intent.setClass(context, AppWidgetProviderImpl.class); //通过intent把广播发给TestWidget本身，TestWidget
        // 接受到广播之后，会调用onReceive()方法进而刷新界面。
        intent.setAction(WIDGET_BTN_ACTION);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);


        // 添加点击事件 -> 发送一个广播
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
            // 为按钮添加点击事件
            remoteViews.setOnClickPendingIntent(R.id.widget_btn, pendingIntent);//控件btn_widget
            // 的点击事件：点击按钮时，会发一个带action的广播。
            // 更新下(因为给按钮添加了点击事件)
            appWidgetManager.updateAppWidget(appWidgetIds, remoteViews); //点击完了之后，记得更新一下。

        }


    }


}
