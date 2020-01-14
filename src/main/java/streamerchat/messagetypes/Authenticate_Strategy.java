package streamerchat.messagetypes;

import com.google.gson.Gson;
import streamerchat.http.HttpController;
import streamerchat.models.User;
import streamerchat.websockets.WSMessage;
import streamerchat.models.Controller;
import streamerchat.models.Session;
import streamerchat.websockets.WSMessageConverter;

import java.util.Collection;

public class Authenticate_Strategy implements WSMessageTypeStrategy {

    private Gson gson = new Gson();

    @Override
    public Collection<WSMessage> start(Object object, Session session, Controller controller) throws Exception {

        User user = gson.fromJson(object.toString(), User.class);               // Converting the object to JSON
        Collection<Object> result = new HttpController().createUser(user);      // Making the call to Controller

        // Parsing it to WSMessages
        return new WSMessageConverter().toWSMessages(WSMessageType.AUTHENTICATE, session.getSessionId(), result);
    }
}
