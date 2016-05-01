package com.vnapnic.streamgames.data.model;

import java.util.List;

public class TwitchStream extends TwitchBase {

    private TwitchStreamElement stream;
    private List<TwitchStreamElement> streams;

    public TwitchStreamElement getStream() {
        return stream;
    }

    public void setStream(TwitchStreamElement stream) {
        this.stream = stream;
    }

    public List<TwitchStreamElement> getStreams() {
        return streams;
    }

    public void setStreams(List<TwitchStreamElement> streams) {
        this.streams = streams;
    }
}
