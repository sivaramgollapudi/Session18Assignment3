package com.sivaram.session18assignment3;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    // Declare Notification Button Object.
    Button showNotificationButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Reference Notification Button Object from xml to Java.
        showNotificationButton  = (Button)findViewById(R.id.notificationButton);

        // Set OnClick Listener to Show Notification Object
        showNotificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Call showNotification() method on Button Click
                showNotification();
            }
        });
    }

    // Create Notification Method.
    public void showNotification(){
        // Create Notification Lines Text Object.
        String[] notificationLines = new String[]{
                "Hello",
                "How are you.",
                "Hi !!!",
                "I am fine",
                "What about you",
                "I am great..."
        };

        // Create Notification Builder Object for current context.
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);

        // Create Inbox style object
        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();

        // Set Inbox style notification Content Title
        inboxStyle.setBigContentTitle("Event Details");

        // Loop through the length of notification lines string object and add to inbox style notification object.
        for(int lineNumber = 0 ; lineNumber< notificationLines.length; lineNumber++){

            // Add Line to inbox style notification object.
            inboxStyle.addLine(notificationLines[lineNumber]);
        }

        // Set Required Values to Notification Compact Builder Object
        builder.setSmallIcon(R.mipmap.ic_launcher) // Icon
                .setContentTitle("Inbox Style Notifications.")
                .setAutoCancel(true)
                .setStyle(inboxStyle)
                .setOngoing(true); // On Going

        // Create An Intent To Set Which Activity Shall start on
        Intent startIntent = new Intent(this, MainActivity.class);
        // Set Pending Intent for Notification Action.
        PendingIntent contentIntent = PendingIntent.getActivity(this, 101, startIntent, 0);

        // Set Content Intent To Notification Compact Builder.
        builder.setContentIntent(contentIntent);

        // Create Object of Notification with Notification Builder.
        Notification notification = builder.build();

        // Create Notification manager to Request Notification Service.
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Send Notification.
        notificationManager.notify(101, notification);
    }
}
