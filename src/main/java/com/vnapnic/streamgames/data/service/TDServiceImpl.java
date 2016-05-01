package com.vnapnic.streamgames.data.service;

import com.vnapnic.streamgames.BuildConfig;
import com.vnapnic.streamgames.config.TDConfig;
import com.vnapnic.streamgames.data.model.TwitchAccessToken;
import com.vnapnic.streamgames.data.model.TwitchBroadcast;
import com.vnapnic.streamgames.data.model.TwitchChannel;
import com.vnapnic.streamgames.data.model.TwitchChannels;
import com.vnapnic.streamgames.data.model.TwitchFeatureds;
import com.vnapnic.streamgames.data.model.TwitchFollows;
import com.vnapnic.streamgames.data.model.TwitchGames;
import com.vnapnic.streamgames.data.model.TwitchStream;
import com.vnapnic.streamgames.data.model.TwitchVideos;
import com.vnapnic.streamgames.data.service.TDService.TwitchAPI;
import com.vnapnic.streamgames.data.service.TDService.TwitchKraken;
import com.vnapnic.streamgames.data.service.TDService.TwitchUsher;
import com.vnapnic.streamgames.util.Log;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.Response;
import retrofit.converter.JacksonConverter;
import retrofit.http.Path;
import retrofit.http.Query;

public class TDServiceImpl implements TwitchAPI, TwitchKraken, TwitchUsher, RestAdapter.Log {

    private static final String TAG = "TDService";

    private static TDServiceImpl instance;

    private TwitchAPI twitchAPI;
    private TwitchUsher twitchUsher;
    private TwitchKraken twitchKraken;

    private TDServiceImpl() {
        RestAdapter.Builder builder = new RestAdapter.Builder()
                .setLog(this)
                .setLogLevel(BuildConfig.DEBUG ? RestAdapter.LogLevel.FULL : RestAdapter.LogLevel.NONE)
                .setConverter(new JacksonConverter());

        RestAdapter apiAdapter = builder
                .setEndpoint(TDConfig.URL_API_TWITCH_API_BASE)
                .build();
        RestAdapter usherAdapter = builder
                .setEndpoint(TDConfig.URL_API_USHER_BASE)
                .build();
        RestAdapter krakenAdapter = builder
                .setEndpoint(TDConfig.URL_API_TWITCH_KRAKEN_BASE)
                .setRequestInterceptor(new KrakenRequestInterceptor())
                .build();

        twitchAPI = apiAdapter.create(TwitchAPI.class);
        twitchUsher = usherAdapter.create(TwitchUsher.class);
        twitchKraken = krakenAdapter.create(TwitchKraken.class);
    }

    public static TDServiceImpl getInstance() {
        if (instance == null) {
            instance = new TDServiceImpl();
        }
        return instance;
    }

    @Override
    public void log(String message) {
        Log.d(TAG, message);
    }

    private class KrakenRequestInterceptor implements RequestInterceptor {
        @Override
        public void intercept(RequestInterceptor.RequestFacade request) {
            request.addHeader("Accept", TDConfig.MIME_TWITCH);
            request.addHeader("Client-ID", TDConfig.KRAKEN_CLIENT_ID);
        }
    }

    @Override
    public TwitchFollows getFollows(String username, int offset) {
        return twitchKraken.getFollows(username, offset);
    }

    @Override
    public TwitchChannel getChannel(String channel) {
        return twitchKraken.getChannel(channel);
    }

    @Override
    public TwitchStream getStream(String channel) {
        return twitchKraken.getStream(channel);
    }

    @Override
    public TwitchStream getStreams(String game, int offset) {
        return twitchKraken.getStreams(game, offset);
    }

    @Override
    public TwitchFeatureds getFeatured(int limit, int offset) {
        return twitchKraken.getFeatured(limit, offset);
    }

    @Override
    public TwitchVideos getVideos(String channel, int offset) {
        return twitchKraken.getVideos(channel, offset);
    }

    @Override
    public TwitchStream searchStreams(String query, int offset) {
        return twitchKraken.searchStreams(query, offset);
    }

    @Override
    public TwitchChannels searchChannels(String query, int offset) {
        return twitchKraken.searchChannels(query, offset);
    }

    @Override
    public TwitchGames getTopGames(int limit, int offset) {
        return twitchKraken.getTopGames(limit, offset);
    }

    //TwitchAPI
    @Override
    public TwitchAccessToken getChannelToken(String channel) {
        return twitchAPI.getChannelToken(channel);
    }

    @Override
    public TwitchAccessToken getVodToken(@Path("videoId") String videoId) {
        return twitchAPI.getVodToken(videoId);
    }

    @Override
    public TwitchBroadcast getVideoPlaylist(@Path("id") String id) {
        return twitchAPI.getVideoPlaylist(id);
    }

    //TwitchUsher
    @Override
    public Response getChannelPlaylist(String channel, String p, String token, String sig) {
        return twitchUsher.getChannelPlaylist(channel, p, token, sig);
    }

    @Override
    public Response getVodPlaylist(@Path("videoId") String videoId, @Query("p") String p, @Query("token") String token, @Query("sig") String sig) {
        return twitchUsher.getVodPlaylist(videoId, p, token, sig);
    }
}