package streamerchat.messagetypes;

import streamerchat.models.ChatLobby;
import streamerchat.models.Controller;
import streamerchat.models.User;

import java.util.Collection;

public class JoinChatLobby_Strategy implements WSMessageTypeStrategy {

    @Override
    public Object start(Object parameter, User user, Controller controller) throws Exception {

        // Parameter checking
        if(parameter instanceof String) {
            String lobbyName = (String) parameter;
            controller.addUserToLobby(lobbyName, user);
        }
        else { return new IllegalArgumentException("The lobby you put in is not a string!"); }

        //System.out.println("I've arrived at the right strategy stuff meuk ding hahahahahahaahahahahah pop");
        return null;
    }
}
