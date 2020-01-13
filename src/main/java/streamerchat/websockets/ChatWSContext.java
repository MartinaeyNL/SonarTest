package streamerchat.websockets;

import streamerchat.messagetypes.*;
import streamerchat.models.Controller;

import java.util.HashMap;

public class ChatWSContext extends WSContext {

    // Constructor
    public ChatWSContext() {
        this.controller = new Controller();
        this.strategies = new HashMap<>();
        this.strategies.put(WSMessageType.joinChatLobby, new JoinChatLobby_Strategy());
        this.strategies.put(WSMessageType.leaveChatLobby, new LeaveChatLobby_Strategy());
        this.strategies.put(WSMessageType.getAllChatLobbies, new GetAllChatLobbies_Strategy());
    }

    /*--------------------------------------------------------------------------*/
}
