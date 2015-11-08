package io.github.yas99en.clipshare.view;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Tray {
    public Tray() throws IOException, AWTException {
        SystemTray tray = SystemTray.getSystemTray();
        Image image = ImageIO.read(Tray.class.getResourceAsStream("clipboard_edit16.png"));
        TrayIcon icon = new TrayIcon(image, "ClipShare");
        tray.add(icon);
    }


}
