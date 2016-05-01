package com.vnapnic.streamgames.data.model;

public class TwitchBroadcast extends TwitchBase {

    private String api_id;
    private String increment_view_count_url;
    private String path;
    private String broadcaster_software;
    private String channel;
    private String preview_small;
    private String preview;
    private TwitchChunks chunks;
    private long duration;
    private long start_offset;
    private long end_offset;
    private long play_offset;
    private long vod_ad_frequency;
    private long vod_ad_length;

    public String getApi_id() {
        return api_id;
    }

    public void setApi_id(String api_id) {
        this.api_id = api_id;
    }

    public String getIncrement_view_count_url() {
        return increment_view_count_url;
    }

    public void setIncrement_view_count_url(String increment_view_count_url) {
        this.increment_view_count_url = increment_view_count_url;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getBroadcaster_software() {
        return broadcaster_software;
    }

    public void setBroadcaster_software(String broadcaster_software) {
        this.broadcaster_software = broadcaster_software;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getPreview_small() {
        return preview_small;
    }

    public void setPreview_small(String preview_small) {
        this.preview_small = preview_small;
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    public TwitchChunks getChunks() {
        return chunks;
    }

    public void setChunks(TwitchChunks chunks) {
        this.chunks = chunks;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public long getStart_offset() {
        return start_offset;
    }

    public void setStart_offset(long start_offset) {
        this.start_offset = start_offset;
    }

    public long getEnd_offset() {
        return end_offset;
    }

    public void setEnd_offset(long end_offset) {
        this.end_offset = end_offset;
    }

    public long getPlay_offset() {
        return play_offset;
    }

    public void setPlay_offset(long play_offset) {
        this.play_offset = play_offset;
    }

    public long getVod_ad_frequency() {
        return vod_ad_frequency;
    }

    public void setVod_ad_frequency(long vod_ad_frequency) {
        this.vod_ad_frequency = vod_ad_frequency;
    }

    public long getVod_ad_length() {
        return vod_ad_length;
    }

    public void setVod_ad_length(long vod_ad_length) {
        this.vod_ad_length = vod_ad_length;
    }
}
