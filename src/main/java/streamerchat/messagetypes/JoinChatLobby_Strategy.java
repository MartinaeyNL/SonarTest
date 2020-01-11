package streamerchat.messagetypes;

import streamerchat.main.WSMessage;
import streamerchat.models.ChatLobby;
import streamerchat.models.Controller;
import streamerchat.models.User;

import java.util.ArrayList;
import java.util.Collection;

public class JoinChatLobby_Strategy implements WSMessageTypeStrategy {

    @Override
    public Collection<WSMessage> start(Object parameter, User user, Controller controller) throws Exception {

        Collection<WSMessage> toReturn = new ArrayList<>();

        // Parameter checking
        if(parameter instanceof String) {
            String lobbyName = (String) parameter;
            controller.addUserToLobby(lobbyName, user);
            for(User u : controller.getAllUsers()) {
                toReturn.add(new WSMessage(u.getSessionId(), WSMessageType.getAllChatLobbies, controller.getAllLobbies()));
            }
            return toReturn;
        }
        else { throw new IllegalArgumentException("The lobby you put in is not a string!"); }

        //System.out.println("I've arrived at the right strategy stuff meuk ding hahahahahahaahahahahah pop");
        //return null;
    }
}
