package streamerchat.websockets;

import streamerchat.messagetypes.WSMessageType;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.Collection;

@ServerEndpoint(value = "/auth/")
public class AuthEndpoint extends Endpoint {

    @OnOpen @Override
    public void onOpen(Session session) {
        System.out.println("A user has connected to the Authentication server.");
        super.setWsContext(new AuthWSContext());
        super.onOpen(session);
    }

    @OnMessage @Override
    public void onMessage(String message, Session session) {
        super.onMessage(message, session);
    }

    @OnClose @Override
    public void onClose(CloseReason reason, Session session) {
        super.onClose(reason, session);
    }

    @OnError @Override
    public void onError(Throwable error, Session session) {
        super.onError(error, session);
    }
}
