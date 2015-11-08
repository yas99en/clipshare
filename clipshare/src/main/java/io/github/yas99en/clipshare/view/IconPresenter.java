package io.github.yas99en.clipshare.view;

import java.awt.AWTException;
import java.awt.TrayIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class IconPresenter {
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
    }
}
