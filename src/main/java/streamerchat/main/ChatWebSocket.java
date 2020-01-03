package streamerchat.main;

import com.google.gson.Gson;
import streamerchat.messagetypes.WSMessageType;
import streamerchat.models.Controller;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.*;

@ServerEndpoint(value = "/streamerchat/")
public class ChatWebSocket {

    // Variables
    private Collection<Session> connectedSessions; // List of sessions
    private Controller controller;
    private WSContext wsContext;

    // Constructor
    public ChatWebSocket() {
        this.connectedSessions = new ArrayList<>();
        this.controller = new Controller();
        this.wsContext = new WSContext();
    }

    /*-------------------------------------------------*/

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("A user has connected.");
        this.connectedSessions.add(session);
        this.controller.addConnectedUser(session.getId());
        this.sendFinalMessage(session, new WSMessage(WSMessageType.getAllChatLobbies, this.controller.getAllLobbies()));
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        //System.out.println("Received the message: " + message);
        Gson converter = new Gson();
        WSMessage wsMessage = converter.fromJson(message, WSMessage.class);
        WSContext.start(wsMessage.messageType, wsMessage.object, this.controller);
        System.out.println("Received the websocket message: " + wsMessage);
        System.out.println("The messageType is [" + wsMessage.messageType.name() + "] and the object was [" + wsMessage.object + "]");
    }

    @OnClose
    public void onClose(CloseReason reason, Session session) {
        System.out.println("A user closed the connection due to [" + reason + "]");
        this.connectedSessions.remove(session);
    }

    @OnError
    public void onError(Throwable error, Session session) {
        System.out.println("Foutje moet kunnen toch?");
    }

    /*---------------------------------------------------------------*/

    // the method where other classes can send messages from

    // public void sendMessage(String userId, String messageType, Object o) {
    //    boolean test = false;
    //    for(Session s : this.connectedSessions) {
    //        if(s.getId().equals(userId)) {
    //            this.sendFinalMessage(s, new WSMessage(messageType, o));
    //            test = true;
    //        }
    //    }
    //    if(!test) { System.out.println("Couldn't find session! Oof."); }
    //}


    // sendMessage method
    private void sendFinalMessage(Session session, WSMessage wsMessage) {

        // Converting it to JSON
        Gson converter = new Gson();
        String message = converter.toJson(wsMessage);

        // Sending the actual object
        try { session.getBasicRemote().sendObject(message); }
        catch (EncodeException ee) {
            try { session.getBasicRemote().sendText("Something went wrong with sending the lobby object! :("); }
            catch (Exception ex) { ex.printStackTrace(); } // Temporary !!!
        }
        catch (IOException io_e) {
            io_e.printStackTrace(); // Temporary !!!
        }
    }
}
