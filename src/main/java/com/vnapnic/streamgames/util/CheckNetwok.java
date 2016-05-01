package com.vnapnic.streamgames.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * Created by vn apnic on 4/27/2016.
 */
public class CheckNetwok {
    public static String TAG = "Check Internet";


    public static boolean isWiFiConnected(Context ctx) {
        ConnectivityManager cm = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        return ni != null;
    }


    public static Boolean isNetworkConnected(Context ctx) {

        ExecutorService service = Executors.newFixedThreadPool(10);

        FutureTask<Boolean> connectionTask = new FutureTask<Boolean>(new CheckConnection(ctx));
        service.submit(connectionTask);
        try {
            return connectionTask.get();
        } catch (java.util.concurrent.ExecutionException ex) {
            ex.printStackTrace();
            return false;
        } catch (InterruptedException ex) {
            ex.printStackTrace();
            return false;
        }


    }


    public static class CheckConnection implements Callable {
        Context ctx;

        public CheckConnection(Context ctx) {
            this.ctx = ctx;
        }

        @Override
        public Boolean call() {
            if (isWiFiConnected(ctx)) {
                try {
                    HttpURLConnection urlc = (HttpURLConnection)
                            (new URL("http://clients3.google.com/generate_204").openConnection());
                    urlc.setRequestProperty("User-Agent", "Android");
                    urlc.setRequestProperty("Connection", "close");
                    urlc.setConnectTimeout(1500);
                    urlc.connect();
                    return (urlc.getResponseCode() == 204 && urlc.getContentLength() == 0);
                } catch (IOException e) {
                    Log.e(TAG, "Error checking internet connection");
                }
            } else {
                Log.d(TAG, "No network available!");
            }
            return false;
        }

    }
}
