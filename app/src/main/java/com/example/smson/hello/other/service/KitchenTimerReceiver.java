package com.example.smson.hello.other.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.widget.Toast;

import com.example.smson.hello.R;

/**
 * Created by sangmun on 2015-04-07.
 */
public class KitchenTimerReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // if (intent.getAction() == KitchenTimerService.ACTION))
        Toast.makeText(context, "Time Over!", Toast.LENGTH_LONG).show();

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, new Intent(context,
                KitchenTimerActivity.class), 0);

        Notification notification = new NotificationCompat.Builder(context)
                .setLargeIcon(
                        BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher))
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("KitchenTimer")
                .setContentText("Time Over!")
                .setContentIntent(pendingIntent)
                .setWhen(System.currentTimeMillis())
                .build();
        notification.flags = Notification.FLAG_AUTO_CANCEL;

        NotificationManagerCompat mNotificationManager = NotificationManagerCompat.from(context);
        mNotificationManager.notify(1, notification);

        MediaPlayer mp = MediaPlayer.create(context, R.raw.alarm);
        mp.start();
    }
}
