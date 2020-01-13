package streamerchat.websockets;

import com.google.gson.Gson;
import streamerchat.messagetypes.WSMessageType;

import javax.websocket.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public abstract class Endpoint {

    // Variables
    private WSContext wsContext;
    private static Collection<Session> connectedSessions = new ArrayList<>();

    // Get&Setters
    public void setWsContext(WSContext context) { this.wsContext = context; }


    /*-------------------------------------------------*/

    // WEBSOCKET BASE METHODS

    public void onOpen(Session session) {
        connectedSessions.add(session);
        wsContext.connectUser(session.getId());
    }

    public void onMessage(String message, Session session) {
        // Creating the WSMessage from the JSON String
        WSMessage wsMessage = new Gson().fromJson(message, WSMessage.class);
        System.out.println("Received message with type [" + wsMessage.messageType.name() + "] and the object was [" + wsMessage.object + "]");

        // Start Strategy pattern
        Collection<WSMessage> toSend = wsContext.start(wsMessage.messageType, wsMessage.object, session.getId());

        // Send new messages depending on the result
        this.sendMessages(toSend);
    }

    public void onClose(CloseReason reason, Session session) {
        System.out.println("A user closed the connection due to [" + reason + "]");
        connectedSessions.remove(session);
        wsContext.disconnectUser(session.getId());
    }

    public void onError(Throwable error, Session session) {
        System.out.println("Foutje moet kunnen toch? [" + error.getMessage() + "]");
    }


    /*---------------------------------------------------------------*/

    // PUBLIC METHODS

    public void executeMessage(WSMessageType type, Object parameter, Session session) {
        Collection<WSMessage> result = this.wsContext.start(type, parameter, session.getId());
        this.sendMessages(result);
    }


    /*---------------------------------------------*/

    // PRIVATE METHODS

    private void sendMessages(Collection<WSMessage> messages) {
        for(WSMessage item : messages) {
            Session collected = this.getSessionById(item.receiver_SessionId);
            if (collected != null) {
                this.sendFinalMessage(collected, item);
            }
        }
    }

    private void sendFinalMessage(Session session, WSMessage wsMessage) {

        // Converting it to JSON
        String message = new Gson().toJson(wsMessage);

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
