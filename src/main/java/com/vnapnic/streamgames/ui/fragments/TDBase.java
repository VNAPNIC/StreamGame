package com.vnapnic.streamgames.ui.fragments;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.vnapnic.streamgames.ui.TDActivity;

public interface TDBase {

    public void loadData();

    public void startActivity(Intent intent);

    public SharedPreferences getDefaultSharedPreferences();

    public boolean isAdded();

    public TDActivity getTDActivity();

    public void setArgs(Bundle args);

    public Bundle getArgs();
}
