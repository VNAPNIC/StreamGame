package com.vnapnic.streamgames.data.model;

import java.util.List;

public class TwitchChannels extends TwitchBase {

    private TwitchChannel channel;
    private List<TwitchChannel> channels;

    public TwitchChannel getChannel() {
        return channel;
    }

    public void setChannel(TwitchChannel channel) {
        this.channel = channel;
    }

    public List<TwitchChannel> getChannels() {
        return channels;
    }

    public void setChannels(List<TwitchChannel> channels) {
        this.channels = channels;
    }
}
