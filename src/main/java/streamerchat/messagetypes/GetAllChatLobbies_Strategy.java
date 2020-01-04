package streamerchat.messagetypes;

import streamerchat.models.Controller;
import streamerchat.models.User;

public class GetAllChatLobbies_Strategy implements WSMessageTypeStrategy {

    @Override
    public Object start(Object parameter, User user, Controller controller) {
        System.out.println("Getting all lobbies..");
        return controller.getAllLobbies();
    }
}
