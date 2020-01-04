package streamerchat.main;

import streamerchat.messagetypes.GetAllChatLobbies_Strategy;
import streamerchat.messagetypes.JoinChatLobby_Strategy;
import streamerchat.messagetypes.WSMessageType;
import streamerchat.messagetypes.WSMessageTypeStrategy;
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
        strategies.put(WSMessageType.getAllChatLobbies, new GetAllChatLobbies_Strategy());
    }

    public static WSMessage start(WSMessageType type, Object parameter, User user, Controller controller) {
        WSMessageTypeStrategy strategy = strategies.get(type);
        Object toReturn = strategy.start(parameter, user, controller);
        //System.out.println("The object that the strategy pattern returned was: [" + toReturn + "]");
        if(type != null && toReturn != null) {
            return new WSMessage(type, toReturn);
        }
        return null;
    }
}
