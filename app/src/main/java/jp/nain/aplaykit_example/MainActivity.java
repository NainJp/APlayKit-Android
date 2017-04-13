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
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import jp.nain.aplaykit.APlayExtender;

public class MainActivity extends AppCompatActivity implements ActionHistory.UpdateListener {

    ListView listView;
    TextView textView;
    ArrayAdapter<String> arrayAdapter;
    ActionHistory history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        history = ActionHistory.sharedInstance();
        history.setListener(this);

        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, history.getEventContexts());

        listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(arrayAdapter);

        textView = (TextView) findViewById(R.id.text_view);
        if (history.getEventContexts().isEmpty()) {
            textView.setVisibility(View.VISIBLE);
        } else {
            textView.setVisibility(View.INVISIBLE);
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendNotification();
            }
        });
    }

    public void onUpdate(final ActionEvent event) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textView.setVisibility(View.INVISIBLE);
                arrayAdapter.add(event.context);
            }
        });
    }

    private void sendNotification() {

        final CharSequence title = getString(R.string.notification_title);
        final CharSequence text = getString(R.string.notification_text);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        Notification notification = builder.setSmallIcon(R.drawable.ic_notification_small)
                .setContentTitle(title)
                .setContentText(text)
                .setPriority(Notification.PRIORITY_HIGH)
                .extend(getAPlayExtender())
                .build();
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify(1, notification);
    }

    private APlayExtender getAPlayExtender() {

        Intent voiceRecognitionIntent = new Intent(this, ActionService.class);
        voiceRecognitionIntent.setAction(ActionService.ACTION_VOICE_RECOGNITION);
        PendingIntent voiceRecognitionPendingIntent
                = PendingIntent.getService(this, 0, voiceRecognitionIntent, 0);

        Intent quickResponseIntent = new Intent(this, ActionService.class);
        quickResponseIntent.setAction(ActionService.ACTION_QUICK_RESPONSE);
        PendingIntent quickResponsePendingIntent
                = PendingIntent.getService(this, 0, quickResponseIntent, 0);

        return new APlayExtender()
                .setGuidanceStart(getString(R.string.guidance_start))
                .setGuidanceConfirm(getString(R.string.guidance_confirm))
                .setGuidanceSuccess(getString(R.string.guidance_success))
                .setGuidanceFailure(getString(R.string.guidance_failure))
                .addVoiceRecognitionAction(voiceRecognitionPendingIntent)
                .addQuickResponseAction(quickResponsePendingIntent);
    }

}
