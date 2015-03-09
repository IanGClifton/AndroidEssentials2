package com.iangclifton.androidessentials;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver {
    private static final String TAG = "MyReceiver";

    public static final String ACTION_MY_DATA = "com.iangclifton.androidessentials.action.MY_DATA";

    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        final String action = intent.getAction();
        if (action.equals(ACTION_MY_DATA)) {
            Log.d(TAG, "Receive ACTION_MY_DATA");

            final Intent notificationIntent = new Intent(context, NotificationActivity.class);
            final PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

            // Notification style
            final NotificationCompat.BigTextStyle style = new NotificationCompat.BigTextStyle();
            style.bigText("This is the big text.  It is quite a bit of text");
            style.setBigContentTitle("This is the big title");
            style.setSummaryText("Summary text");

            // Notification action
            final NotificationCompat.Action notificationAction = new NotificationCompat.Action(R.drawable.ic_stat_person, "T-pose", pendingIntent);

            // Build notification
            final NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
            builder.setSmallIcon(R.drawable.ic_stat_person);
            builder.setContentTitle("Our notification title");
            builder.setContentText("Some really interesting text");
            builder.setAutoCancel(true);
            builder.setContentIntent(pendingIntent);
            builder.setStyle(style);
            builder.addAction(notificationAction);
            builder.setColor(context.getResources().getColor(R.color.main_color));
            final Notification notification = builder.build();

            // Display the notification
            final NotificationManagerCompat nm = NotificationManagerCompat.from(context);
            nm.notify(0, notification);
        }

    }
}
