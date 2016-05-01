package com.vnapnic.streamgames.data.model;

import java.util.List;

public class TwitchVideos extends TwitchBase {

    private List<TwitchVideo> videos;
    private String title;

    public List<TwitchVideo> getVideos() {
        return videos;
    }

    public void setVideos(List<TwitchVideo> videos) {
        this.videos = videos;
        if (videos != null && videos.size() > 0) {
            this.title = videos.get(0).getTitle();
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
