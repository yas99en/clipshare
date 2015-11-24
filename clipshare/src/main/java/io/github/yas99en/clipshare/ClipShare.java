package io.github.yas99en.clipshare;

import java.awt.AWTException;
import java.io.IOException;
import java.util.prefs.BackingStoreException;

import javax.swing.SwingUtilities;

import io.github.yas99en.clipshare.model.ClipShareConfig;
import io.github.yas99en.clipshare.model.ClipShareContext;
import io.github.yas99en.clipshare.view.IconPresenter;

public class ClipShare {

    public static void main(String[] args) throws BackingStoreException {
        ClipShareConfig config = ClipShareContext.getInstance().getConfig();
        args2Prefs(args, config);
        SwingUtilities.invokeLater(ClipShare::run);
    }

    public static void args2Prefs(String[] args, ClipShareConfig config) throws BackingStoreException {
        for(int i = 0; i < args.length; i++) {
            String arg = args[i];
            if(arg.equals("--serverMode")) {
                config.setServerMode(true);
            } else if(arg.equals("--clientMode")) {
                config.setServerMode(false);
            } else if(arg.equals("--port")) {
                if(i < args.length-1) {
                    config.setPort(Integer.parseInt(args[i+1]));
                    i++;
                }
            } else if(arg.equals("--host")) {
                if(i < args.length-1) {
                    config.setClientServerHost(args[i+1]);
                    i++;
                }
            } else if(arg.equals("--clear")) {
                config.clear();
                System.exit(0);
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
