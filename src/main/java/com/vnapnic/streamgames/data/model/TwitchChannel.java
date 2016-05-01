package com.vnapnic.streamgames.data.model;

import java.util.List;

import com.vnapnic.streamgames.R;


public class TwitchChannel extends TwitchBase {

    private int _id;
    private String background;
    private String banner;
    private Status channelStatus;
    private Long delay;
    private String display_name;
    private long followers;
    private long views;
    private String game;
    private TwitchLogo logo;
    private boolean mature;
    private String name;
    private boolean partner;
    private List<TwitchChannel> teams;
    private String title;
    private String status;
    private String url;
    private String video_banner;


    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public Status getChannelStatus() {
        if (channelStatus == null) {
            channelStatus = Status.UNKNOWN;
        }
        return channelStatus;
    }

    public void setChannelStatus(Status channelStatus) {
        this.channelStatus = channelStatus;
    }

    public Long getDelay() {
        return delay;
    }

    public void setDelay(Long delay) {
        this.delay = delay;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public long getFollowers() {
        return followers;
    }

    public void setFollowers(long followers) {
        this.followers = followers;
    }

    public long getViews() {
        return views;
    }

    public void setViews(long views) {
        this.views = views;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public TwitchLogo getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = new TwitchLogo(logo);
    }

    public boolean isMature() {
        return mature;
    }

    public void setMature(boolean mature) {
        this.mature = mature;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPartner() {
        return partner;
    }

    public void setPartner(boolean partner) {
        this.partner = partner;
    }

    public List<TwitchChannel> getTeams() {
        return teams;
    }

    public void setTeams(List<TwitchChannel> teams) {
        this.teams = teams;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getVideo_banner() {
        return video_banner;
    }

    public void setVideo_banner(String video_banner) {
        this.video_banner = video_banner;
    }

    public enum Status {
        ONLINE,
        OFFLINE,
        UNKNOWN;

        public int getText() {
            switch (this) {
                case ONLINE:
                    return R.string.channel_online;
                case OFFLINE:
                    return R.string.channel_offline;
            }
            return R.string.channel_unknown;
        }
    }
}
