package com.vnapnic.streamgames.ui.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.vnapnic.streamgames.R;
import com.vnapnic.streamgames.data.model.TwitchFeatured;
import com.vnapnic.streamgames.data.model.TwitchFeatureds;
import com.vnapnic.streamgames.data.model.TwitchLogo;
import com.vnapnic.streamgames.data.model.TwitchStreamElement;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by vn apnic on 4/27/2016.
 */
public class FeaturedAdapter extends BaseAdapter {

    private List<TwitchFeatured> data;
    private LayoutInflater inflater;
    private Picasso picasso;
    private ActionFeatured listener;

    public FeaturedAdapter(Context context) {
        init(context);
    }

    private void init(Context context) {
        this.inflater = LayoutInflater.from(context);
        this.picasso = Picasso.with(context);
        this.data = new ArrayList<TwitchFeatured>();
    }

    public void addData(TwitchFeatureds featureds) {
        this.data.clear();
        this.data.addAll(featureds.getFeatured());
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (data != null) {
            return data.size();
        }
        return 0;
    }

    @Override
    public TwitchFeatured getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TwitchFeatured featured = data.get(position);
        ViewHolder holder;
        if (convertView == null || convertView.getTag() == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.list_item_featured, parent, false);
            if (convertView != null) {
                ButterKnife.inject(holder, convertView);
            }
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        picasso.load(featured.getStream().getChannel().getLogo()
                .getUrl(TwitchLogo.Size.SMALL))
                .placeholder(R.drawable.default_channel_logo_small)
                .into(holder.logo);

        picasso.load(featured.getStream().getPreview().getLarge())
                .placeholder(R.drawable.default_banner)
                .into(holder.banner);

        holder.lblAuthor.setText(featured.getStream().getChannel().getDisplay_name());
        holder.lblStatus.setText(featured.getStream().getChannel().getStatus());
        holder.lblViewers.setText(String.valueOf(featured.getStream().getChannel().getViews()));
        holder.lblText.setText(Html.fromHtml(featured.getText()));
        holder.screenVideo.setOnClickListener(GetActionClick.newInstace(this.listener, position));

        return convertView;
    }

    public void setListener(ActionFeatured listener) {
        this.listener = listener;
    }

    static class GetActionClick implements View.OnClickListener {
        private ActionFeatured Listener;
        private int position;

        public GetActionClick(ActionFeatured listener, int position) {
            this.Listener = listener;
            this.position = position;
        }

        public static GetActionClick newInstace(ActionFeatured listener, int position) {
            GetActionClick actionClick = new GetActionClick(listener, position);
            return actionClick;
        }

        @Override
        public void onClick(View v) {
            if (Listener != null) {
                Listener.playVideo(this.position);
            }
        }
    }

    public interface ActionFeatured {
        void playVideo(int position);
    }

    static class ViewHolder {
        @InjectView(R.id.screen_video)
        RelativeLayout screenVideo;
        @InjectView(R.id.banner_featured)
        ImageView banner;
        @InjectView(R.id.img_logo)
        CircleImageView logo;
        @InjectView(R.id.lblAuthor)
        TextView lblAuthor;
        @InjectView(R.id.lblStatus)
        TextView lblStatus;
        @InjectView(R.id.lblText)
        TextView lblText;
        @InjectView(R.id.lblViewers)
        TextView lblViewers;

    }
}
