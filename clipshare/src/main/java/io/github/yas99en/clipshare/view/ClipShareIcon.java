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
    final PopupMenu menu = new PopupMenu(Messages.getString("appName"));
    final MenuItem settingsItem = new MenuItem(Messages.getString("ClipShareIcon.settings"));
    final MenuItem exitItem = new MenuItem(Messages.getString("ClipShareIcon.exit"));

    public ClipShareIcon() throws IOException, AWTException {
        SystemTray tray = SystemTray.getSystemTray();

        String iconFileName = 
                System.getProperty("os.name", "").equals("Linux") ? "clipboard_edit24.png" : "clipboard_edit16.png";

        Image image = ImageIO.read(ClipShareIcon.class.getResourceAsStream(iconFileName));
        menu.add(settingsItem);
        menu.add(exitItem);
        trayIcon = new TrayIcon(image, Messages.getString("AppName"), menu);
        tray.add(trayIcon);
    }

    public void displayMessage(String caption, String text, MessageType messageType) {
        trayIcon.displayMessage(caption, text, messageType);
    }

    public static void main(String[] args) throws IOException {
        System.getProperties().store(System.out, "test");
    }
}
