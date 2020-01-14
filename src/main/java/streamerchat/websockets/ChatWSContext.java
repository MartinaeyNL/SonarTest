package streamerchat.websockets;

import streamerchat.messages.*;
import streamerchat.models.Controller;
import streamerchat.strategies.GetAllChatLobbiesStrategy;
import streamerchat.strategies.JoinChatLobbyStrategy;
import streamerchat.strategies.LeaveChatLobbyStrategy;

import java.util.EnumMap;

public class ChatWSContext extends WSContext {

    // Constructor
    public ChatWSContext() {
        this.controller = new Controller();
        this.strategies = new EnumMap<>(WSMessageType.class);
        this.strategies.put(WSMessageType.JOIN_CHAT_LOBBY, new JoinChatLobbyStrategy());
        this.strategies.put(WSMessageType.LEAVE_CHAT_LOBBY, new LeaveChatLobbyStrategy());
        this.strategies.put(WSMessageType.GET_ALL_CHAT_LOBBIES, new GetAllChatLobbiesStrategy());
    }

    /*--------------------------------------------------------------------------*/
}
