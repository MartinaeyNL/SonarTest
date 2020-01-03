package streamerchat.main;

import streamerchat.messagetypes.WSMessageType;

public class WSMessage {

    // Variables
    public WSMessageType messageType;
    public Object object;

    // Constructor
    public WSMessage(WSMessageType messageType, Object data) {
        this.messageType = messageType;
        this.object = data;
    }
}
