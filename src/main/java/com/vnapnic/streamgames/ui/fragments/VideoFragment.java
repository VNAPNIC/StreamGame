package com.vnapnic.streamgames.ui.fragments;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.vnapnic.streamgames.R;
import com.vnapnic.streamgames.ui.widget.EmptyView;

import butterknife.InjectView;

public class VideoFragment extends TDFragment {//implements MediaPlayer.OnPreparedListener {

    public static final String PLAYLIST = "playlist";
    public static final String TITLE = "title";
    public static final String URL = "url";

    @InjectView(android.R.id.empty) EmptyView emptyView;
    //    @InjectView(R.id.videoView) VideoView videoView;
//    @InjectView(R.id.player) View player;
//    @InjectView(R.id.chat) View chat;

    @Override
    protected int onCreateView() {
        return R.layout.video;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        super.setRetainInstance(true);

//        if (((TDActivity) getActivity()).isTablet()) {
//            emptyView.setImage(R.drawable.ic_glitchicon_white);
//        }

//        MediaController mediaController = new MediaController(getActivity());
//        videoView.setMediaController(mediaController);
//        videoView.setVideoLayout(VideoView.VIDEO_LAYOUT_SCALE, 1);
//        videoView.setOnPreparedListener(this);
//
//        onOrientationChange(getResources().getConfiguration().orientation);
//
//        playVideo();
    }

    @Override
    public void loadData() {

    }

    @Override
    public void onPause() {
        super.onPause();

        getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().show();
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

//        onOrientationChange(newConfig.orientation);
    }

    private void onOrientationChange(int orientation) {
//        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
//            getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
//            getSupportActionBar().hide();
//            videoView.setVideoLayout(VideoView.VIDEO_LAYOUT_STRETCH, 1.77f);
//            chat.setVisibility(View.GONE);
//        } else {
//            getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
//            getSupportActionBar().show();
//            videoView.setVideoLayout(VideoView.VIDEO_LAYOUT_SCALE, 1.77f);
//            chat.setVisibility(View.VISIBLE);
//        }
    }

    public void playVideo() {

//        getActivity().setVolumeControlStream(AudioManager.STREAM_MUSIC);
//        if (getArguments() != null && (!getArguments().containsKey(URL) || !getArguments().containsKey(CHUNKS))) {
//            getSupportActionBar().setTitle(getArguments().getString(TITLE));
//
//            if (getArguments().containsKey(URL)) {
//                videoView.setVideoURI(Uri.parse(getArguments().getString(URL)));
//            }
//            if (getArguments().containsKey(CHUNKS)) {
//                videoView.setVideoPlaylist(getArguments().getStringArray(CHUNKS));
//            }
//            videoView.start();
//        }
    }

    @Override
    public Object startRequest() {
        return null;
    }

    @Override
    public void onResponse(Object response) {

    }

//    @Override
//    public void onPrepared(MediaPlayer mp) {
//        emptyView.setVisibility(View.GONE);
//        player.setVisibility(View.VISIBLE);
//    }
}
