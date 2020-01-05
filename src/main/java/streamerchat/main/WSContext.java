package streamerchat.main;

import streamerchat.messagetypes.*;
import streamerchat.models.Controller;
import streamerchat.models.User;

import java.util.HashMap;
import java.util.Map;

public class WSContext {

    // Variables
    private Controller controller;
    private Map<WSMessageType, WSMessageTypeStrategy> strategies;

    // Constructor
    public WSContext() {
        controller = new Controller();
        strategies = new HashMap<>();
        strategies.put(WSMessageType.joinChatLobby, new JoinChatLobby_Strategy());
        strategies.put(WSMessageType.leaveChatLobby, new LeaveChatLobby_Strategy());
        strategies.put(WSMessageType.getAllChatLobbies, new GetAllChatLobbies_Strategy());
    }

    /*--------------------------------------------------------------------------*/

    // Main method for starting procedure
    public WSMessage start(WSMessageType type, Object parameter, String sessionId) {
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

    /*-----------------------------------------------------------------*/

    // User Connections
    public void addConnectedUser(String sessionId) {
        controller.addConnectedUser(sessionId);
    }

    public void removeConnectedUser(String sessionId) {
        controller.removeConnectedUser(sessionId);
    }
}
