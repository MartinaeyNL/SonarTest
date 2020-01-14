package streamerchat.messages;

public class WSMessage {

    // Variables
    private String receiverSessionId;
    private WSMessageType messageType;
    private Object object;

    // Constructor
    public WSMessage(String receiverSessionId, WSMessageType messageType, Object data) {
        this.receiverSessionId = receiverSessionId;
        this.messageType = messageType;
        this.object = data;
    }

    public String getSessionId() { return this.receiverSessionId; }
    public WSMessageType getMessageType() { return this.messageType; }
    public Object getObject() { return this.object; }
}
