package io.github.yas99en.clipshare.model;

import java.util.logging.Logger;

import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/update")
public class ClipShareEndpoint {
    private static final Logger logger = Logger.getLogger(ClipShareEndpoint.class.getName());

    ClipShareServer server = ClipShareServer.getInstance();
    @OnOpen
    public void onOpen(EndpointConfig config, Session session) {
        logger.info("[open] " + session);
        server.addSession(session);
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        server.onMessage(message, session);
    }

    @OnClose
    public void onClose(Session session) {
        logger.info("[close] " + session);
        server.removeSession(session);
    }
}
