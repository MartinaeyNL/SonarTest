package streamerchat.main;

import com.google.gson.Gson;
import streamerchat.messagetypes.WSMessageType;
import streamerchat.models.Controller;
import streamerchat.models.User;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.*;

@ServerEndpoint(value = "/streamerchat/")
public class ChatWebSocket {

    // Variables
    private WSContext wsContext;

    // Constructor
    public ChatWebSocket() {
        this.wsContext = new WSContext();
    }

    /*-------------------------------------------------*/

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("A user has connected.");
        WSContext.addConnectedUser(session.getId());
        WSMessage toSend = WSContext.start(WSMessageType.getAllChatLobbies, null, session.getId());
        this.sendFinalMessage(session, toSend);
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        //System.out.println("Received the message: " + message);
        Gson converter = new Gson();
        WSMessage wsMessage = converter.fromJson(message, WSMessage.class);
        System.out.println("Received message with type [" + wsMessage.messageType.name() + "] and the object was [" + wsMessage.object + "]");

        WSMessage toSend = WSContext.start(wsMessage.messageType, wsMessage.object, session.getId());
        if(toSend != null) {
            this.sendFinalMessage(session, toSend);
        }
    }

    @OnClose
    public void onClose(CloseReason reason, Session session) {
        System.out.println("A user closed the connection due to [" + reason + "]");
        WSContext.removeConnectedUser(session.getId());
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
        try {
            session.getBasicRemote().sendObject(message);
            System.out.println("Successfully sent message! It was " + message);
        }
        catch (EncodeException ee) {
            try { session.getBasicRemote().sendText("Something went wrong with sending the lobby object! :("); }
            catch (Exception ex) { ex.printStackTrace(); } // Temporary !!!
        }
        catch (IOException io_e) {
            io_e.printStackTrace(); // Temporary !!!
        }
    }
}
