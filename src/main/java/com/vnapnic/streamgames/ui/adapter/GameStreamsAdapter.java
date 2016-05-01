package com.vnapnic.streamgames.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import com.vnapnic.streamgames.R;
import com.vnapnic.streamgames.data.model.TwitchStreamElement;
import com.vnapnic.streamgames.util.FormatUtils;
import com.vnapnic.streamgames.util.MarqueeHelper;

public class GameStreamsAdapter extends BaseAdapter {

    private List<TwitchStreamElement> data;
    private LayoutInflater inflater;
    private Picasso picasso;

    public GameStreamsAdapter(Context context, List<TwitchStreamElement> data) {
        init(context, data);
    }

    private void init(Context context, List<TwitchStreamElement> data) {
        this.data = data;
        this.inflater = LayoutInflater.from(context);
        this.picasso = Picasso.with(context);
    }

    public void setData(List<TwitchStreamElement> data) {
        if (data != null) {
            if (getCount() == 0) {
                this.data = data;
            } else {
                this.data.addAll(data);
            }
            notifyDataSetChanged();
        }
    }

    public void clear() {
        data.clear();
        notifyDataSetInvalidated();
    }

    @Override
    public int getCount() {
        if (data != null) {
            return data.size();
        }
        return 0;
    }

    @Override
    public TwitchStreamElement getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TwitchStreamElement stream = data.get(position);
        ViewHolder holder;
        if (convertView == null || convertView.getTag() == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.list_item_games, parent, false);
            if (convertView != null) {
                ButterKnife.inject(holder, convertView);
            }
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        picasso.load(stream.getPreview().getMedium()).placeholder(R.drawable.default_archive_thumbnail).into(holder.imgThumbnail);
        MarqueeHelper.setupMarquee(holder.lblTitle, stream.getChannel().getStatus());
        MarqueeHelper.setupMarquee(holder.lblChannel, stream.getChannel().getName());
        MarqueeHelper.setupMarquee(holder.lblViewers, FormatUtils.formatNumber(stream.getViewers()));
        return convertView;
    }

    static class ViewHolder {
        @InjectView(R.id.imgThumbnail) ImageView imgThumbnail;
        @InjectView(R.id.lblTitle) TextView lblTitle;
        @InjectView(R.id.lblChannel) TextView lblChannel;
        @InjectView(R.id.lblViewers) TextView lblViewers;
    }
}
