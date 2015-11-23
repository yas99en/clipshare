package io.github.yas99en.clipshare;

import java.awt.AWTException;
import java.io.IOException;
import java.util.prefs.Preferences;

import javax.swing.SwingUtilities;

import io.github.yas99en.clipshare.model.ClipShareContext;
import io.github.yas99en.clipshare.view.IconPresenter;

public class ClipShare {

    public static void main(String[] args) {
        Preferences prefs = ClipShareContext.getInstance().getPreferences();
        args2Prefs(args, prefs);
        SwingUtilities.invokeLater(ClipShare::run);
    }

    public static void args2Prefs(String[] args, Preferences prefs) {
        for(int i = 0; i < args.length; i++) {
            String arg = args[i];
            if(arg.equals("--serverMode")) {
                prefs.putBoolean("serverMode", true);
            } else if(arg.equals("--clientMode")) {
                prefs.putBoolean("serverMode", false);
            } else if(arg.equals("--serverPort")) {
                if(i < args.length-1) {
                    prefs.putInt("server.port", Integer.parseInt(args[i+1]));
                    i++;
                }
            } else if(arg.equals("--clientServerHost")) {
                if(i < args.length-1) {
                    prefs.put("client.serverHost", args[i+1]);
                    i++;
                }
            } else if(arg.equals("--clientServerPort")) {
                if(i < args.length-1) {
                    prefs.putInt("client.serverPort", Integer.parseInt(args[i+1]));
                    i++;
                }
            }
        }
    }

    public static void run() {
        try {
            new IconPresenter().start();;
        } catch (IOException | AWTException e) {
            e.printStackTrace();
        }
    }
}
