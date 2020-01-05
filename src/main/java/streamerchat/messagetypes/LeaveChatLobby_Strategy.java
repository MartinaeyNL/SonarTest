package streamerchat.messagetypes;

import streamerchat.models.ChatLobby;
import streamerchat.models.Controller;
import streamerchat.models.User;

public class LeaveChatLobby_Strategy implements WSMessageTypeStrategy {

    @Override
    public Object start(Object parameter, User user, Controller controller) {

        // Parameter checking
        if(parameter instanceof String) {
            String lobbyName = (String) parameter;
            controller.removeUserFromLobby(lobbyName, user);
            System.out.println("User #" + user.getSessionId() + " left Lobby " + lobbyName);
        }
        else { return new IllegalArgumentException("The lobby you put in is not a string!"); }

        return null;
    }
}
