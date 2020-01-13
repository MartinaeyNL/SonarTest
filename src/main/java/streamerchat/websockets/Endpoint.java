package streamerchat.websockets;

import com.google.gson.Gson;

import javax.websocket.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public abstract class Endpoint {

    // Variables
    public WSContext wsContext;
    private static Collection<Session> connectedSessions = new ArrayList<>();

    /*-------------------------------------------------*/

    public void onOpen(Session session) {
        connectedSessions.add(session);
        wsContext.connectUser(session.getId());
    }

    public void onMessage(String message, Session session) {
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

    public void onClose(CloseReason reason, Session session) {
        System.out.println("A user closed the connection due to [" + reason + "]");
        connectedSessions.remove(session);
        wsContext.disconnectUser(session.getId());
    }

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
            try { session.getBasicRemote().sendText("Something went wrong with sending the object! :("); }
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
