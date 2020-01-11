package streamerchat.websockets;

import com.google.gson.Gson;
import streamerchat.messagetypes.WSMessageType;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@ServerEndpoint(value = "/auth/")
public class AuthEndpoint {

    // Variables
    private static WSContext wsContext = new WSContext();
    private static Collection<Session> connectedSessions = new ArrayList<>();

    // Constructor
    //public ChatWebSocket() {
    //    this.wsContext = new WSContext();
    //}

    /*-------------------------------------------------*/

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("A user has connected to the authentication server.");
        connectedSessions.add(session);
        wsContext.connectUser(session.getId());
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        //System.out.println("Received the message: " + message);
        Gson converter = new Gson();
        WSMessage wsMessage = converter.fromJson(message, WSMessage.class);
        System.out.println("Received message with type [" + wsMessage.messageType.name() + "] and the object was [" + wsMessage.object + "]");

        Collection<WSMessage> toSend = wsContext.start(wsMessage.messageType, wsMessage.object, session.getId());
        if(toSend != null) {
            for(WSMessage item : toSend) {
                Session collected = this.getSessionById(item.receiver_SessionId);
                if (collected != null) {
                    this.sendFinalMessage(collected, item);
                }
            }
        }
    }

    @OnClose
    public void onClose(CloseReason reason, Session session) {
        System.out.println("A user closed the connection due to [" + reason + "]");
        connectedSessions.remove(session);
        wsContext.disconnectUser(session.getId());
    }

    @OnError
    public void onError(Throwable error, Session session) {
        System.out.println("Foutje moet kunnen toch?");
    }

    /*---------------------------------------------------------------*/

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

    private Session getSessionById(String sessionId) {
        Optional<Session> session = connectedSessions.stream().filter(item -> item.getId().equals(sessionId)).findFirst();
        if(session.isPresent()) {
            return session.orElse(null);
        }
        return null;
    }
}
