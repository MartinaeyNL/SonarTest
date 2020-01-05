package streamerchat.messagetypes;

import streamerchat.main.WSMessage;
import streamerchat.models.Controller;
import streamerchat.models.User;

import java.util.ArrayList;
import java.util.Collection;

public class GetAllChatLobbies_Strategy implements WSMessageTypeStrategy {

    @Override
    public Collection<WSMessage> start(Object parameter, User user, Controller controller) {
        Collection<WSMessage> toReturn = new ArrayList<>();
        toReturn.add(new WSMessage(user.getSessionId(), WSMessageType.getAllChatLobbies, controller.getAllLobbies()));
        return toReturn;
    }
}
