package streamerchat.messagetypes;

import streamerchat.websockets.WSMessage;
import streamerchat.models.Controller;
import streamerchat.models.Session;
import streamerchat.websockets.WSMessageConverter;

import java.util.ArrayList;
import java.util.Collection;

public class LeaveChatLobby_Strategy implements WSMessageTypeStrategy {

    @Override
    public Collection<WSMessage> start(Object parameter, Session session, Controller controller) {

        Collection<Object> toSend = new ArrayList<>();     // Creating list of objects
        toSend.add(controller.getAllLobbies());            // Adding the lobby as ONE object of it

        // Parameter checking
        if(parameter instanceof String) {
            Collection<String> sessionIds = new ArrayList<>();
            controller.removeUserFromLobby((String) parameter, session);
            for(Session u : controller.getAllUsers()) {
                sessionIds.add(u.getSessionId());
            }
            return new WSMessageConverter().toWSMessages(WSMessageType.GET_ALL_CHAT_LOBBIES, sessionIds, toSend);
        }
        else { throw new IllegalArgumentException("The lobby you put in is not a string!"); }
    }
}
