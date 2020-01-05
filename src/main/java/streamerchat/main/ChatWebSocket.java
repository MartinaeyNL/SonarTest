package streamerchat.main;

import com.google.gson.Gson;
import streamerchat.messagetypes.WSMessageType;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@ServerEndpoint(value = "/streamerchat/")
public class ChatWebSocket {

    // Variables
    private static WSContext wsContext = new WSContext();

    // Constructor
    //public ChatWebSocket() {
    //    this.wsContext = new WSContext();
    //}

    /*-------------------------------------------------*/

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("A user has connected.");
        wsContext.connectUser(session.getId());
        WSMessage toSend = wsContext.start(WSMessageType.getAllChatLobbies, null, session.getId());
        this.sendFinalMessage(session, toSend);
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        //System.out.println("Received the message: " + message);
        Gson converter = new Gson();
        WSMessage wsMessage = converter.fromJson(message, WSMessage.class);
        System.out.println("Received message with type [" + wsMessage.messageType.name() + "] and the object was [" + wsMessage.object + "]");

        WSMessage toSend = wsContext.start(wsMessage.messageType, wsMessage.object, session.getId());
        if(toSend != null) {
            this.sendFinalMessage(session, toSend);
        }
    }

    @OnClose
    public void onClose(CloseReason reason, Session session) {
        System.out.println("A user closed the connection due to [" + reason + "]");
        wsContext.disconnectUser(session.getId());
    }

    @OnError
    public void onError(Throwable error, Session session) {
        System.out.println("Foutje moet kunnen toch?");
    }

    /*---------------------------------------------------------------*/

    // sendMessage method
    public void sendFinalMessage(Session session, WSMessage wsMessage) {

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
