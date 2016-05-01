package com.vnapnic.streamgames.ui.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.vnapnic.streamgames.R;
import com.vnapnic.streamgames.data.model.TwitchStream;
import com.vnapnic.streamgames.data.model.TwitchStreamElement;
import com.vnapnic.streamgames.data.service.TDServiceImpl;
import com.vnapnic.streamgames.data.worker.TDTaskManager;
import com.vnapnic.streamgames.ui.adapter.SearchAdapter;
import com.vnapnic.streamgames.ui.widget.EmptyView;
import com.vnapnic.streamgames.util.Log;
import com.vnapnic.streamgames.util.VideoPlayer;

public class SearchFragment extends TDListFragment<TwitchStream> implements AdapterView.OnItemClickListener {

    public static final String QUERY = "query";
    public static final String OFFSET = "offset";

    private SearchAdapter adapter;
    private EmptyView emptyView;
    private String query;
    private int offset;

    @Override
    protected int onCreateView() {
        return R.layout.list;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (savedInstanceState != null) {
            query = savedInstanceState.getString(QUERY);
            offset = savedInstanceState.getInt(OFFSET);
        }

        if (adapter == null || adapter.getCount() == 0) {
            adapter = new SearchAdapter(getActivity());

            setListAdapter(adapter);
        }
        setOnItemClickListener(this);

        emptyView = (EmptyView) getListView().getEmptyView();
        if (emptyView != null) {
            emptyView.setText(R.string.search_no_result);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString(QUERY, query);
        outState.putInt(OFFSET, offset);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onResume() {
        super.onResume();
        getTDActivity().setTitle(R.string.live_stream_title);
        getTDActivity().getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        getTDActivity().setShowAds();
    }

    @Override
    public void loadData() {
        if (emptyView != null) {
            emptyView.showProgress();
        }
        TDTaskManager.executeTask(this);
    }

    @Override
    public void refreshData() {
        adapter.clear();
        loadData();
    }

    @Override
    public TwitchStream startRequest() {
        return TDServiceImpl.getInstance().searchStreams(query, offset);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getTDActivity().setGoneAds();
    }

    @Override
    public void onResponse(TwitchStream response) {
        adapter.setData(response.getStreams());
        if (emptyView != null) {
            emptyView.showText();
        }
        Log.d(this, "SearchResult: " + response.getStreams());
    }

    public void setQuery(String query) {
        this.query = query;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        TwitchStreamElement stream = adapter.getItem(i);
        if (stream != null) {
            TDTaskManager.executeTask(new VideoPlayer.StreamPlaylistCallback(this, stream.getChannel().getName()));
        }
    }
}
