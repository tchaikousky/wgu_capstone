package com.tchaikousky.grade_tracker.UI;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import com.tchaikousky.grade_tracker.R;

import static android.content.Context.NOTIFICATION_SERVICE;

public class MyReceiver extends BroadcastReceiver {
    String channel_id="test";

    @Override
    public void onReceive(Context context, Intent intent) {
        int notificationNumber = intent.getIntExtra("notificationNumber", 0);
        boolean notifications = intent.getBooleanExtra("notifications", false);
        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        if((notificationNumber == 0) && (notifications)) {
            Toast.makeText(context, intent.getStringExtra("key"), Toast.LENGTH_LONG).show();
            createNotificationChannel(context, channel_id);
            Notification n = new NotificationCompat.Builder(context, channel_id)
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setContentText(intent.getStringExtra("key"))
                    .setContentTitle(intent.getStringExtra("contentTitle")).build();
            notificationManager.notify(notificationNumber, n);
        } else if((notificationNumber != 0) && (notifications)) {
            Toast.makeText(context, intent.getStringExtra("key"), Toast.LENGTH_LONG).show();
            createNotificationChannel(context, channel_id);
            Notification n = new NotificationCompat.Builder(context, channel_id)
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setContentText(intent.getStringExtra("key"))
                    .setContentTitle(intent.getStringExtra("contentTitle")).build();
            notificationManager.notify(notificationNumber, n);
        } else if(notificationNumber != 0) {
            notificationManager.cancel(notificationNumber);
        }

    }

    private void createNotificationChannel(Context context, String CHANNEL_ID) {
        CharSequence name = context.getResources().getString(R.string.channel_name);
        String description = context.getString(R.string.channel_description);
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
        channel.setDescription(description);
        NotificationManager notificationManager =
                context.getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);
    }
}