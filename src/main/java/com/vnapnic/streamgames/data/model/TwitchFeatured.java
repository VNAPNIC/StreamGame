package com.vnapnic.streamgames.data.model;

/**
 * Created by vn apnic on 4/27/2016.
 */
public class TwitchFeatured extends TwitchBase {
    private String text;
    private String image;
    private String title;
    private TwitchStreamElement stream;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public TwitchStreamElement getStream() {
        return stream;
    }

    public void setStream(TwitchStreamElement stream) {
        this.stream = stream;
    }
}
