package com.vnapnic.streamgames.data.service;

import com.vnapnic.streamgames.data.model.TwitchAccessToken;
import com.vnapnic.streamgames.data.model.TwitchBroadcast;
import com.vnapnic.streamgames.data.model.TwitchChannel;
import com.vnapnic.streamgames.data.model.TwitchChannels;
import com.vnapnic.streamgames.data.model.TwitchFeatureds;
import com.vnapnic.streamgames.data.model.TwitchFollows;
import com.vnapnic.streamgames.data.model.TwitchGames;
import com.vnapnic.streamgames.data.model.TwitchStream;
import com.vnapnic.streamgames.data.model.TwitchVideos;
import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

public interface TDService {

    public interface TwitchKraken {

        @GET("/users/{username}/follows/channels?limit=25")
        public TwitchFollows getFollows(@Path("username") String username, @Query("offset") int offset);

        @GET("/channels/{channel}")
        public TwitchChannel getChannel(@Path("channel") String channel);

        @GET("/streams/{channel}")
        public TwitchStream getStream(@Path("channel") String channel);

        @GET("/streams?limit=54")
        public TwitchStream getStreams(@Query("game") String game, @Query("offset") int offset);

        @GET("/streams/featured")
        public TwitchFeatureds getFeatured(@Query("limit") int limit, @Query("offset") int offset);

        @GET("/channels/{channel}/videos?limit=10&broadcasts=true")
        public TwitchVideos getVideos(@Path("channel") String channel, @Query("offset") int offset);

        @GET("/search/streams?limit=20")
        public TwitchStream searchStreams(@Query("query") String query, @Query("offset") int offset);

        @GET("/search/channels?limit=20")
        public TwitchChannels searchChannels(@Query("query") String query, @Query("offset") int offset);

        @GET("/games/top")
        public TwitchGames getTopGames(@Query("limit") int limit, @Query("offset") int offset);
    }

    public interface TwitchAPI {

        @GET("/channels/{channel}/access_token")
        public TwitchAccessToken getChannelToken(@Path("channel") String channel);

        @GET("/vods/{videoId}/access_token")
        public TwitchAccessToken getVodToken(@Path("videoId") String videoId);

        @GET("/videos/{id}?as3=t")
        public TwitchBroadcast getVideoPlaylist(@Path("id") String id);
    }

    public interface TwitchUsher {

        @GET("/api/channel/hls/{channel}.json?allow_source=true&allow_audio_only=false&type=any&player=twitchweb")
        public Response getChannelPlaylist(@Path("channel") String channel, @Query("p") String p, @Query("token") String token, @Query("sig") String sig);

        @GET("/vod/{videoId}?allow_source=true&allow_audio_only=false&type=any&player=twitchweb")
        public Response getVodPlaylist(@Path("videoId") String videoId, @Query("p") String p, @Query("nauth") String token, @Query("nauthsig") String sig);
    }
}
