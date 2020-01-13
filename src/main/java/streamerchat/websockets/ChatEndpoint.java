package streamerchat.websockets;

import streamerchat.messagetypes.WSMessageType;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.Collection;

@ServerEndpoint(value = "/streamerchat/")
public class ChatEndpoint extends Endpoint {

    /*-------------------------------------------------*/

    @OnOpen @Override
    public void onOpen(Session session) {
        System.out.println("A user has connected to the Streamer Chat server.");
        super.setWsContext(new ChatWSContext());
        super.onOpen(session);
        super.executeMessage(WSMessageType.getAllChatLobbies, null, session);
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
