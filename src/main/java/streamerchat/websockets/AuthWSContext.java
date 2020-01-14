package streamerchat.websockets;

import streamerchat.messagetypes.*;
import streamerchat.models.Controller;

import java.util.EnumMap;

public class AuthWSContext extends WSContext {

    // Constructor
    public AuthWSContext() {
        this.controller = new Controller();
        this.strategies = new EnumMap<>(WSMessageType.class);
        this.strategies.put(WSMessageType.AUTHENTICATE, new Authenticate_Strategy());
    }
}
