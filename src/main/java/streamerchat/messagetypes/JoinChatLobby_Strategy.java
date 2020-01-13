package streamerchat.messagetypes;

import streamerchat.websockets.WSMessage;
import streamerchat.models.Controller;
import streamerchat.models.Session;

import java.util.ArrayList;
import java.util.Collection;

public class JoinChatLobby_Strategy implements WSMessageTypeStrategy {

    @Override
    public Collection<WSMessage> start(Object parameter, Session session, Controller controller) throws Exception {

        Collection<WSMessage> toReturn = new ArrayList<>();

        // Parameter checking
        if(parameter instanceof String) {
            String lobbyName = (String) parameter;
            controller.addUserToLobby(lobbyName, session);
            for(Session u : controller.getAllUsers()) {
                toReturn.add(new WSMessage(u.getSessionId(), WSMessageType.GET_ALL_CHAT_LOBBIES, controller.getAllLobbies()));
            }
            return toReturn;
        }
        else { throw new IllegalArgumentException("The lobby you put in is not a string!"); }

        //System.out.println("I've arrived at the right strategy stuff meuk ding hahahahahahaahahahahah pop");
        //return null;
    }
}
