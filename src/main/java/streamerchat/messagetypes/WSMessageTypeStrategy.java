package streamerchat.messagetypes;

import streamerchat.models.Controller;

public interface WSMessageTypeStrategy {

    // Methods
    Object start(Object parameter, Controller controller);
}
