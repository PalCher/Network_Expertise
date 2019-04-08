package com.nexp.pavel.networkexpertise.ui.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.nexp.pavel.networkexpertise.R;
import com.nexp.pavel.networkexpertise.ui.MainActivity;

public class NotificationService extends FirebaseMessagingService {

    private static final long[] VIBRATION_PATTERN = {0, 100, 50 ,100};

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        Log.d("My Service", remoteMessage.getData().toString());

        makeNote(this, remoteMessage.getNotification().getTitle(),
                remoteMessage.getNotification().getBody());
        Log.d("My Service", remoteMessage.getNotification().getBody());
    }

    private void makeNote(Context context, String title, String body) {

        NotificationCompat.Builder builder = new
                NotificationCompat.Builder(context, "2")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(body)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setTicker("Hey")
                .setVibrate(VIBRATION_PATTERN)
                .setAutoCancel(true)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .setCategory(NotificationCompat.CATEGORY_ALARM);

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("body", body);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent resultIntent = PendingIntent.getActivity(this, 0,
                intent, PendingIntent.FLAG_NO_CREATE);
        builder.setFullScreenIntent(resultIntent, true);
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = "YOUR_CHANNEL_ID";
            NotificationChannel channel = new NotificationChannel(channelId,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription("DESCRIPTION");
            channel.enableLights(true);
            channel.enableVibration(true);
            manager.createNotificationChannel(channel);
            builder.setChannelId(channelId);

        }

        manager.notify(1, builder.build());




    }
}
