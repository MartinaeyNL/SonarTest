package streamerchat.websockets;

import streamerchat.messagetypes.*;
import streamerchat.models.Controller;

import java.util.HashMap;

public class AuthWSContext extends WSContext {

    // Constructor
    public AuthWSContext() {
        this.controller = new Controller();
        this.strategies = new HashMap<>();
        this.strategies.put(WSMessageType.authenticate, new Authenticate_Strategy());
    }
}
