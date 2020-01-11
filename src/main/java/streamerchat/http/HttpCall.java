package streamerchat.http;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.util.Collection;

public class HttpCall {

    // Variables
    CloseableHttpClient httpClient;

    // Constructor
    public HttpCall() {
        this.httpClient = HttpClients.createDefault();
    }

    public Collection<Object> call(HttpRequestBase request) throws IOException {
        CloseableHttpResponse response = this.httpClient.execute(request);
        System.out.println(response);
        return null;
    }
}
