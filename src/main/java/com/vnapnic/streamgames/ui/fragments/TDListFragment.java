package com.vnapnic.streamgames.ui.fragments;


import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ListFragment;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.Optional;

import com.vnapnic.streamgames.config.TDConfig;
import com.vnapnic.streamgames.data.model.TwitchBase;
import com.vnapnic.streamgames.data.worker.TDCallback;
import com.vnapnic.streamgames.ui.TDActivity;
import com.vnapnic.streamgames.ui.widget.EmptyView;
import com.vnapnic.streamgames.ui.widget.ListView;

public abstract class TDListFragment<Result extends TwitchBase> extends ListFragment implements TDBase, TDCallback<Result> {

    private TDActivity activity;
    private Bundle args;
    protected boolean hasUsername;
    @Optional
    @InjectView(android.R.id.empty)
    EmptyView emptyView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hasUsername = getArgs().getBoolean(TDConfig.SETTINGS_CHANNEL_NAME);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof TDActivity) {
            this.activity = (TDActivity) activity;
        } else {
            throw new IllegalStateException("TDListFragment must be attached to a TDActivity.");
        }
    }

    @Override
    public final View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(onCreateView(), container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    protected abstract int onCreateView();

    @Override
    public void onResume() {
        super.onResume();
        refreshData();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getSupportActionBar().setTitle("");
    }

    @Override
    public ListView getListView() {
        return (ListView) super.getListView();
    }

    public ActionBar getSupportActionBar() {
        return activity.getSupportActionBar();
    }

    @Override
    public SharedPreferences getDefaultSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(activity);
    }

    public void startLoading() {
        if (activity != null) {
            activity.startLoading();
            if (emptyView != null) {
                emptyView.showProgress();
            }
        }
    }

    public void stopLoading() {
        if (activity != null) {
            activity.stopLoading();
        }
        if (emptyView != null) {
            emptyView.showText();
        }
    }

    @Override
    public void onError(String title, String message) {
        activity.onError(title, message);
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        if (getListView() != null) {
            getListView().setOnItemClickListener(onItemClickListener);
        }
    }

    public void setOnLastItemVisibleListener(ListView.OnLastItemVisibleListener onLastItemVisibleListener) {
        if (getListView() != null) {
            getListView().setOnLastItemVisibleListener(onLastItemVisibleListener);
        }
    }

    public abstract void loadData();

    public void refreshData() {
        loadData();
    }

    public TDActivity getTDActivity() {
        return activity;
    }

    @Override
    public void setArgs(Bundle args) {
        this.args = args;
    }

    @Override
    public Bundle getArgs() {
        if (args == null) {
            if (getArguments() != null) {
                args = getArguments();
            } else {
                args = new Bundle();
            }
        }
        return args;
    }
}
