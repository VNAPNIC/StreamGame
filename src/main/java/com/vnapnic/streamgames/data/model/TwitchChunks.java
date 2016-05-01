package com.vnapnic.streamgames.data.model;

import java.util.List;

public class TwitchChunks extends TwitchBase {

    private List<TwitchChunk> live;

    public List<TwitchChunk> getLive() {
        return live;
    }

    public void setLive(List<TwitchChunk> live) {
        this.live = live;
    }
}
