package com.vnapnic.streamgames.util;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.widget.Toast;

import com.vnapnic.streamgames.R;
import com.vnapnic.streamgames.config.TDConfig;
import com.vnapnic.streamgames.data.model.TwitchAccessToken;
import com.vnapnic.streamgames.data.model.TwitchPlayList;
import com.vnapnic.streamgames.data.model.TwitchStreamQuality;
import com.vnapnic.streamgames.data.service.TDServiceImpl;
import com.vnapnic.streamgames.data.worker.TDBasicCallback;
import com.vnapnic.streamgames.ui.TDActivity;
import com.vnapnic.streamgames.ui.dialogs.ErrorDialogFragment;
import com.vnapnic.streamgames.ui.fragments.TDBase;

import net.chilicat.m3u8.Element;
import net.chilicat.m3u8.ParseException;
import net.chilicat.m3u8.Playlist;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import retrofit.client.Response;

public class VideoPlayer {
    private static final String TAG = "VideoPlayer";
    public static void playVideo(TDActivity activity, String title, String url) {
        if (activity != null) {
            Log.d(TAG, "Playing '" + title + "' from " + url);

            if (useInternPlayer(activity)) { //built-in Player
                //TODO
            } else { //external Player
                Log.d(TAG, "external");
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.parse(url), TDConfig.MIME_FLV);
                if (intent.getData() != null) {
                    try {
                        activity.startActivity(intent);
                    } catch (Exception exception) {
                        ErrorDialogFragment.ErrorDialogFragmentBuilder builder = new ErrorDialogFragment.ErrorDialogFragmentBuilder(activity);
                        builder.setTitle(R.string.error_no_player_title);
                        builder.setMessage(R.string.error_no_player_message);
                        builder.show();
                    }
                }
            }
        }
    }

    public static boolean useInternPlayer(TDActivity activity) {
        return false;
    }

    public static abstract class PlaylistCallback extends TDBasicCallback<TwitchPlayList> {

        protected TDBase fragment;
        protected TDActivity activity;
        protected TwitchAccessToken accessToken;

        public PlaylistCallback(TDBase caller) {
            super(caller);
            this.fragment = caller;
            this.activity = caller.getTDActivity();
        }

        protected abstract Response startPlaylistRequest();

        protected abstract TwitchAccessToken getAccessToken();

        protected abstract String getTitle();

        @Override
        public final TwitchPlayList startRequest() {
            TwitchPlayList twitchPlayList = new TwitchPlayList();

            accessToken = getAccessToken();
            accessToken.setP(String.valueOf((Math.random() * 999999)));

            Response response = startPlaylistRequest();
            try {
                Playlist playlist = Playlist.parse(response.getBody().in());
                twitchPlayList.setStreams(parsePlaylist(playlist));
            } catch (ParseException | IOException e) {
                Log.e(this, e);
            }
            return twitchPlayList;
        }

        private HashMap<TwitchStreamQuality, String> parsePlaylist(Playlist playlist) {
            HashMap<TwitchStreamQuality, String> streams = new HashMap<TwitchStreamQuality, String>();
            if (playlist != null) {
                List<Element> elements = playlist.getElements();
                if (elements != null && elements.size() > 0) {
                    for (Element element : elements) {
                        Log.d(TAG, "URI: " + element.getURI() + " Name: " + element.getPlayListInfo().getVideo());
                        TwitchStreamQuality quality = TwitchPlayList.parseQuality(element.getPlayListInfo().getVideo());
                        if (quality != null) {
                            streams.put(quality, element.getURI().toString());
                        }
                    }
                }
            }
            return streams;
        }

        @Override
        public void onResponse(TwitchPlayList response) {
            if (response != null && response.getStreams() != null) {
                Log.d(this, "Streams :" + response.getStreams().toString());
                if (response.getStreams() != null && response.getStreams().size() > 0) {
                    SharedPreferences sharedpreferences = activity.getSharedPreferences(TDActivity.QUALITY, Context.MODE_PRIVATE);
                    String quality = sharedpreferences.getString(TDActivity.QUALITY_VALUE, TwitchPlayList.QUALITY_MEDIUM.getKey());
                    TwitchStreamQuality streamQuality = TwitchPlayList.parseQuality(quality);
                    Log.d(this, "streamQuality: " + streamQuality.getName());
                    Toast.makeText(activity, String.format(activity.getResources().getString(R.string.toast_quality),
                                    activity.getResources().getStringArray(R.array.dialog_stream_quality_entries)[sharedpreferences.getInt(TDActivity.QUALITY_POSITION, 0)]),
                            Toast.LENGTH_SHORT).show();
                    String url = response.getStream(streamQuality);
                    if (url != null) {
                        playVideo(activity, getTitle(), url);
                        return;
                    }
                }
            }
            ErrorDialogFragment.ErrorDialogFragmentBuilder builder = new ErrorDialogFragment.ErrorDialogFragmentBuilder(activity);
            builder.setMessage(R.string.error_stream_offline).setTitle(R.string.dialog_error_title).show();
        }

        @Override
        public boolean isAdded() {
            return fragment.isAdded();
        }
    }


    public static class StreamPlaylistCallback extends PlaylistCallback {

        private String channel;

        public StreamPlaylistCallback(TDBase caller, String channel) {
            super(caller);
            this.channel = channel;
        }

        @Override
        protected String getTitle() {
            return channel;
        }

        @Override
        protected Response startPlaylistRequest() {
            return TDServiceImpl.getInstance().getChannelPlaylist(channel, accessToken.getP(), accessToken.getToken(), accessToken.getSig());
        }

        @Override
        protected TwitchAccessToken getAccessToken() {
            return TDServiceImpl.getInstance().getChannelToken(channel);
        }
    }
}
