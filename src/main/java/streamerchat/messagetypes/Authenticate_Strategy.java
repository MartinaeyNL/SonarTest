package streamerchat.messagetypes;

import com.google.gson.JsonObject;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import streamerchat.http.HttpCall;
import streamerchat.websockets.WSMessage;
import streamerchat.models.Controller;
import streamerchat.models.User;

import java.util.Collection;

public class Authenticate_Strategy implements WSMessageTypeStrategy {

    @Override
    public Collection<WSMessage> start(Object parameter, User user, Controller controller) throws Exception {
        HttpPost request = new HttpPost("http://localhost:8088/auth/createUser");
        String json = "{'displayname':'test','twitchToken':'John''}";
        JsonObject test = new JsonObject();
        test.addProperty("displayname", "test");
        test.addProperty("twitchToken", "John");
        StringEntity entity = new StringEntity(test.toString());
        request.setEntity(entity);
        request.setHeader("Accept", "application/json");
        request.setHeader("Content-type", "application/json");
        Collection<Object> toReturn = new HttpCall().call(request);
        return null;
    }
}
