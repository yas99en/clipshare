package io.github.yas99en.clipshare.view;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ClipShareIcon {
    private TrayIcon icon;

    public ClipShareIcon() throws IOException, AWTException {
        SystemTray tray = SystemTray.getSystemTray();
        Image image = ImageIO.read(ClipShareIcon.class.getResourceAsStream("clipboard_edit16.png")); //$NON-NLS-1$

        PopupMenu menu = new PopupMenu(Msgs.m("ClipShareIcon.AppName")); //$NON-NLS-1$
        menu.add(new MenuItem(Msgs.m("ClipShareIcon.Settings"))); //$NON-NLS-1$
        menu.add(new MenuItem(Msgs.m("ClipShareIcon.Exit"))); //$NON-NLS-1$

        icon = new TrayIcon(image, Msgs.m("Tray.AppName"), menu); //$NON-NLS-1$
        tray.add(icon);
    }

    
}
