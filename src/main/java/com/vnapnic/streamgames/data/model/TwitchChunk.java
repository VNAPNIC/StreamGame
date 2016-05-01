package com.vnapnic.streamgames.data.model;

public class TwitchChunk extends TwitchBase {

    private String url;
    private String vod_count_url;
    private String upkeep;
    private long length;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getVod_count_url() {
        return vod_count_url;
    }

    public void setVod_count_url(String vod_count_url) {
        this.vod_count_url = vod_count_url;
    }

    public String getUpkeep() {
        return upkeep;
    }

    public void setUpkeep(String upkeep) {
        this.upkeep = upkeep;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }
}
