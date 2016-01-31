package com.example.gisi.userdictionaryprovider;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.UserDictionary.Words;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

/**
 * This is the central activity for the Provider Dictionary Example App. The purpose of this app is
 * to show an example of accessing the {@link Words} list via its' Content Provider.
 */
public class MainActivity extends AppCompatActivity {

    /**********************
     NOTE
     Press Ctrl+Alt+L  at regular intervals :p

     ************************/

    private NotificationManager myNotificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void notify(View v)
    {
        showNotification();
        Log.e("as","clicked");
    }

    //YOUR CODE
    public void Notify()
    {
        // Prepare intent which is triggered if the
        // notification is selected
        Intent intent = new Intent(this, NotificationReciever.class);
        PendingIntent pIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent, 0);

        // Build notification
        // Actions are just fake
        NotificationCompat.Builder noti = new NotificationCompat.Builder(this).setContentTitle("New mail from " + "test@gmail.com").setContentText("Subject").setSmallIcon(R.drawable.icon).setContentIntent(pIntent);
        NotificationManager notificationManager = (NotificationManager) getSystemService(getApplicationContext().NOTIFICATION_SERVICE);
        // hide the notification after its selected
        // noti.flags |= Notification.FLAG_AUTO_CANCEL;

        notificationManager.notify(0, noti.build());

    }

    //MY CODE
    public void showNotification() {
        Log.e("as","clicked");
        NotificationManager notificationManager = (NotificationManager) this.getSystemService(NOTIFICATION_SERVICE);

      //  Intent intent = new Intent(this, NotificationReciever.class);
       // PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification.Builder notificationBuilder = new Notification.Builder(this)
                .setContentTitle("Subject")
                .setContentText("New mail from"+"abc@gmail.com")
                .setSmallIcon(R.drawable.icon)
               // .setContentIntent(pIntent)
                .setAutoCancel(false)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));

        if(Build.VERSION.SDK_INT<16) //Since notificationBuilder.build(); doesn't works on <API 16
            notificationManager.notify(0, notificationBuilder.getNotification());
        else
            notificationManager.notify(0,notificationBuilder.build());
    }


}

