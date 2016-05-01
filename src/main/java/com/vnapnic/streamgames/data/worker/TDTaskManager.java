package com.vnapnic.streamgames.data.worker;

import android.annotation.TargetApi;
import android.os.AsyncTask;
import android.os.Build;

import java.util.concurrent.CopyOnWriteArrayList;

import com.vnapnic.streamgames.data.model.TwitchBase;
import com.vnapnic.streamgames.ui.fragments.FishLoad;

public class TDTaskManager {

    private static CopyOnWriteArrayList<TDTask> tasks = new CopyOnWriteArrayList<TDTask>();

    public static void removeTask(TDTask task) {
        removeTask(task, false);
    }

    public static void removeTask(TDTask task, boolean cancel) {
        if (cancel) {
            task.cancel(true);
        }
        tasks.remove(task);
    }

    public static void cancelAllTasks() {
        for (TDTask task : tasks) {
            removeTask(task, true);
        }
    }

    @TargetApi(11)
       public static <Result extends TwitchBase> void executeTask(TDCallback<Result> callback) {
        TDTask<Result> task = new TDTask<Result>(callback);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        } else {
            task.execute();
        }
        tasks.add(task);
    }

}
