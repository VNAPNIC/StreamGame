package com.vnapnic.streamgames.ui.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import com.vnapnic.streamgames.R;
import com.vnapnic.streamgames.data.model.TwitchGame;
import com.vnapnic.streamgames.data.model.TwitchStream;
import com.vnapnic.streamgames.data.model.TwitchStreamElement;
import com.vnapnic.streamgames.data.service.TDServiceImpl;
import com.vnapnic.streamgames.data.worker.TDTaskManager;
import com.vnapnic.streamgames.ui.adapter.GameStreamsAdapter;
import com.vnapnic.streamgames.ui.widget.ListView;
import com.vnapnic.streamgames.util.VideoPlayer;

import org.apache.commons.lang3.StringUtils;

public class GameStreamsFragment extends TDListFragment<TwitchStream> implements AdapterView.OnItemClickListener, ListView.OnLastItemVisibleListener {

    public static final String GAME = "game";

    private GameStreamsAdapter adapter;
    private TwitchGame game;
    private int offset;

    @Override
    protected int onCreateView() {
        return R.layout.list;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getArgs().containsKey(GAME)) {
            game = (TwitchGame) getArgs().getSerializable(GAME);
            offset = 0;
        }
        setOnItemClickListener(this);

    }

    @Override
    public void onResume() {
        super.onResume();
        getTDActivity().setTitle(R.string.live_stream_title);
        getTDActivity().getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        getTDActivity().setShowAds();
    }

    @Override
    public void refreshData() {
        if (adapter != null) {
            adapter.clear();
        }
        loadData();
    }

    @Override
    public void loadData() {
        if (game != null) {
            if (StringUtils.isNotEmpty(game.getName())) {
                TDTaskManager.executeTask(this);
            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        TwitchStreamElement stream = adapter.getItem(position);
        if (stream != null) {
            TDTaskManager.executeTask(new VideoPlayer.StreamPlaylistCallback(this, stream.getChannel().getName()));
        }
    }

    @Override
    public TwitchStream startRequest() {
        return TDServiceImpl.getInstance().getStreams(game.getName(), offset);
    }

    @Override
    public void onResponse(TwitchStream response) {
        setOnLastItemVisibleListener(this);
        emptyView.setText(R.string.search_no_result);
        if (adapter == null) {
            adapter = new GameStreamsAdapter(getActivity(), response.getStreams());
            setListAdapter(adapter);
        } else {
            adapter.setData(response.getStreams());
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getTDActivity().setGoneAds();
    }

    @Override
    public void onLastItemVisible() {
//        offset += 10;
//        loadData();
    }


}
