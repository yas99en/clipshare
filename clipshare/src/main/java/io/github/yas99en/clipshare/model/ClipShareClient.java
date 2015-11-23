package io.github.yas99en.clipshare.model;

import java.io.IOException;
import java.net.URI;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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
    private String host;
    private int port;
    private ScheduledExecutorService ses;
    private boolean started;

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public void start(String host, int port)  {
        started = true;
        this.host = host;
        this.port = port;

        startConnection();
    }

    private void startConnection() {
        ses = Executors.newScheduledThreadPool(1);
        ses.scheduleAtFixedRate(() -> {
            session = connectTo(this.host, this.port);
            if(session != null) {
                ses.shutdown();
                ses = null;
            }
        }, 0, 5, TimeUnit.SECONDS);
    }

    private Session connectTo(String host, int port) {
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();

        URI uri = URI.create("ws://"+host+":"+port+"/clipShare/update");

        try {
            return container.connectToServer(this, uri);
        } catch (DeploymentException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void stop() {
        started = false;
        if(ses != null) {
            ses.shutdown();
            try {
                ses.awaitTermination(5, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if(session != null) {
            try {
                session.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
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
        if(started) {
            startConnection();
        }
    }

    public void sendMessage(String message) {
        if(session != null) {
            session.getAsyncRemote().sendText(message);
        }
    }
}
