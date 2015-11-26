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

import javax.websocket.DeploymentException;

import io.github.yas99en.clipshare.model.ClipShareClient;
import io.github.yas99en.clipshare.model.ClipShareConfig;
import io.github.yas99en.clipshare.model.ClipShareContext;
import io.github.yas99en.clipshare.model.ClipShareServer;;

public class IconPresenter implements ClipShareServer.Listener, ClipShareClient.Listener {
    private final ClipShareContext context = ClipShareContext.getInstance();
    private final ClipShareServer server = context.getServer();
    private final ClipShareClient client = context.getClient();
    private final ClipShareConfig config = context.getConfig();
    private final ClipShareIcon icon = new ClipShareIcon();
    private final Toolkit kit = Toolkit.getDefaultToolkit();
    private final Clipboard clipboard = kit.getSystemClipboard();
    private SettingDialogPresenter settingDialogPresenter;

    public IconPresenter() throws IOException, AWTException {
        icon.exitItem.addActionListener(e -> System.exit(0));
        icon.settingsItem.addActionListener(e -> getSettingDialogPresenter().show());
        icon.trayIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 1) {
                    onClicked();
                }
            }
        });
        server.setListener(InvokeLaterProxy.makeProxy(ClipShareServer.Listener.class, this));
        client.setListener(InvokeLaterProxy.makeProxy(ClipShareClient.Listener.class, this));
        update();
    }

    public void update() {
        String key = config.isServerMode() ? "ClipShareIcon.tooltip.server" : "ClipShareIcon.tooltip.client";
        String tooltip = Messages.getString(key);
        icon.trayIcon.setToolTip(tooltip);
    }

    private SettingDialogPresenter getSettingDialogPresenter() {
        if(settingDialogPresenter == null) {
            settingDialogPresenter = new SettingDialogPresenter(this);
        }
        return settingDialogPresenter;
    }

    private void onClicked() {
        try {
            String data = (String) clipboard.getData(DataFlavor.stringFlavor);
            if(data == null) {
                return;
            }
            sendMessage(data);
        } catch (UnsupportedFlavorException | IOException e) {
            showMessage(e.getLocalizedMessage());
        }
    }

    private void sendMessage(String data) {
        boolean serverMode = config.isServerMode();
        if(serverMode) {
            server.broadCast(data);
        } else {
            client.sendMessage(data);
        }
    }

    public void serverStartFailed() {
        showMessage(Messages.getString("ClipShareIcon.serverStartFailed"));
    }

    public void showMessage(String message) {
        icon.displayMessage(Messages.getString("AppName"), message, TrayIcon.MessageType.NONE);
    }

    public void start() {
        boolean serverMode = config.isServerMode();
        if(serverMode) {
            int serverPort = config.getPort();
            try {
                server.start(serverPort);
            } catch (DeploymentException e) {
                serverStartFailed();
            }
        } else {
            String host = config.getHost();
            if(host == null) {
                return;
            }
            int port = config.getPort();
            client.start(host, port);
        }
    }

    @Override
    public void onServerMessage(String message) {
        setClipboad(message);
        server.broadCast(message);
    }

    @Override
    public void onClientMessage(String message) {
        setClipboad(message);
    }

    private void setClipboad(String data) {
        StringSelection ss = new StringSelection(data);
        clipboard.setContents(ss, ss);
    }
}
