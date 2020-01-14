package streamerchat.websockets;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/auth/")
public class AuthEndpoint extends Endpoint {

    private static final WSContext WS_CONTEXT = new AuthWSContext();

    @OnOpen @Override
    public void onOpen(Session session) {
        System.out.println("A user has connected to the Authentication server.");
        super.onOpen(session);
        WS_CONTEXT.connectUser(session.getId());
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        super.onMessage(message, session, WS_CONTEXT);
    }

    @OnClose @Override
    public void onClose(CloseReason reason, Session session) {
        super.onClose(reason, session);
        WS_CONTEXT.disconnectUser(session.getId());
    }

    @OnError @Override
    public void onError(Throwable error, Session session) {
        super.onError(error, session);
    }
}
