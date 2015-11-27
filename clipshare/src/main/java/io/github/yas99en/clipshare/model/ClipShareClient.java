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
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

@ClientEndpoint
public class ClipShareClient {
    private static final int INITIAL_DELAY = 0;
    private static final int PERIOD = 5;

    public interface Listener {
        void onClientMessage(String message);
    }

    private Session session;
    private Listener listener;
    private String host;
    private int port;
    private ScheduledExecutorService ses;
    private boolean started;
    private final Object lock = new Object();

    public void setListener(Listener listener) {
        synchronized (lock) {
            this.listener = listener;
        }
    }

    public void start(String host, int port)  {
        synchronized (lock) {
            if(started) {
                throw new IllegalStateException("already started");
            }
            started = true;
            this.host = host;
            this.port = port;
        }

        startConnection();
    }

    private void startConnection() {
        synchronized (lock) {
            ses = Executors.newScheduledThreadPool(1);
        }
        ses.scheduleAtFixedRate(() -> {
            synchronized (lock) {
                if(!started) {
                    return;
                }
                session = connectTo(this.host, this.port);
                if(session != null) {
                    ses.shutdown();
                    ses = null;
                }
            }
        }, INITIAL_DELAY, PERIOD, TimeUnit.SECONDS);
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
        synchronized (lock) {
            if(!started) {
                throw new IllegalAccessError("already stopped");
            }
            started = false;
            if(ses != null) {
                ses.shutdown();
                ses = null;
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
    }

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("open: " + session.getId());
    }

    @OnMessage
    public void onMessage(Session session, String message) {
        System.out.println("message: "+message);
        Listener l = null;
        synchronized (lock) {
            l = listener;
        }
        if(l != null) {
            l.onClientMessage(message);
        }
    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("close: " + session.getId());
        synchronized (lock) {
            session = null;
            if(started) {
                startConnection();
            }
        }
    }

    public void sendMessage(String message) {
        Session s = null;
        synchronized (lock) {
            s = session;
        }
        if(s != null) {
            s.getAsyncRemote().sendText(message);
        }
    }
}
