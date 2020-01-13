package streamerchat.http;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

public class HttpCall {

    // Variables
    Gson gson = new Gson();
    CloseableHttpClient httpClient;

    // Constructor
    public HttpCall() {
        this.httpClient = HttpClients.createDefault();
    }

    public Collection<Object> call(HttpRequestBase request) throws IOException {

        // Add JSON Headers
        request.addHeader("Accept", "application/json");
        request.addHeader("Content-type", "application/json");

        // Making the actual call
        CloseableHttpResponse response = this.httpClient.execute(request);

        // Getting the entity out of the response
        HttpEntity entity = response.getEntity();
        String toReturn = EntityUtils.toString(entity);
        EntityUtils.consume(entity);

        // Handling the response
        System.out.println("toReturn = [" + toReturn + "]");
        Collection<Object> test = gson.fromJson(toReturn, new TypeToken<Collection<Object>>(){}.getType());
        System.out.println("HttpCall returns: [" + test + "]");
        return test;
    }
}
