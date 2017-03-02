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

    public String ACTION_VOICE_RECOGNITION = "jp.nain.aplaykit_example.ACTION_VOICE_RECOGNITION";
    public String ACTION_QUICK_RESPONSE = "jp.nain.aplaykit_example.ACTION_QUICK_RESPONSE";

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

        }
    }

    private void handleVoiceRecognitionResult(Bundle result) {
        String resultText = result.getString(APlayExtender.ACTION_VOICE_RECOGNITION);
        Log.i("APlayKit", "VoiceRecognitionResult: "+resultText);
    }

    private void handleQuickResponseResult(Bundle result) {
        String resultCode = result.getString(APlayExtender.ACTION_QUICK_RESPONSE);
        Log.i("APlayKit", "QuickResponseResult:"+resultCode);
        if (QUICK_RESPONSE_POSITIVE.equals(resultCode)) {
            // Do positive action
        } else if (QUICK_RESPONSE_NEGATIVE.equals(resultCode)) {
            // Do negative action
        } else {

        }
    }

}
