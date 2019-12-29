package com.yy.app.notification;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.yy.app.R;

public class NotificationActivity extends AppCompatActivity {

    private static final int NOTIFICATIONS_ID = 9900;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification2);
        findViewById(R.id.button8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testNotificationNew();
            }
        });
    }


    /**
     * new的方式, 在高版本上好使
     */
    @SuppressLint("ObsoleteSdkInt")
    public void testNotificationNew() {


        // 1, 设置点击后跳转
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        intent.setClass(this, NotificationResultActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);


        // 2
        PendingIntent pendingIntent = PendingIntent.getActivity(this
                , (int) SystemClock.uptimeMillis()
                , intent
                , PendingIntent.FLAG_UPDATE_CURRENT);

        // 3
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // 4
        //重点：先创建通知渠道
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(getString(R.string.app_name),
                    getString(R.string.app_name), NotificationManager.IMPORTANCE_LOW);
            mChannel.setDescription("此处填写通知渠道介绍");
            mChannel.setShowBadge(false);
            notificationManager.createNotificationChannel(mChannel);
        }

        // 5
        //再创建通知
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,
                getString(R.string.app_name));


        // 6
        //设置通知栏大图标，上图中右边的大图
        builder.setLargeIcon(BitmapFactory.decodeResource(
                getResources(), R.mipmap.ic_launcher))
                // 设置状态栏和通知栏小图标
                .setSmallIcon(R.drawable.ic_launcher_background)
                // 设置通知栏应用名称
                .setTicker("通知栏应用名称")
                // 设置通知栏显示时间
                .setWhen(System.currentTimeMillis())
                // 设置通知栏标题
                .setContentTitle("通知栏标题")
                // 设置通知栏内容
                .setContentText("通知栏内容")
                // 设置通知栏点击后是否清除，设置为true，当点击此通知栏后，它会自动消失
                .setAutoCancel(false)
                // 将Ongoing设为true 那么左滑右滑将不能删除通知栏
                .setOngoing(true)
                // 设置通知栏点击意图
                .setContentIntent(pendingIntent)
                // 铃声、闪光、震动均系统默认
                .setDefaults(Notification.DEFAULT_ALL)
                // 设置为public后，通知栏将在锁屏界面显示
                .setVisibility(NotificationCompat.VISIBILITY_PRIVATE)
                // 从Android4.1开始，可以通过以下方法，设置通知栏的优先级，优先级越高的通知排的越靠前，
                // 优先级低的，不会在手机最顶部的状态栏显示图标
                // 设置优先级为PRIORITY_MAX，将会在手机顶部显示通知栏
                .setPriority(NotificationCompat.PRIORITY_MIN);

        notificationManager.notify(NOTIFICATIONS_ID, builder.build());


    }


}
