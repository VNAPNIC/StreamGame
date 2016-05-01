package com.vnapnic.streamgames.ui.fragments;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import com.vnapnic.streamgames.R;
import com.vnapnic.streamgames.data.model.TwitchGame;
import com.vnapnic.streamgames.data.model.TwitchGames;
import com.vnapnic.streamgames.data.model.TwitchGamesElement;
import com.vnapnic.streamgames.data.service.TDServiceImpl;
import com.vnapnic.streamgames.data.worker.TDTaskManager;
import com.vnapnic.streamgames.ui.adapter.GameOverviewAdapter;
import com.vnapnic.streamgames.ui.widget.EmptyView;
import com.vnapnic.streamgames.ui.widget.GridView;
import com.vnapnic.streamgames.util.Log;

import butterknife.InjectView;

public class GameOverviewFragment extends TDFragment<TwitchGames> implements GridView.OnLastItemVisibleListener, AdapterView.OnItemClickListener {

    private static final int LIMIT = 102;

    @InjectView(R.id.empty)
    EmptyView emptyView;
    @InjectView(R.id.gridview)
    GridView gridView;

    private GameOverviewAdapter adapter;
    private int offset = 0;
    private boolean loadData = true;


    public GameOverviewFragment() {
    }

    @Override
    protected int onCreateView() {
        return R.layout.game_overview;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (adapter == null) {
            adapter = new GameOverviewAdapter(getActivity());
        }
        gridView.setEmptyView(emptyView);
        gridView.setAdapter(adapter);
        gridView.setOnLastItemVisibleListener(this);
        gridView.setOnItemClickListener(this);
    }

    @Override
    public void loadData() {
        if (loadData) {
            TDTaskManager.executeTask(this);
        }
    }

    @Override
    public TwitchGames startRequest() {
        return TDServiceImpl.getInstance().getTopGames(LIMIT, offset);
    }

    @Override
    public void onResponse(TwitchGames response) {
        if (response != null && response.getTop() != null) {
//            for (TwitchGamesElement game : response.getTop()) {
//                Log.d("namit","Game overview Fragment ..."+ game.getGame().getName());
//            }
//            android.util.Log.d("namit", "emptyView = gone");
            adapter.addData(response);
            emptyView.setVisibility(View.GONE);
            try {
                ((HomeFragment) getParentFragment()).loadSuccess();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onLastItemVisible() {
//        offset += LIMIT;
//        if (adapter.getTotalCount() > offset + LIMIT) {
//            loadData = true;
//            loadData();
//        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        TwitchGame game = adapter.getItem(position).getGame();
        if (game != null) {
            Log.d(this, game.getName());

            Bundle args = new Bundle();
            args.putSerializable(GameStreamsFragment.GAME, game);
            getTDActivity().showStreams(args);
            loadData = false;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        loadData = true;
        return super.onOptionsItemSelected(item);
    }
}
