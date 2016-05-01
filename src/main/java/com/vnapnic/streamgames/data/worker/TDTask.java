package com.vnapnic.streamgames.data.worker;

import android.content.Context;
import android.os.AsyncTask;

import com.vnapnic.streamgames.R;
import com.vnapnic.streamgames.TDApplication;
import com.vnapnic.streamgames.data.model.TwitchBase;
import com.vnapnic.streamgames.data.model.TwitchError;
import com.vnapnic.streamgames.util.Log;
import retrofit.RetrofitError;

public class TDTask<Result extends TwitchBase> extends AsyncTask<Void, Void, TwitchBase> {

    protected Context context;
    protected TDCallback<Result> callback;

    public TDTask(TDCallback<Result> callback) {
        this.callback = callback;
        this.context = TDApplication.getContext();
    }

    @Override
    protected void onPreExecute() {
        callback.startLoading();
        super.onPreExecute();
    }

    @Override
    protected TwitchBase doInBackground(Void... params) {
        TwitchBase result;
        try {
            result = callback.startRequest();
        } catch (RetrofitError retrofitError) {
            result = new TwitchError(); //No need for class in constructor
            switch (retrofitError.getKind()) {
                case NETWORK:
                    result.setErrorResId(R.string.error_connection_error_message);
                    break;
                case CONVERSION:
                case HTTP:
                    if (retrofitError.getResponse().getStatus() != 502) { //Bad Gateway can be ignored
                        result.setErrorResId(R.string.error_data_error_message);
                    }
                    break;
                case UNEXPECTED:
                default:
                    result.setErrorResId(R.string.error_unexpected);
            }
            Log.e(this, retrofitError);
        }
        if (result != null) {
            return result;
        }
        return null;
    }

    @Override
    protected void onPostExecute(TwitchBase result) {
        super.onPostExecute(result);
        if (result != null) {
            if (!result.hasError()) {
                if (callback.isAdded()) {
                    callback.onResponse((Result) result);
                }
            } else {
                callback.onError(getString(R.string.dialog_error_title), result.getError());
            }

        } else {
            callback.onError(getString(R.string.error_connection_error_title), getString(R.string.error_connection_error_message));
        }
        callback.stopLoading();
        TDTaskManager.removeTask(this);
    }

    protected String getString(int resId) {
        return context.getString(resId);
    }
}
