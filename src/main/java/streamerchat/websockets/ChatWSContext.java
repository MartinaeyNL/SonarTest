package streamerchat.websockets;

import streamerchat.messagetypes.*;
import streamerchat.models.Controller;

import java.util.EnumMap;

public class ChatWSContext extends WSContext {

    // Constructor
    public ChatWSContext() {
        this.controller = new Controller();
        this.strategies = new EnumMap<>(WSMessageType.class);
        this.strategies.put(WSMessageType.JOIN_CHAT_LOBBY, new JoinChatLobby_Strategy());
        this.strategies.put(WSMessageType.LEAVE_CHAT_LOBBY, new LeaveChatLobby_Strategy());
        this.strategies.put(WSMessageType.GET_ALL_CHAT_LOBBIES, new GetAllChatLobbies_Strategy());
    }

    /*--------------------------------------------------------------------------*/
}
