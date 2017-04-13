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
