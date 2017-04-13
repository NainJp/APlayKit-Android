# APlayKit-Android

APlayKit is a softwar developers kit for the hearable device "APlay". APlay let you notify information by voice from any apps. APlayKit can customise the notification and to enable receiving the intent like connection or user initiation events.

## Requisites

- Phone : Android 5.0~ (Supported Bluetooth Low Energy: BLE)
- APlay device : NA1, NA1L http://www.nain.jp/aplay/


## Get started

You can get library from JCenter.

``` Gradle
dependencies {
	compile 'jp.nain:aplaykit:0.1.0'
}
```

## Features available and usage example

### Customising Voice Notification

These are custmizable guidance when APlay read out notification:


- Guidance about starting voice input
- Confirmation input result
- Success / Failue status for notification action

You need to set just only NotificationCompat.Builder of Android API

```Java

	APlayExtender ext = new APlayExtender()
		.setGuidanceStart("Start") // start voice input 
		.setGuidanceConfirm("Confirmation:%s") // confirm result of voice input
		.setGuidanceSuccess("Succeeded")  // action success
		.setGuidanceFailure("Failed"); // action failure
                
	NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
	Notification notification = builder.setSmallIcon(R.drawable.ic_notification_small)
                .setContentTitle("Title")
                .setContentText("Body")
                .setPriority(Notification.PRIORITY_HIGH)
                .extend(ext) // ★
                .build();

```


### Handling User Action Callbacks

Your app can receive user action about APlay oparation:

- Result of voice input with text
- Event of quick response

```Java

	// Prepare to receive intent about voice input
	Intent voiceRecognitionIntent = new Intent(this, ActionService.class);
	voiceRecognitionIntent.setAction(ActionService.ACTION_VOICE_RECOGNITION);
	PendingIntent voiceRecognitionPendingIntent = PendingIntent.getService(this, 0, voiceRecognitionIntent, 0);
	
	// Prepare to receive intent about quick response
	Intent quickResponseIntent = new Intent(this, ActionService.class);
	quickResponseIntent.setAction(ActionService.ACTION_QUICK_RESPONSE);
	PendingIntent quickResponsePendingIntent = PendingIntent.getService(this, 0, quickResponseIntent, 0);


	APlayExtender ext = new APlayExtender()
		.addVoiceRecognitionAction(voiceRecognitionPendingIntent) // Set Intent for voice input
		.addQuickResponseAction(quickResponsePendingIntent); // Set Intent for quick action
                
	NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
	Notification notification = builder.setSmallIcon(R.drawable.ic_notification_small)
                .setContentTitle("Tile")
                .setContentText("Body")
                .setPriority(Notification.PRIORITY_HIGH)
                .extend(ext)
                .build();

```

### Receiving APlay Events

To acquire events about APlay:

- APlay connection
- APlay disconnection
- Call the app by user initiaion

You have to describe Android Manifest to be called.

```AndroidManifest.xml

	<receiver
	    android:name=".APlayEventReceiver"
	    android:exported="true">
	    <intent-filter>
	        <action android:name="jp.nain.aplay.ACTION_CALL_EVENT" />
	        <action android:name="jp.nain.aplay.ACTION_CONNECTED_EVENT" />
	        <action android:name="jp.nain.aplay.ACTION_DISCONNECTED_EVENT" />
	    </intent-filter>
	</receiver>


```

```Java

public class APlayEventReceiver extends EventReceiver {

    @Override
    protected void onConnected(Context context){
        Log.i("APlayKit", "onConnected");
    }

    @Override
    protected void onDisconnected(Context context){
        Log.i("APlayKit", "onDisconnected");
    }

    @Override
    protected void onCalled(Context context){
        Log.i("APlayKit", "onCalled");
        // Do custom launch action
    }
}

```

## License

Copyright 2017 Nain Inc.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
