package streamerchat.messagetypes;

import streamerchat.websockets.WSMessage;
import streamerchat.models.Controller;
import streamerchat.models.Session;

import java.util.ArrayList;
import java.util.Collection;

public class GetAllChatLobbies_Strategy implements WSMessageTypeStrategy {

    @Override
    public Collection<WSMessage> start(Object parameter, Session session, Controller controller) {
        Collection<WSMessage> toReturn = new ArrayList<>();
        toReturn.add(new WSMessage(session.getSessionId(), WSMessageType.GET_ALL_CHAT_LOBBIES, controller.getAllLobbies()));
        return toReturn;
    }
}
