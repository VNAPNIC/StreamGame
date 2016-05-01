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

import com.vnapnic.streamgames.R;
import com.vnapnic.streamgames.data.model.TwitchVideo;
import com.vnapnic.streamgames.util.FormatUtils;

public class ArchiveAdapter extends BaseAdapter {

    private List<TwitchVideo> data;
    private LayoutInflater inflater;
    private Picasso picasso;

    public ArchiveAdapter(Context context, List<TwitchVideo> data) {
        init(context, data);
    }

    private void init(Context context, List<TwitchVideo> data) {
        this.data = data;
        this.inflater = LayoutInflater.from(context);
        this.picasso = Picasso.with(context);
    }

    public void setData(List<TwitchVideo> data) {
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
    public TwitchVideo getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TwitchVideo video = getItem(position);
        ViewHolder holder;
        if (convertView == null || convertView.getTag() == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.list_item_videos, parent, false);
            if (convertView != null) {
                holder.imgThumbnail = (ImageView) convertView.findViewById(R.id.imgThumbnail);
                holder.lblTitle = (TextView) convertView.findViewById(R.id.lblTitle);
                holder.lblDate = (TextView) convertView.findViewById(R.id.lblDate);
                holder.lblDuration = (TextView) convertView.findViewById(R.id.lblDuration);
            }
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        picasso.load(video.getPreview()).fit().placeholder(R.drawable.default_archive_thumbnail).into(holder.imgThumbnail);
        holder.lblTitle.setText(video.getTitle());
        holder.lblDate.setText(FormatUtils.formateDate(video.getRecorded_at()));
        holder.lblDuration.setText(FormatUtils.formatTime(video.getLength()));
        return convertView;
    }

    private class ViewHolder {
        ImageView imgThumbnail;
        TextView lblTitle;
        TextView lblDate;
        TextView lblDuration;
    }
}
