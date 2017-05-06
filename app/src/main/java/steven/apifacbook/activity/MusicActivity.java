package steven.apifacbook.activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.RemoteViews;

import de.hdodenhof.circleimageview.CircleImageView;
import steven.apifacbook.R;

public class MusicActivity extends AppCompatActivity {
    private CircleImageView civImage;
    private RotateAnimation r;
    Notification foregroundNote;
    private NotificationCompat.Builder builder;
    private NotificationManager notificationManager;
    private int notification_id;
    private RemoteViews remoteViews;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        iniUI();
    }

    protected void iniUI() {
        civImage = (CircleImageView) findViewById(R.id.civ_image);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.rotate_around_center_point);
        civImage.startAnimation(animation);
        customNotification();
        context = this;
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        remoteViews = new RemoteViews(getPackageName(), R.layout.notification);
        remoteViews.setImageViewResource(R.id.civ_thumbnail, R.drawable.ic_menu_camera);
        remoteViews.setTextViewText(R.id.tv_song_name, "Hi all!!!");
        
    }

    public void customNotification() {
        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.notification);
        Notification.Builder mNotifyBuilder = new Notification.Builder(this);
        foregroundNote = mNotifyBuilder.setContentTitle("123")
                .setContentText("Up down")
                .build();
        foregroundNote.bigContentView = remoteViews;
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(1, foregroundNote);

//        Intent intent = new Intent(this, MainActivity.class);
//        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
//        stackBuilder.addParentStack(MainActivity.class);
//        stackBuilder.addNextIntent(intent);
//        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,
//                PendingIntent.FLAG_UPDATE_CURRENT);
//        remoteViews.setOnClickPendingIntent(R.id.civ_thumbnail, resultPendingIntent);
//        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//        mNotificationManager.notify(100, mBuilder.build());
    }
}
