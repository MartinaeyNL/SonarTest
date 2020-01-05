package streamerchat.messagetypes;

import streamerchat.models.Controller;
import streamerchat.models.User;

public interface WSMessageTypeStrategy {

    // Methods
    Object start(Object parameter, User user, Controller controller) throws Exception;
}
