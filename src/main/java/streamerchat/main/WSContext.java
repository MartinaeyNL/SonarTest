package streamerchat.main;

import streamerchat.messagetypes.*;
import streamerchat.models.Controller;
import streamerchat.models.User;

import java.util.HashMap;
import java.util.Map;

public class WSContext {

    // Variables
    private static Map<WSMessageType, WSMessageTypeStrategy> strategies;

    // Constructor
    public WSContext() {
        strategies = new HashMap<>();
        strategies.put(WSMessageType.joinChatLobby, new JoinChatLobby_Strategy());
        strategies.put(WSMessageType.leaveChatLobby, new LeaveChatLobby_Strategy());
        strategies.put(WSMessageType.getAllChatLobbies, new GetAllChatLobbies_Strategy());
    }

    public static WSMessage start(WSMessageType type, Object parameter, String sessionId, Controller controller) {
        WSMessageTypeStrategy strategy = strategies.get(type);
        User user = controller.getConnectedUser(sessionId);
        Object toReturn = strategy.start(parameter, user, controller);
        //System.out.println("The object that the strategy pattern returned was: [" + toReturn + "]");
        if(toReturn instanceof Exception) {
            type = WSMessageType.error;
            toReturn = ((Exception) toReturn).getMessage();
            System.out.println("There was a freaking error! Wow! This is him: [" + toReturn + "]");
        }
        if(type != null && toReturn != null) {
            return new WSMessage(type, toReturn);
        }
        return null;
    }
}
