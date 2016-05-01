package com.vnapnic.streamgames.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import com.vnapnic.streamgames.R;
import com.vnapnic.streamgames.data.model.TwitchChunk;

public class PlaylistAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private List<TwitchChunk> videos;
    private ArrayList<Integer> played;

    public PlaylistAdapter(Context context, List<TwitchChunk> videos) {
        super();
        this.inflater = LayoutInflater.from(context);
        this.videos = videos;
        this.played = new ArrayList<Integer>(videos.size());
    }

    public void setVideos(List<TwitchChunk> videos) {
        this.videos = videos;
        if (videos != null && videos.size() > 0) {
            notifyDataSetChanged();
        } else {
            notifyDataSetInvalidated();
        }
    }

    public void setPlayed(int position) {
        played.add(position);
    }

    @Override
    public int getCount() {
        return videos.size();
    }

    @Override
    public TwitchChunk getItem(int position) {
        if (position < videos.size()) {
            return videos.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TwitchChunk video = getItem(position);
        ViewHolder holder;
        if (convertView == null || convertView.getTag() == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.list_item_playlist, parent, false);
            if (convertView != null) {
                ButterKnife.inject(holder, convertView);
            }
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.lblPartIndex.setText(String.valueOf(position + 1));
        holder.lblPartCount.setText(String.valueOf(getCount()));
        //TODO holder.lblTitle.setText(video.());
        holder.chkPlayed.setChecked(played.contains(position));
        return convertView;
    }

    class ViewHolder {
        @InjectView(R.id.lblPartIndex) TextView lblPartIndex;
        @InjectView(R.id.lblPartCount) TextView lblPartCount;
        @InjectView(R.id.lblTitle) TextView lblTitle;
        @InjectView(R.id.chkPlayed) CheckBox chkPlayed;
    }
}
