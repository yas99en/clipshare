package io.github.yas99en.clipshare.model;

import java.io.IOException;
import java.net.URI;

import javax.websocket.ClientEndpoint;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

@ClientEndpoint
public class ClipShareClient {
    public interface Listener {
        
    }

    public void setListener(Listener listener) {
        
    }

    public void start(String server, int port)  {
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();

        URI uri = URI.create("ws://localhost:8080/ws/echo");

        Session session;
        try {
            session = container.connectToServer(this, uri);
            System.out.println("connected");
            session.getBasicRemote().sendText("hello");
        } catch (DeploymentException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void stop() {
        
    }

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("open: " + session.getId());
    }

    @OnMessage
    public void onMessage(Session session, String message) {
        System.out.println("message: "+message);
    }

    @OnError
    public void onError(Throwable t) {
        t.printStackTrace();
    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("close: " + session.getId());
    }
}
