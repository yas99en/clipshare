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
        void onClientMessage(String message);
    }

    private Session session;
    private Listener listener;

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public void start(String server, int port)  {
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();

        URI uri = URI.create("ws://"+server+":"+port+"/clipShare/update");

        try {
            session = container.connectToServer(this, uri);
        } catch (DeploymentException | IOException e) {
            session = null;
        }
    }

    public void stop() throws IOException {
        if(session != null) {
            session.close();
        }
        session = null;
    }

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("open: " + session.getId());
    }

    @OnMessage
    public void onMessage(Session session, String message) {
        System.out.println("message: "+message);
        if(listener != null) {
            listener.onClientMessage(message);
        }
    }

    @OnError
    public void onError(Throwable t) {
        t.printStackTrace();
    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("close: " + session.getId());
        session = null;
    }

    public void sendMessage(String message) {
        if(session != null) {
            session.getAsyncRemote().sendText(message);
        }
    }
}
