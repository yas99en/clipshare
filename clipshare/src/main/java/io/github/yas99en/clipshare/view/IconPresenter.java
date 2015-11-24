package io.github.yas99en.clipshare.view;

import java.awt.AWTException;
import java.awt.Toolkit;
import java.awt.TrayIcon;
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
    private final ClipShareContext context = ClipShareContext.getInstance();
    private final ClipShareServer server = context.getServer();
    private final ClipShareClient client = context.getClient();
    private final ClipShareIcon icon = new ClipShareIcon();
    private final Toolkit kit = Toolkit.getDefaultToolkit();
    private final Clipboard clip = kit.getSystemClipboard();
    private SettingDialogPresenter settingDialogPresenter;

    public IconPresenter() throws IOException, AWTException {
        icon.exitItem.addActionListener(e -> System.exit(0));
        
        icon.settingsItem.addActionListener(e -> {
            getSettingDialogPresenter().show();
        });

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

    private SettingDialogPresenter getSettingDialogPresenter() {
        if(settingDialogPresenter == null) {
            settingDialogPresenter = new SettingDialogPresenter();
        }
        return settingDialogPresenter;
    }

    private void onClicked() {
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
            int serverPort = config.getPort();
            try {
                server.start(serverPort);
            } catch (DeploymentException e) {
                icon.displayMessage(Msgs.m("AppName"),
                        "Server start failed", TrayIcon.MessageType.NONE);
            }
        } else {
            String host = config.getClientServerHost();
            if(host == null) {
                return;
            }
            int port = config.getPort();
            client.start(host, port);
        }
    }

    @Override
    public void onServerMessage(String message) {
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
