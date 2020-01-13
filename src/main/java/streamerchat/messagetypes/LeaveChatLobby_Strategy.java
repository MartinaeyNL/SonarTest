package streamerchat.messagetypes;

import streamerchat.websockets.WSMessage;
import streamerchat.models.Controller;
import streamerchat.models.Session;

import java.util.ArrayList;
import java.util.Collection;

public class LeaveChatLobby_Strategy implements WSMessageTypeStrategy {

    @Override
    public Collection<WSMessage> start(Object parameter, Session session, Controller controller) {

        Collection<WSMessage> toReturn = new ArrayList<>();

        // Parameter checking
        if(parameter instanceof String) {
            String lobbyName = (String) parameter;
            controller.removeUserFromLobby(lobbyName, session);
            for(Session u : controller.getAllUsers()) {
                toReturn.add(new WSMessage(u.getSessionId(), WSMessageType.getAllChatLobbies, controller.getAllLobbies()));
            }
            return toReturn;
        }
        else { throw new IllegalArgumentException("The lobby you put in is not a string!"); }
    }
}
