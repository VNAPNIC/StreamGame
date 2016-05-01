package com.vnapnic.streamgames.data.model;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;

public class TwitchPlayList extends TwitchBase {

    public static final TwitchStreamQuality QUALITY_MOBILE = new TwitchStreamQuality("mobile", 1);
    public static final TwitchStreamQuality QUALITY_LOW = new TwitchStreamQuality("low", 2);
    public static final TwitchStreamQuality QUALITY_MEDIUM = new TwitchStreamQuality("medium", 3);
    public static final TwitchStreamQuality QUALITY_HIGH = new TwitchStreamQuality("high", 4);
    public static final TwitchStreamQuality QUALITY_CHUNKED = new TwitchStreamQuality("chunked", 5, "source");

    public static final TwitchStreamQuality[] SUPPORTED_QUALITIES = {
            QUALITY_MOBILE,
            QUALITY_LOW,
            QUALITY_MEDIUM,
            QUALITY_HIGH,
            QUALITY_CHUNKED
    };

    private HashMap<TwitchStreamQuality, String> streams;

    public static TwitchStreamQuality parseQuality(String name) {
        for (TwitchStreamQuality quality : SUPPORTED_QUALITIES) {
            if (quality.getName().equalsIgnoreCase(name) || quality.getKey().equalsIgnoreCase(name)) {
                return quality;
            }
        }
        return null;
    }

    public String getStream(TwitchStreamQuality quality) {
        String url = streams.get(quality);
        if (StringUtils.isEmpty(url)) {
            url = getBestStream();
        }
        return url;
    }

    public String getBestStream() {
        TwitchStreamQuality best = new TwitchStreamQuality("", -1);

        for (TwitchStreamQuality quality : streams.keySet()) {
            if (best.getValue() < quality.getValue()) {
                best = quality;
            }
        }

        if (best.getValue() > -1) {
            return getStream(best);
        }
        return null;
    }

    public HashMap<TwitchStreamQuality, String> getStreams() {
        return streams;
    }

    public void setStreams(HashMap<TwitchStreamQuality, String> streams) {
        this.streams = streams;
    }
}
