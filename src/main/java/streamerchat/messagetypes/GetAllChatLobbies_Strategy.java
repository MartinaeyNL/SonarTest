package streamerchat.messagetypes;

import streamerchat.websockets.WSMessage;
import streamerchat.models.Controller;
import streamerchat.models.Session;
import streamerchat.websockets.WSMessageConverter;

import java.util.ArrayList;
import java.util.Collection;

public class GetAllChatLobbies_Strategy implements WSMessageTypeStrategy {

    @Override
    public Collection<WSMessage> start(Object parameter, Session session, Controller controller) {
        Collection<Object> toSend = new ArrayList<>();
        toSend.add(controller.getAllLobbies());
        return new WSMessageConverter().toWSMessages(WSMessageType.GET_ALL_CHAT_LOBBIES, session.getSessionId(), toSend);
        //Collection<WSMessage> toReturn = new ArrayList<>();
        //toReturn.add(new WSMessage(session.getSessionId(), WSMessageType.GET_ALL_CHAT_LOBBIES, controller.getAllLobbies()));
        //return toReturn;
    }
}
