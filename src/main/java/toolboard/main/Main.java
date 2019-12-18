package toolboard.main;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.websocket.jsr356.server.ServerContainer;
import org.eclipse.jetty.websocket.jsr356.server.deploy.WebSocketServerContainerInitializer;

import javax.websocket.Endpoint;

public class Main {

    private static final int PORT = 8096;

    // Init
    public static void main(String[] args)
    {
        // Creation of Server and setting the port
        Server webSocketServer = new Server();
        ServerConnector connector = new ServerConnector(webSocketServer);
        connector.setPort(PORT);
        webSocketServer.addConnector(connector);

        // Setting the path of the server
        ServletContextHandler webSocketContext = new ServletContextHandler(ServletContextHandler.SESSIONS);
        webSocketContext.setContextPath("/");
        webSocketServer.setHandler(webSocketContext);
        System.out.println("Starting server..");

        // Starting the container and linking the endpoint to it
        try {
            ServerContainer container = WebSocketServerContainerInitializer.configureContext(webSocketContext);
            container.addEndpoint(Websocket.class);
            webSocketServer.start(); // Start the server
            webSocketServer.join(); // Wait till server is ready
        }
        catch(Exception e) { e.printStackTrace(); }


    }
}
