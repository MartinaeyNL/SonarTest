package streamerchat.http;

import com.google.gson.Gson;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;

import java.io.UnsupportedEncodingException;
import java.util.Collection;

public class HttpController {

    // Variables
    private Gson gson = new Gson();

    /*---------------------------------------------------------------*/

    // Methods
    public Collection<Object> createUser(Object user) throws Exception {
        HttpPost request = new HttpPost("http://localhost:8088/auth/connectUser");
        request.setEntity(new StringEntity(this.gson.toJson(user)));
        return new HttpCall().call(request);
    }
}
