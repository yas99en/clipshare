package io.github.yas99en.clipshare.view;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ClipShareIcon {
    final TrayIcon trayIcon;
    final MenuItem settingsItem;
    final MenuItem exitItem;

    public ClipShareIcon() throws IOException, AWTException {
        SystemTray tray = SystemTray.getSystemTray();
        Image image = ImageIO.read(ClipShareIcon.class.getResourceAsStream("clipboard_edit16.png"));

        PopupMenu menu = new PopupMenu(Msgs.m("AppName"));
        settingsItem = new MenuItem(Msgs.m("ClipShareIcon.Settings"));
        menu.add(settingsItem);
        exitItem = new MenuItem(Msgs.m("ClipShareIcon.Exit"));
        menu.add(exitItem);

        trayIcon = new TrayIcon(image, Msgs.m("AppName"), menu);
        tray.add(trayIcon);
    }

    public void displayMessage(String caption, String text, MessageType messageType) {
        trayIcon.displayMessage(caption, text, messageType);
    }

    
}
