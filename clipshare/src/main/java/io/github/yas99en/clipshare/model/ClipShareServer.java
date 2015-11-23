package io.github.yas99en.clipshare.model;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.websocket.DeploymentException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.glassfish.tyrus.server.Server;

@ServerEndpoint("/clipShare")
public class ClipShareServer {
    public interface Listener {

        void onMessage(String message);
        
    }

    private Server server;
    private Listener listener;
    private List<Session> sessions = new CopyOnWriteArrayList<Session>();

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public void start(int port) throws DeploymentException {
        server = new Server("localhost", 8080, "/ws", null, ClipShareServer.class);
        server.start();
    }
    
    public void stop() {
        server.stop();
        server = null;
    }

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("[open] " + session);
        sessions.add(session);
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        Set<Session> sessions = session.getOpenSessions();
        System.out.println("no of sessions: "+sessions.size());
        if(listener != null) {
            listener.onMessage(message);
        }
    }

    public void broadCast(String message) {
        sessions.forEach(s -> {
            s.getAsyncRemote().sendText(message);
        });
    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("[close] " + session);
        sessions.remove(session);
    }
}
