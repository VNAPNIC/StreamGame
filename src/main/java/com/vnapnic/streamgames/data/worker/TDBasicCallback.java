package com.vnapnic.streamgames.data.worker;

import com.vnapnic.streamgames.ui.fragments.TDListFragment;

public abstract class TDBasicCallback<Result> implements TDCallback<Result> {

    private Object caller;

    protected TDBasicCallback(Object caller) {
        this.caller = caller;
    }

    @Override
    public void startLoading() {
        if (caller instanceof TDListFragment) {
            ((TDListFragment) caller).startLoading();
        }
    }

    @Override
    public void stopLoading() {
        if (caller instanceof TDListFragment) {
            ((TDListFragment) caller).stopLoading();
        }
    }

    @Override
    public void onError(String title, String message) {
        if (caller instanceof TDListFragment) {
            ((TDListFragment) caller).onError(title, message);
        }
    }
}
