package com.vnapnic.streamgames.data.model;

import java.util.HashMap;

public class TwitchLogo extends TwitchBase {

    public enum Size {
        HUGE("-600x600"),
        LARGE("-300x300"),
        MEDIUM("-150x150"),
        SMALL("-70x70"),
        TINY("-50x50");

        private String size;

        private Size(String size) {
            this.size = size;
        }
    }

    private HashMap<Size, String> logos;

    public TwitchLogo() {
        logos = new HashMap<Size, String>();
    }

    public TwitchLogo(String url) {
        logos = new HashMap<Size, String>();
        parseUrl(url);
    }

    private void parseUrl(String url) {
        if (url != null) {
            for (Size size : Size.values()) {
                logos.put(size, url.replaceAll("-(\\d+)x(\\d+)", size.size));
            }
        }
    }

    public void setUrl(String url) {
        parseUrl(url);
    }

    public String getUrl(Size size) {
        return logos.get(size);
    }
}