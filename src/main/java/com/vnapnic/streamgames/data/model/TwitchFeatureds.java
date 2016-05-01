package com.vnapnic.streamgames.data.model;

import java.util.ArrayList;

/**
 * Created by vn apnic on 4/27/2016.
 */
public class TwitchFeatureds extends TwitchBase {
    ArrayList<TwitchFeatured> featured;

    public ArrayList<TwitchFeatured> getFeatured() {
        return featured;
    }

    public void setFeatured(ArrayList<TwitchFeatured> featured) {
        this.featured = featured;
    }
}
