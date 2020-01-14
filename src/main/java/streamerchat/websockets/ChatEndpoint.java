package streamerchat.websockets;

import streamerchat.messages.WSMessageType;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/streamerchat/")
public class ChatEndpoint extends Endpoint {

    private static final WSContext WS_CONTEXT = new ChatWSContext();

    /*-------------------------------------------------*/

    @OnOpen @Override
    public void onOpen(Session session) {
        System.out.println("A user has connected to the Streamer Chat server.");
        super.onOpen(session);
        WS_CONTEXT.connectUser(session.getId());
        super.executeMessage(WSMessageType.GET_ALL_CHAT_LOBBIES, null, session, WS_CONTEXT);
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
