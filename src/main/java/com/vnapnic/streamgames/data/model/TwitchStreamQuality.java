package com.vnapnic.streamgames.data.model;

public class TwitchStreamQuality extends TwitchBase {

    private String key;
    private String name;
    private int value;

    public TwitchStreamQuality(String key, int value) {
        this(key, value, key);
    }

    public TwitchStreamQuality(String key, int value, String name) {
        this.key = key;
        this.value = value;
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
