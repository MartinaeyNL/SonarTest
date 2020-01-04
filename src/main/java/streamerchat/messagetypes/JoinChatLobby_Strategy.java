package streamerchat.messagetypes;

import streamerchat.models.ChatLobby;
import streamerchat.models.Controller;
import streamerchat.models.User;

public class JoinChatLobby_Strategy implements WSMessageTypeStrategy {

    @Override
    public Object start(Object parameter, User user, Controller controller) {

        // Parameter checking
        if(parameter instanceof String) {
            String lobbyName = (String) parameter;

            // All other checks
            ChatLobby lobby = controller.getChatLobby(lobbyName);
            if(lobby != null) {
                controller.addUserToLobby(lobby, user);
                System.out.println("Joining the chatlobby " + parameter);
            }
        }

        //System.out.println("I've arrived at the right strategy stuff meuk ding hahahahahahaahahahahah pop");
        return null;
    }
}
