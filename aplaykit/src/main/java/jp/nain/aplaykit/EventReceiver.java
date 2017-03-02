package jp.nain.aplaykit;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by ytakano-Nain on 2017/02/27.
 */
public abstract class EventReceiver extends BroadcastReceiver {

    private static final String PREFIX = "jp.nain.aplay.";

    public static final String KEY_CALL_EVENT = PREFIX + "ACTION_CALL_EVENT";
    public static final String KEY_CONNECTED_EVENT = PREFIX + "ACTION_CONNECTED_EVENT";
    public static final String KEY_DISCONNECTED_EVENT = PREFIX + "ACTION_DISCONNECTED_EVENT";

    @Override
    public final void onReceive(Context context, Intent intent) {
        final String action = intent.getAction();
        if (KEY_CALL_EVENT.equals(action)) {
            Log.i("APlayKit", "onReceived ACTION_CALL_EVENT");
            onCalled(context);
        } else if (KEY_CONNECTED_EVENT.equals(action)) {
            Log.i("APlayKit", "onReceived ACTION_CONNECTED_EVENT");
            onConnected(context);
        } else if (KEY_DISCONNECTED_EVENT.equals(action)) {
            Log.i("APlayKit", "onReceived ACTION_DISCONNECTED_EVENT");
            onDisconnected(context);
        } else {
            // nothing
        }
    }

    protected abstract void onConnected(Context context);

    protected abstract void onDisconnected(Context context);

    protected abstract void onCalled(Context context);
}
