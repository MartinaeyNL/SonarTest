package streamerchat.strategies;

import com.google.gson.Gson;
import streamerchat.http.HttpController;
import streamerchat.messages.WSMessageType;
import streamerchat.models.User;
import streamerchat.messages.WSMessage;
import streamerchat.models.Controller;
import streamerchat.models.Session;
import streamerchat.messages.WSMessageConverter;

import java.util.ArrayList;
import java.util.Collection;

public class AuthenticateStrategy implements WSMessageTypeStrategy {

    private Gson gson = new Gson();

    @Override
    public Collection<WSMessage> start(Object object, Session session, Controller controller) throws Exception {

        User user = gson.fromJson(object.toString(), User.class);               // Converting the object to JSON
        Collection<Object> result = new HttpController().createUser(user);      // Making the call to Controller

        // Adding the people that need to receive a message
        Collection<String> sessionIds = new ArrayList<>();
        sessionIds.add(session.getSessionId());

        // Parsing it to WSMessages
        return new WSMessageConverter().toWSMessages(WSMessageType.AUTHENTICATE, sessionIds, result);
    }
}
