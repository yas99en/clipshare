package io.github.yas99en.clipshare.view;

import java.awt.AWTException;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.SwingUtilities;
import javax.websocket.DeploymentException;

import io.github.yas99en.clipshare.model.ClipShareClient;
import io.github.yas99en.clipshare.model.ClipShareConfig;
import io.github.yas99en.clipshare.model.ClipShareContext;
import io.github.yas99en.clipshare.model.ClipShareServer;;

public class IconPresenter implements ClipShareServer.Listener, ClipShareClient.Listener {
    private ClipShareContext context = ClipShareContext.getInstance();
    private ClipShareServer server = context.getServer();
    private ClipShareClient client = context.getClient();
    private ClipShareIcon icon;
    private Toolkit kit = Toolkit.getDefaultToolkit();
    private Clipboard clip = kit.getSystemClipboard();

    public IconPresenter() throws IOException, AWTException {
        icon = new ClipShareIcon();
        icon.exitItem.addActionListener(e -> System.exit(0));

        icon.trayIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 1) {
                    onClicked();
                }
            }
        });

        server.setListener(this);
        client.setListener(this);
    }

    private void onClicked() {
//        icon.displayMessage(Msgs.m("AppName"), "Clicked", TrayIcon.MessageType.NONE);
        ClipShareConfig config = context.getConfig();
        
        try {
            String data = (String) clip.getData(DataFlavor.stringFlavor);
            if(data == null) {
                return;
            }
            boolean serverMode = config.isServerMode();
            if(serverMode) {
                server.broadCast(data);
            } else {
                client.sendMessage(data);
            }
        } catch (UnsupportedFlavorException | IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        ClipShareConfig config = context.getConfig();
        boolean serverMode = config.isServerMode();
        if(serverMode) {
            int serverPort = config.getServerPort();
            try {
                server.start(serverPort);
            } catch (DeploymentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            String host = config.getClientServerHost();
            if(host == null) {
                return;
            }
            int port = config.getClientServerPort();
            client.start(host, port);
        }
    }

    @Override
    public void onMessage(String message) {
        SwingUtilities.invokeLater(() -> {
            StringSelection ss = new StringSelection(message);
            clip.setContents(ss, ss);
            server.broadCast(message);
        });
    }

    @Override
    public void onClientMessage(String message) {
        SwingUtilities.invokeLater(() -> {
            StringSelection ss = new StringSelection(message);
            clip.setContents(ss, ss);
        });
    }
}
