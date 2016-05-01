package com.vnapnic.streamgames.data.model;

import android.util.SparseArray;

import java.util.List;

public class TwitchFollows extends TwitchBase {

    private List<TwitchChannels> follows;

    public SparseArray<TwitchChannel> getFollows() {
        SparseArray<TwitchChannel> result = new SparseArray<TwitchChannel>(follows.size());
        for (TwitchChannels channels : follows) {
            TwitchChannel channel = channels.getChannel();
            result.put(channel.get_id(), channel);
        }
        return result;
    }

    public void setFollows(List<TwitchChannels> follows) {
        this.follows = follows;
    }
}
