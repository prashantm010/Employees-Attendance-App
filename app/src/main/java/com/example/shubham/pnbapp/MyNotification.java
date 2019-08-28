package com.example.shubham.pnbapp;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by prashant maheshwari on 21-07-2018.
 */

public class MyNotification extends BroadcastReceiver {
        public static String NOTIFICATION_ID = "notification_id";
        public static String NOTIFICATION = "notification";

        @Override
        public void onReceive(final Context context, Intent intent) {

            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

            Notification notification = intent.getParcelableExtra(NOTIFICATION);
            int notificationId = intent.getIntExtra(NOTIFICATION_ID, 0);
            notificationManager.notify(notificationId, notification);
        }

}
