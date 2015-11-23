package io.github.yas99en.clipshare.view;

import java.awt.AWTException;
import java.awt.TrayIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.prefs.Preferences;

import io.github.yas99en.clipshare.model.ClipShareContext;
import io.github.yas99en.clipshare.model.Server;
import io.github.yas99en.clipshare.model.Client;;

public class IconPresenter implements Server.Listener, Client.Listener {
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
        
        ClipShareContext context = ClipShareContext.getInstance();

        Server server = context.getServer();
        server.setListener(this);

        Client client = context.getClient();
        client.setListener(this);
        
        Preferences prefs = context.getPreferences();
        boolean serverMode = prefs.getBoolean("serverMode", false);
        if(serverMode) {
            int serverPort = prefs.getInt("serverPort", 18211);
            server.start(serverPort);
        } else {
            String serverHost = prefs.get("serverHost", null);
            int port = prefs.getInt("port", 18211);
            client.start(serverHost, port);
        }

    }
}
