package com.vnapnic.streamgames.data.model;

import java.util.ArrayList;

public class TwitchGames extends TwitchBase {

    private int _total;
    private ArrayList<TwitchGamesElement> top;

    public int get_total() {
        return _total;
    }

    public void set_total(int _total) {
        this._total = _total;
    }

    public ArrayList<TwitchGamesElement> getTop() {
        return top;
    }

    public void setTop(ArrayList<TwitchGamesElement> top) {
        this.top = top;
    }
}
