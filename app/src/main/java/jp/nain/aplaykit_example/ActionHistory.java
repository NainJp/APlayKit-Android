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

import java.util.ArrayList;

/**
 * Created by ytakano-Nain on 2017/04/03.
 */
public class ActionHistory {

    public interface UpdateListener {
        void onUpdate(ActionEvent item);
    }

    private ArrayList<ActionEvent> events;
    private UpdateListener listener;
    private static ActionHistory instance;

    public static ActionHistory sharedInstance() {
        if (instance == null) {
            instance = new ActionHistory();
        }
        return instance;
    }

    private ActionHistory() {
        this.events = new ArrayList<>();
    }

    public void setListener(UpdateListener listener) {
        this.listener = listener;
    }

    public void addEvent(ActionEvent event) {
        events.add(event);
        if (listener != null) {
            listener.onUpdate(event);
        }
    }

    public ArrayList<ActionEvent> getEvents() {
        return events;
    }

    public ArrayList<String> getEventContexts() {
        ArrayList<String> contexts = new ArrayList<>();
        for(ActionEvent event : events) {
            contexts.add(event.context);
        }
        return contexts;
    }
}
