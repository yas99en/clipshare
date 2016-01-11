package io.github.yas99en.clipshare.model;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.websocket.DeploymentException;
import javax.websocket.Session;

import org.glassfish.tyrus.server.Server;

public class ClipShareServer {
    public interface Listener {
        void onServerMessage(String message, Session session);
    }

    private static ClipShareServer instance = new ClipShareServer();
    private Server server;
    private Listener listener;
    private List<Session> sessions = new CopyOnWriteArrayList<Session>();

    private ClipShareServer() {
    }

    public static ClipShareServer getInstance() {
        return instance;
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public void start(int port) throws DeploymentException {
        server = new Server("localhost", port, "/clipShare", null, ClipShareEndpoint.class);
        server.start();
    }
    
    public void stop() {
        if(server != null) {
            server.stop();
        }
        server = null;
    }

    public void broadCast(String message) {
        broadCast(message, null);
    }
    public void broadCast(String message, Session except) {
        String exceptId = (except != null) ? except.getId(): null;
        sessions.stream()
        .filter(s -> exceptId == null || !s.getId().equals(exceptId))
        .forEach(s ->s.getAsyncRemote().sendText(message));
    }

    void addSession(Session session) {
        sessions.add(session);
    }

    void removeSession(Session session) {
        sessions.remove(session);
    }

    void onMessage(String message, Session session) {
        if(listener != null) {
            listener.onServerMessage(message, session);
        }
    }
}
