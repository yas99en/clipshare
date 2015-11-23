package io.github.yas99en.clipshare.model;

public class ClipShareContext {
    private ClipShareContext() {}
    private ClipShareServer server = ClipShareServer.getInstance();
    private ClipShareClient client = new ClipShareClient();
    private ClipShareConfig config = new ClipShareConfig();

    static ClipShareContext instance = new ClipShareContext();

    static public ClipShareContext getInstance() {
        return instance;
    }

    public ClipShareServer getServer() {
        return server;
    }

    public ClipShareClient getClient() {
        return client;
    }

    public ClipShareConfig getConfig() {
        return config;
    }
}
