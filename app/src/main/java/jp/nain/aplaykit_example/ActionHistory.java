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
