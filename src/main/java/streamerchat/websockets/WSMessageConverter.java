package streamerchat.websockets;

import streamerchat.messagetypes.WSMessageType;
import streamerchat.websockets.WSMessage;

import java.util.ArrayList;
import java.util.Collection;

public class WSMessageConverter {

    public Collection<WSMessage> toWSMessages(WSMessageType type, String sessionId, Collection<Object> objects) {
        Collection<WSMessage> toReturn = new ArrayList<>();
        for(Object o : objects) {
            toReturn.add(new WSMessage(sessionId, type, o));
        }
        return toReturn;
    }
}
