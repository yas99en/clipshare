package io.github.yas99en.clipshare.model;

import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

public class ClipShareEndpoint {
    ClipShareServer server = ClipShareServer.getInstance();
    @OnOpen
    public void onOpen(EndpointConfig config, Session session) {
        System.out.println("[open] " + session);
        server.addSession(session);
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        server.onMessage(message, session);
    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("[close] " + session);
        server.removeSession(session);
    }
}
