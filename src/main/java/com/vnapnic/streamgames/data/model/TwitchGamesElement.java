package com.vnapnic.streamgames.data.model;

public class TwitchGamesElement extends TwitchBase {

    private TwitchGame game;
    private long viewers;
    private long channels;

    public TwitchGame getGame() {
        return game;
    }

    public void setGame(TwitchGame game) {
        this.game = game;
    }

    public long getViewers() {
        return viewers;
    }

    public void setViewers(long viewers) {
        this.viewers = viewers;
    }

    public long getChannels() {
        return channels;
    }

    public void setChannels(long channels) {
        this.channels = channels;
    }
}
