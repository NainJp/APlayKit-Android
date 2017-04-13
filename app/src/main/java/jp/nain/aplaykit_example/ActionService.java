package jp.nain.aplaykit_example;

import android.app.IntentService;
import android.app.RemoteInput;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import jp.nain.aplaykit.APlayExtender;

import static jp.nain.aplaykit.APlayExtender.QUICK_RESPONSE_NEGATIVE;
import static jp.nain.aplaykit.APlayExtender.QUICK_RESPONSE_POSITIVE;

/**
 * Created by ytakano-Nain on 2017/02/27.
 */
public class ActionService extends IntentService {

    public static String ACTION_VOICE_RECOGNITION = "jp.nain.aplaykit_example.ACTION_VOICE_RECOGNITION";
    public static String ACTION_QUICK_RESPONSE = "jp.nain.aplaykit_example.ACTION_QUICK_RESPONSE";

    public ActionService() {
        super(ActionService.class.getSimpleName());
    }

    @Override
    public void onHandleIntent(Intent intent) {
        if (intent == null) {
            return;
        }

        String action = intent.getAction();
        Bundle result = RemoteInput.getResultsFromIntent(intent);
        if (result == null) {
            return;
        }
        if (ACTION_VOICE_RECOGNITION.equals(action)) {
            handleVoiceRecognitionResult(result);
        } else if (ACTION_QUICK_RESPONSE.equals(action)) {
            handleQuickResponseResult(result);
        } else {
            // nothing
        }
    }

    private void handleVoiceRecognitionResult(Bundle result) {
        String resultText = result.getString(APlayExtender.ACTION_VOICE_RECOGNITION);
        Log.i("APlayKit", "VoiceRecognitionResult: "+resultText);
        ActionEvent event = new ActionEvent(ActionEvent.Type.VOICE, "Voice \""+resultText+"\"");
        ActionHistory.sharedInstance().addEvent(event);
    }

    private void handleQuickResponseResult(Bundle result) {
        String resultCode = result.getString(APlayExtender.ACTION_QUICK_RESPONSE);
        Log.i("APlayKit", "QuickResponseResult:"+resultCode);
        ActionEvent event;
        if (QUICK_RESPONSE_POSITIVE.equals(resultCode)) {
            // Do your positive action
            event = new ActionEvent(ActionEvent.Type.QUICK, "Quick ↑");
        } else if (QUICK_RESPONSE_NEGATIVE.equals(resultCode)) {
            // Do your negative action
            event = new ActionEvent(ActionEvent.Type.VOICE, "Quick ↓");
        } else {
            return;
        }
        ActionHistory.sharedInstance().addEvent(event);
    }

}
