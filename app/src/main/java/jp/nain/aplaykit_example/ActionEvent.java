package jp.nain.aplaykit_example;

/**
 * Created by ytakano-Nain on 2017/04/03.
 */
public class ActionEvent {

    public enum Type {
        VOICE,
        QUICK,
        CALL,
        CONNECT,
        DISCONNECT
    }

    public Type type;
    public String context;

    public ActionEvent(Type type, String context) {
        this.type = type;
        this.context = context;
    }

}
