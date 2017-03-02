package jp.nain.aplaykit;

import android.app.PendingIntent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.RemoteInput;

/**
 * Created by ytakano-Nain on 2017/02/27.
 */
public class APlayExtender implements NotificationCompat.Extender {

    private static final String PREFIX = "jp.nain.aplay.";

    public static final String KEY_MUTE_APP_NAME = PREFIX + "muteAppName";
    public static final String KEY_PRIOR_TICKER = PREFIX + "priorTicker";
    public static final String KEY_GUIDANCE_START = PREFIX + "guidanceStart";
    public static final String KEY_GUIDANCE_CONFIRM = PREFIX + "guidanceConfirm";
    public static final String KEY_GUIDANCE_SUCCESS = PREFIX + "guidanceSuccess";
    public static final String KEY_GUIDANCE_FAILURE = PREFIX + "guidanceFailure";

    public static final String ACTION_VOICE_RECOGNITION = PREFIX + "ACTION_VOICE_RECOGNITION";
    public static final String ACTION_QUICK_RESPONSE = PREFIX + "ACTION_QUICK_RESPONSE";

    public static final String QUICK_RESPONSE_POSITIVE = "QUICK_RESPONSE_POSITIVE";
    public static final String QUICK_RESPONSE_NEGATIVE = "QUICK_RESPONSE_NEGATIVE";

    private static final String EXTRA_APLAY_EXTENSIONS = PREFIX + "EXTENSIONS";
    private static final String EXTRA_APLAY_ACTIONS = PREFIX + "ACTIONS";

    private Boolean muteAppName;
    private Boolean priorTicker;
    private String guidanceStart;
    private String guidanceConfirm;
    private String guidanceSuccess;
    private String guidanceFailure;
    private PendingIntent voiceRecognitionActionIntent;
    private PendingIntent quickResponseActionIntent;

    private NotificationCompat.Builder builder;

    public APlayExtender() {}

    @Override
    public NotificationCompat.Builder extend(NotificationCompat.Builder builder) {

        this.builder = builder;

        // Add Bundle Extensions
        addExtraBundle();

        // Add Actions
        addExtraActions();

        return this.builder;
    }

    @Override
    public APlayExtender clone() {
        APlayExtender that = new APlayExtender();
        return that;
    }

    public APlayExtender setMuteAppName(boolean muteAppName) {
        this.muteAppName = muteAppName;
        return this;
    }

    public APlayExtender setPriorTicker(boolean priorTicker) {
        this.priorTicker = priorTicker;
        return this;
    }

    public APlayExtender setGuidanceStart(String guidanceStart) {
        this.guidanceStart = guidanceStart;
        return this;
    }

    public APlayExtender setGuidanceConfirm(String guidanceConfirm) {
        this.guidanceConfirm = guidanceConfirm;
        return this;
    }

    public APlayExtender setGuidanceSuccess(String guidanceSuccess) {
        this.guidanceSuccess = guidanceSuccess;
        return this;
    }

    public APlayExtender setGuidanceFailure(String guidanceFailure) {
        this.guidanceFailure = guidanceFailure;
        return this;
    }

    public APlayExtender addVoiceRecognitionAction(PendingIntent intent) {
        this.voiceRecognitionActionIntent = intent;
        return this;
    }

    public APlayExtender addQuickResponseAction(PendingIntent intent) {
        this.quickResponseActionIntent = intent;
        return this;
    }

    private void addExtraBundle() {
        Bundle bundle = new Bundle();
        if (muteAppName != null) {
            bundle.putBoolean(KEY_MUTE_APP_NAME, muteAppName);
        }
        if (priorTicker != null) {
            bundle.putBoolean(KEY_PRIOR_TICKER, priorTicker);
        }
        if (guidanceStart != null) {
            bundle.putString(KEY_GUIDANCE_START, guidanceStart);
        }
        if (guidanceConfirm != null) {
            bundle.putString(KEY_GUIDANCE_CONFIRM, guidanceConfirm);
        }
        if (guidanceSuccess != null) {
            bundle.putString(KEY_GUIDANCE_SUCCESS, guidanceSuccess);
        }
        if (guidanceFailure != null) {
            bundle.putString(KEY_GUIDANCE_FAILURE, guidanceFailure);
        }
        builder.getExtras().putBundle(EXTRA_APLAY_EXTENSIONS, bundle);
    }

    private void addExtraActions() {

        NotificationCompat.WearableExtender extender = new NotificationCompat.WearableExtender();

        // VoiceRecognition action
        if (voiceRecognitionActionIntent != null) {
            RemoteInput remoteInput = new RemoteInput.Builder(ACTION_VOICE_RECOGNITION)
                    .build();
            Bundle bundle = new Bundle();
            bundle.putString(EXTRA_APLAY_ACTIONS, ACTION_VOICE_RECOGNITION);
            NotificationCompat.Action action = new NotificationCompat.Action.Builder(0, "Voice", voiceRecognitionActionIntent)
                    .addRemoteInput(remoteInput)
                    .addExtras(bundle)
                    .build();
            extender.addAction(action);
        }

        // Quick Response action
        if (quickResponseActionIntent != null) {
            RemoteInput remoteInput = new RemoteInput.Builder(ACTION_QUICK_RESPONSE)
                    .build();
            Bundle bundle = new Bundle();
            bundle.putString(EXTRA_APLAY_ACTIONS, ACTION_QUICK_RESPONSE);
            NotificationCompat.Action action = new NotificationCompat.Action.Builder(0, "Quick", quickResponseActionIntent)
                    .addRemoteInput(remoteInput)
                    .addExtras(bundle)
                    .build();
            extender.addAction(action);
        }

        builder.extend(extender);
    }

}
