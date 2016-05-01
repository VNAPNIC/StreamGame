package com.vnapnic.streamgames.ui.fragments;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import com.vnapnic.streamgames.R;
import com.vnapnic.streamgames.data.model.TwitchFeatured;
import com.vnapnic.streamgames.data.model.TwitchFeatureds;
import com.vnapnic.streamgames.data.model.TwitchGame;
import com.vnapnic.streamgames.data.service.TDServiceImpl;
import com.vnapnic.streamgames.data.worker.TDTaskManager;
import com.vnapnic.streamgames.ui.adapter.FeaturedAdapter;
import com.vnapnic.streamgames.ui.adapter.GameStreamsAdapter;
import com.vnapnic.streamgames.ui.widget.EmptyView;
import com.vnapnic.streamgames.ui.widget.GridView;
import com.vnapnic.streamgames.ui.widget.ListView;
import com.vnapnic.streamgames.util.Log;
import com.vnapnic.streamgames.util.VideoPlayer;

import butterknife.InjectView;

public class FeaturedFragment extends TDFragment<TwitchFeatureds> implements FeaturedAdapter.ActionFeatured, ListView.OnLastItemVisibleListener {

    private static final int LIMIT = 30;
    private static final int offset = 0;
    private FeaturedAdapter adapter;
    private boolean loadData = true;

    @InjectView(R.id.empty)
    EmptyView emptyView;
    @InjectView(R.id.list)
    ListView listView;

    @Override
    protected int onCreateView() {
        return R.layout.featured_fragment;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (adapter == null) {
            adapter = new FeaturedAdapter(getActivity());
        }
        adapter.setListener(this);
        listView.setEmptyView(emptyView);
        listView.setAdapter(adapter);
        listView.setOnLastItemVisibleListener(this);

    }

    @Override
    public void onResume() {
        super.onResume();
        getTDActivity().setTitle(R.string.fatureds_title);
        getTDActivity().getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_logo);
    }

    @Override
    public void loadData() {
        if (loadData) {
            TDTaskManager.executeTask(this);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        loadData = true;
        return super.onOptionsItemSelected(item);
    }

    @Override
    public TwitchFeatureds startRequest() {
        return TDServiceImpl.getInstance().getFeatured(LIMIT, offset);
    }

    @Override
    public void onResponse(TwitchFeatureds response) {
        if (response != null && response.getFeatured() != null) {
//            for (TwitchFeatured featureds : response.getFeatured()) {
//                Log.d("namit", "Game overview Fragment ..." + featureds.getTitle());
//            }
//            android.util.Log.d("namit", "emptyView = gone");
            adapter.addData(response);
            emptyView.setVisibility(View.GONE);
        }
    }

    @Override
    public void onLastItemVisible() {
//        offset += 10;
//        loadData();
    }


    @Override
    public void playVideo(int position) {
        TwitchFeatured featured = adapter.getItem(position);
        if (featured != null) {
            TDTaskManager.executeTask(new VideoPlayer.StreamPlaylistCallback(this, featured.getStream().getChannel().getName()));
            loadData = false;
        }
    }
}
