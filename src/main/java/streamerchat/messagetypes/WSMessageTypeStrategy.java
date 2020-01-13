package streamerchat.messagetypes;

import streamerchat.websockets.WSMessage;
import streamerchat.models.Controller;
import streamerchat.models.Session;

import java.util.Collection;

public interface WSMessageTypeStrategy {

    // Methods
    Collection<WSMessage> start(Object parameter, Session session, Controller controller) throws Exception;
}
