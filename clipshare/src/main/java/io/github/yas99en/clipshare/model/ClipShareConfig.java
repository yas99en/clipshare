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
        prefs.putInt("serverPort", port);
    }

    public int getPort() {
        return prefs.getInt("serverPort", DEFAULT_PORT);
    }

    public void setClientServerHost(String host) {
        prefs.put("client.serverHost", host);
    }

    public String getClientServerHost() {
        return prefs.get("client.serverHost", null);
    }

    public void clear() throws BackingStoreException {
        prefs.clear();
    }
}
