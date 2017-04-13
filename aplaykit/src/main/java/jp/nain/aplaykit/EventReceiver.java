/*
 * Copyright (c) 2017 Nain Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
