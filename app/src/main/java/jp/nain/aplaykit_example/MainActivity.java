package jp.nain.aplaykit_example;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import jp.nain.aplaykit.APlayExtender;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CharSequence title = "title";
                CharSequence text = "text";
                sendNotification(title, text);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void sendNotification(CharSequence title, CharSequence text) {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        Notification notification = builder.setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(text)
                .setPriority(Notification.PRIORITY_HIGH)
                .extend(getAPlayExtender())
                .build();
        NotificationManager manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        manager.notify(1, notification);
    }

    private APlayExtender getAPlayExtender() {

        Intent voiceRecognitionIntent = new Intent(this, ActionService.class);
        voiceRecognitionIntent.setAction("ACTION_VOICE_RECOGNITION");
        PendingIntent voiceRecognitionPendingIntent
                = PendingIntent.getService(this, 0, voiceRecognitionIntent, 0);

        Intent quickResponseIntent = new Intent(this, ActionService.class);
        quickResponseIntent.setAction("ACTION_QUICK_RESPONSE");
        PendingIntent quickResponsePendingIntent
                = PendingIntent.getService(this, 0, quickResponseIntent, 0);

        return new APlayExtender()
                .setGuidanceStart("今を記録しますか?")
                .setGuidanceConfirm("「%s」と記録します")
                .setGuidanceSuccess("記録しました")
                .setGuidanceFailure("記録できませんでした")
                .setMuteAppName(true)
                .setPriorTicker(true)
                .addVoiceRecognitionAction(voiceRecognitionPendingIntent)
                .addQuickResponseAction(quickResponsePendingIntent);
    }
}
