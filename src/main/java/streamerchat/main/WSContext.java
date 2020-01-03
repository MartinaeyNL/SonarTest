package streamerchat.main;

import streamerchat.messagetypes.GetAllChatLobbies_Strategy;
import streamerchat.messagetypes.JoinChatLobby_Strategy;
import streamerchat.messagetypes.WSMessageType;
import streamerchat.messagetypes.WSMessageTypeStrategy;
import streamerchat.models.Controller;

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

    public static Object start(WSMessageType type, Object parameter, Controller controller) {
        WSMessageTypeStrategy strategy = strategies.get(type);
        Object toReturn = strategy.start(parameter, controller);
        System.out.println("The object that the strategy pattern returned was: [" + toReturn + "]");
        return toReturn;
    }
}
