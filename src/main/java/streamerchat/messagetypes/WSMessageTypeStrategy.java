package streamerchat.messagetypes;

import streamerchat.websockets.WSMessage;
import streamerchat.models.Controller;
import streamerchat.models.User;

import java.util.Collection;

public interface WSMessageTypeStrategy {

    // Methods
    Collection<WSMessage> start(Object parameter, User user, Controller controller) throws Exception;
}
