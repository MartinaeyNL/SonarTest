package toolboard.main;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.*;

@ServerEndpoint(value = "/toolboard/")
public class Websocket {

    //private Session session;
    private static Collection<Websocket> ENDPOINTS = new ArrayList<>();
    private static HashSet<Session> sessions = new HashSet<>();

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("Connection ready.");
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("Received the message: " + message);
    }

    @OnClose
    public void onClose(CloseReason reason, Session session) {
        System.out.println("Closed the connection due to [" + reason + "]");
    }

    @OnError
    public void onError(Throwable error, Session session) {
        System.out.println("Foutje moet kunnen toch?");
    }
}
