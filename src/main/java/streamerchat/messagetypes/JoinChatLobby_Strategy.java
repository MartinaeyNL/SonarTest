package streamerchat.messagetypes;

import streamerchat.models.ChatLobby;
import streamerchat.models.Controller;
import streamerchat.models.User;

import java.util.Collection;

public class JoinChatLobby_Strategy implements WSMessageTypeStrategy {

    @Override
    public Object start(Object parameter, User user, Controller controller) {

        // Parameter checking
        if(parameter instanceof String) {
            String lobbyName = (String) parameter;

            // All other checks
            ChatLobby lobby = controller.getChatLobby(lobbyName);
            if(lobby != null) {
                Collection<User> users = lobby.getUsers();
                if (users.size() > 0) {
                    for (User loopUser : users) {
                        if (loopUser.getSessionId().equals(user.getSessionId())) {
                            System.out.println("You've already joined this lobby!");
                            return new Exception("You've already joined this lobby!");
                        }
                    }
                    controller.addUserToLobby(lobbyName, user);
                    System.out.println("Joining the chatlobby " + parameter);
                }
                else {
                    controller.addUserToLobby(lobbyName, user);
                    System.out.println("Joining the chatlobby " + parameter);
                }
            }
            else {
                System.out.println("The lobby you wanted to join doesn't exist!");
                return new IllegalArgumentException("The lobby you wanted to join doesn't exist!");
            }
        }
        else { return new IllegalArgumentException("The lobby you put in is not a string!"); }

        //System.out.println("I've arrived at the right strategy stuff meuk ding hahahahahahaahahahahah pop");
        return null;
    }
}
