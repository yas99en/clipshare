package io.github.yas99en.clipshare.model;

import java.util.prefs.Preferences;

public class ClipShareContext {
    private ClipShareContext() {}
    private ClipShareServer server = new ClipShareServer();
    private Client client = new Client();
    private Preferences preferences = Preferences.userNodeForPackage(this.getClass());

    static ClipShareContext instance = new ClipShareContext();

    static public ClipShareContext getInstance() {
        return instance;
    }

    public ClipShareServer getServer() {
        return server;
    }

    public void setServer(ClipShareServer server) {
        this.server = server;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Preferences getPreferences() {
        return preferences;
    }

    public void setPreferences(Preferences preferences) {
        this.preferences = preferences;
    }
}
