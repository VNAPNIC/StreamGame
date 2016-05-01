package com.vnapnic.streamgames.data.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Date;

import com.vnapnic.streamgames.TDApplication;

@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class TwitchBase implements Serializable {

    private String error;
    private Date created_at;
    private Date updated_at;
    private TwitchLinks _links;
    private int _total;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setErrorResId(int errorResId) {
        this.error = TDApplication.getContext().getString(errorResId);
    }

    public boolean hasError() {
        return error != null;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public TwitchLinks get_links() {
        return _links;
    }

    public void set_links(TwitchLinks _links) {
        this._links = _links;
    }

    public int get_total() {
        return _total;
    }

    public void set_total(int _total) {
        this._total = _total;
    }
}
