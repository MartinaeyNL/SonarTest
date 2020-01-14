package streamerchat.websockets;

import streamerchat.messagetypes.WSMessageType;

import java.util.ArrayList;
import java.util.Collection;

public class WSMessageConverter {

    public Collection<WSMessage> toWSMessages(WSMessageType type, Collection<String> sessionIds, Collection<Object> objects) {
        Collection<WSMessage> toReturn = new ArrayList<>();
        for(String id : sessionIds) {
            for(Object o : objects) {
                toReturn.add(new WSMessage(id, type, o));
            }
        }
        return toReturn;
    }
}
