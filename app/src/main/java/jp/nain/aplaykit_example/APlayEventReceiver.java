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
    }

    @Override
    protected void onDisconnected(Context context){
        Log.i("APlayKit", "onDisconnected");
    }

    @Override
    protected void onCalled(Context context){
        Log.i("APlayKit", "onCalled");
    }
}
