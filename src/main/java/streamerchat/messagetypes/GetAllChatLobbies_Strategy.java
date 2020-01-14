package streamerchat.messagetypes;

import streamerchat.websockets.WSMessage;
import streamerchat.models.Controller;
import streamerchat.models.Session;
import streamerchat.websockets.WSMessageConverter;

import java.util.ArrayList;
import java.util.Collection;

public class GetAllChatLobbies_Strategy implements WSMessageTypeStrategy {

    @Override
    public Collection<WSMessage> start(Object parameter, Session session, Controller controller) {

        Collection<Object> toSend = new ArrayList<>();      // Creating list of objects
        toSend.add(controller.getAllLobbies());             // Adding the lobby as ONE object of it

        Collection<String> sessionIds = new ArrayList<>();
        sessionIds.add(session.getSessionId());

        // Parsing it to WSMessages
        return new WSMessageConverter().toWSMessages(WSMessageType.GET_ALL_CHAT_LOBBIES, sessionIds, toSend);
    }
}
