package io.github.yas99en.clipshare.view;

import java.awt.AWTException;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.prefs.Preferences;

import javax.websocket.DeploymentException;

import io.github.yas99en.clipshare.model.Client;
import io.github.yas99en.clipshare.model.ClipShareContext;
import io.github.yas99en.clipshare.model.ClipShareServer;;

public class IconPresenter implements ClipShareServer.Listener, Client.Listener {
    private static final int DEFAULT_PORT = 18211;
    private ClipShareContext context = ClipShareContext.getInstance();
    private ClipShareServer server = context.getServer();
    private Client client = context.getClient();

    public IconPresenter() throws IOException, AWTException {
        ClipShareIcon icon = new ClipShareIcon();
        icon.exitItem.addActionListener(e -> System.exit(0));

        icon.trayIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 1) {
                    icon.displayMessage(Msgs.m("AppName"), "Clicked", TrayIcon.MessageType.NONE);
                }
            }
        });

        server.setListener(this);
        client.setListener(this);
    }

    public void start() {
        Preferences prefs = context.getPreferences();
        boolean serverMode = prefs.getBoolean("serverMode", true);
        if(serverMode) {
            int serverPort = prefs.getInt("server.port", DEFAULT_PORT);
            try {
                server.start(serverPort);
            } catch (DeploymentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            String host = prefs.get("client.serverHost", null);
            if(host == null) {
                return;
            }
            int port = prefs.getInt("client.serverPort", DEFAULT_PORT);
            client.start(host, port);
        }
    }

    @Override
    public void onMessage(String message) {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Clipboard clip = kit.getSystemClipboard();
        StringSelection ss = new StringSelection(message);
        clip.setContents(ss, ss);
        
        server.broadCast(message);
    }
}
