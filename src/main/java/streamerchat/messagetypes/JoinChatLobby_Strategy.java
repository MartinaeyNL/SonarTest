package streamerchat.messagetypes;

import streamerchat.models.Controller;

public class JoinChatLobby_Strategy implements WSMessageTypeStrategy {

    @Override
    public Object start(Object parameter, Controller controller) {

        // Parameter checking
        if(parameter instanceof String) {
            System.out.println("Joining the chatlobby " + parameter);

        }

        System.out.println("I've arrived at the right strategy stuff meuk ding hahahahahahaahahahahah pop");
        return null;
    }
}
