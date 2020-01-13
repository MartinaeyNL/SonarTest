package streamerchat.messagetypes;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import streamerchat.http.HttpCall;
import streamerchat.http.HttpController;
import streamerchat.models.User;
import streamerchat.websockets.WSMessage;
import streamerchat.models.Controller;
import streamerchat.models.Session;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;

public class Authenticate_Strategy implements WSMessageTypeStrategy {

    private Gson gson = new Gson();

    @Override
    public Collection<WSMessage> start(Object object, Session session, Controller controller) throws Exception {

        // Converting the object to JSON
        User user = gson.fromJson(object.toString(), User.class);

        // Making the call to Controller
        Collection<Object> result = new HttpController().createUser(user);
        Collection<WSMessage> toReturn = new ArrayList<>();

        // Parsing it to WSMessages
        if(result != null) {
            for(Object o : result) {
                toReturn.add(new WSMessage(session.getSessionId(), WSMessageType.authenticate, o));
            }
        }
        if(toReturn.size() > 0) { return toReturn; }
        return null;
    }
}
