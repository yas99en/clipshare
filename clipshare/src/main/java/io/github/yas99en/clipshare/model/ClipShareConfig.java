package io.github.yas99en.clipshare.model;

import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

public class ClipShareConfig {
    private static final int DEFAULT_PORT = 18211;

    private Preferences prefs = Preferences.userNodeForPackage(this.getClass());

    public void setServerMode(boolean serverMode) {
        prefs.putBoolean("serverMode", serverMode);
    }

    public boolean isServerMode() {
        return prefs.getBoolean("serverMode", true);
    }

    public void setPort(int port) {
        prefs.putInt("port", port);
    }

    public int getPort() {
        return prefs.getInt("port", DEFAULT_PORT);
    }

    public void setHost(String host) {
        prefs.put("host", host);
    }

    public String getHost() {
        return prefs.get("host", null);
    }

    public void clear() throws BackingStoreException {
        prefs.clear();
    }

    public void flush() throws BackingStoreException {
        prefs.flush();
    }
}
