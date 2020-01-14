package streamerchat.websockets;

import streamerchat.messages.*;
import streamerchat.models.Controller;
import streamerchat.strategies.AuthenticateStrategy;

import java.util.EnumMap;

public class AuthWSContext extends WSContext {

    // Constructor
    public AuthWSContext() {
        this.controller = new Controller();
        this.strategies = new EnumMap<>(WSMessageType.class);
        this.strategies.put(WSMessageType.AUTHENTICATE, new AuthenticateStrategy());
    }
}
