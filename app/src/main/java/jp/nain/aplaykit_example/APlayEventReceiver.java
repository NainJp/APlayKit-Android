package jp.nain.aplaykit_example;

import android.content.Context;
import android.util.Log;

import jp.nain.aplaykit.EventReceiver;

/**
 * Created by ytakano-Nain on 2017/02/27.
 */
public class APlayEventReceiver extends EventReceiver {

    @Override
    protected void onConnected(Context context){
        Log.i("APlayKit", "onConnected");
        ActionEvent event = new ActionEvent(ActionEvent.Type.CONNECT, "Connected");
        ActionHistory.sharedInstance().addEvent(event);
    }

    @Override
    protected void onDisconnected(Context context){
        Log.i("APlayKit", "onDisconnected");
        ActionEvent event = new ActionEvent(ActionEvent.Type.DISCONNECT, "Disconnected");
        ActionHistory.sharedInstance().addEvent(event);
    }

    @Override
    protected void onCalled(Context context){
        Log.i("APlayKit", "onCalled");
        ActionEvent event = new ActionEvent(ActionEvent.Type.CALL, "Called");
        ActionHistory.sharedInstance().addEvent(event);

        // Do custom launch action
    }
}
