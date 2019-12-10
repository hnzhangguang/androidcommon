package function.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.yy.app.R;
import com.yy.app.base.BaseActivity;

public class NotificationActivity extends BaseActivity implements View.OnClickListener {

    @Override
    public void initContentViewXml() {
        setContentView(R.layout.activity_notification);
    }

    @Override
    public void initView() {
        super.initView();
        Button sendNotice = (Button) findViewById(R.id.send_notice);
        Button cancel_notice = (Button) findViewById(R.id.cancel_notice);
        sendNotice.setOnClickListener(this);
        cancel_notice.setOnClickListener(this);
    }

    public void cancelNotification() {

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.cancel(1);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            // 取消通知
            case R.id.cancel_notice:
                cancelNotification();
                break;

            // 发送通知
            case R.id.send_notice:
                Intent intent = new Intent(this, NotificationActivity.class);
                PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);
                NotificationManager manager =
                        (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                Notification notification =
                        new NotificationCompat.Builder(this)
                                .setContentTitle("This is content title")
                                .setContentText("This is content text")
                                .setWhen(System.currentTimeMillis())
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setLargeIcon(BitmapFactory.decodeResource(getResources(),
                                        R.mipmap.ic_launcher))
                                .setContentIntent(pi)
                                //        .setSound(Uri.fromFile(new
                                // File("/system/media/audio/ringtones/Luna.ogg")))
                                //        .setVibrate(new long[]{0, 1000, 1000, 1000})
                                //        .setLights(Color.GREEN, 1000, 1000)
                                .setDefaults(NotificationCompat.DEFAULT_ALL)
                                //        .setStyle(new NotificationCompat.BigTextStyle().bigText
                                //        ("Learn how to
                                // build notifications, send and sync data, and use voice actions. Get
                                // the official
                                // Android IDE and developer tools to build apps for Android."))
                                .setStyle(
                                        new NotificationCompat.BigPictureStyle()
                                                .bigPicture(
                                                        BitmapFactory.decodeResource(getResources(),
                                                                R.drawable.icon_toolbar)))
                                .setPriority(NotificationCompat.PRIORITY_MAX)
                                .build();
                manager.notify(1, notification);
                break;
            default:
                break;
        }
    }
}
