package streamerchat.websockets;

import streamerchat.messagetypes.*;
import streamerchat.models.Controller;
import streamerchat.models.User;

import java.util.ArrayList;
import java.util.Collection;
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
        strategies.put(WSMessageType.authenticate, new Authenticate_Strategy());
        strategies.put(WSMessageType.joinChatLobby, new JoinChatLobby_Strategy());
        strategies.put(WSMessageType.leaveChatLobby, new LeaveChatLobby_Strategy());
        strategies.put(WSMessageType.getAllChatLobbies, new GetAllChatLobbies_Strategy());
    }

    /*--------------------------------------------------------------------------*/

    // Main method for starting procedure
    public Collection<WSMessage> start(WSMessageType type, Object parameter, String sessionId) {
        WSMessageTypeStrategy strategy = strategies.get(type);
        User user = controller.getConnectedUser(sessionId);
        Collection<WSMessage> toReturn = new ArrayList<>();

        // Start
        try { toReturn = strategy.start(parameter, user, controller); }
        catch (Exception e) {
            toReturn.add(new WSMessage(user.getSessionId(), WSMessageType.error, e.getMessage()));
            System.out.println("There was a freaking error! Wow! This is him: [" + e.getMessage() + "]");
        }

        if(toReturn.size() > 0) {
            return toReturn;
        }
        return null;
    }

    /*-----------------------------------------------------------------*/

    // User Connections
    public void connectUser(String sessionId) {
        controller.addConnectedUser(sessionId);
    }

    public void disconnectUser(String sessionId) {
        controller.disconnectUser(sessionId);
    }
}
