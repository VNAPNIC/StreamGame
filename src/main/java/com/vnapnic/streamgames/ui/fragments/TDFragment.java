package com.vnapnic.streamgames.ui.fragments;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.Optional;

import com.vnapnic.streamgames.R;
import com.vnapnic.streamgames.config.TDConfig;
import com.vnapnic.streamgames.data.worker.TDCallback;
import com.vnapnic.streamgames.ui.TDActivity;
import com.vnapnic.streamgames.ui.widget.EmptyView;
import com.vnapnic.streamgames.util.CheckNetwok;

public abstract class TDFragment<Result> extends Fragment implements TDBase, TDCallback<Result> {

    private TDActivity activity;
    private Bundle args;
    protected boolean hasUsername;
    @Optional
    @InjectView(android.R.id.empty)
    EmptyView emptyView;

    public static <T extends Fragment> T instantiate(Class<T> clazz) {
        return instantiate(clazz, null);
    }

    public static <T extends Fragment> T instantiate(Class<T> clazz, Bundle args) {
        try {
            T fragment = clazz.newInstance();
            if (args != null) {
                args.setClassLoader(clazz.getClassLoader());
                fragment.setArguments(args);
            }
            return fragment;
        } catch (Exception e) {
            throw new InstantiationException("Unable to instantiate fragment " + clazz
                    + ": make sure class name exists, is public, and has an"
                    + " empty constructor that is public", e);
        }
    }

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
            throw new IllegalStateException("TDFragment must be attached to a TDActivity.");
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

    protected EmptyView getEmptyView() {
        return emptyView;
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
        try {
            activity.onError(title, message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public abstract void loadData();

    public void refreshData() {
        loadData();
    }

    public TDActivity getTDActivity() {
        return activity;
    }

    public ActionBar getSupportActionBar() {
        return activity.getSupportActionBar();
    }

    @Override
    public SharedPreferences getDefaultSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(activity);
    }

    @Override
    public void setArgs(Bundle args) {
        this.args = args;
    }

    @Override
    public Bundle getArgs() {
        if (args == null) {
            args = new Bundle();
        }
        return args;
    }
}
