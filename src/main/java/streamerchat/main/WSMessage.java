package streamerchat.main;

import streamerchat.messagetypes.WSMessageType;

public class WSMessage {

    // Variables
    public String receiver_SessionId;
    public WSMessageType messageType;
    public Object object;

    // Constructor
    public WSMessage(String receiver_SessionId, WSMessageType messageType, Object data) {
        this.receiver_SessionId = receiver_SessionId;
        this.messageType = messageType;
        this.object = data;
    }
}
