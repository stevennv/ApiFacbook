package steven.apifacbook.ultis.firebase;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.io.Serializable;

import steven.apifacbook.R;
import steven.apifacbook.activity.LikedPageActivity;


/**
 * Created by quyet on 13/12/2016.
 */

public class FirebaseDataReceiver extends FirebaseMessagingService {
    private Context context;
    private final String TAG = "FirebaseDataReceiver";

    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "I'm in!!!");

        Bundle dataBundle = intent.getExtras();
        if (dataBundle != null) {

//

        }
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        if (remoteMessage.getData().size() > 0){
            sendNotification();
        }
    }

    private void sendNotification() {

        Intent intent = new Intent(context, LikedPageActivity.class);

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0 /* Request code */, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        android.support.v4.app.NotificationCompat.Builder notificationBuilder = new android.support.v4.app.NotificationCompat.Builder(context);

        android.support.v4.app.NotificationCompat.Builder builder = new android.support.v4.app.NotificationCompat.Builder(context);
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            notificationBuilder.setSmallIcon(R.drawable.ic_picture);
        } else {
            notificationBuilder.setSmallIcon(R.drawable.ic_picture);
        }
        notificationBuilder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher));
        notificationBuilder.setContentTitle("Thông báo");
        notificationBuilder.setContentText("Cái gì vậy???");
        notificationBuilder.setColor(context.getResources().getColor(R.color.background_color));
        notificationBuilder.setAutoCancel(true);
        notificationBuilder.setSound(defaultSoundUri);
        notificationBuilder.setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }

}