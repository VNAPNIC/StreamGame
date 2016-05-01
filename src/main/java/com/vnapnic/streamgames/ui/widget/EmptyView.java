package com.vnapnic.streamgames.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.vnapnic.streamgames.R;

public class EmptyView extends FrameLayout {

    private ProgressBar progress;
    private TextView text;
    private ImageView image;

    public EmptyView(Context context) {
        super(context);
        init(context);
    }

    public EmptyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public EmptyView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.list_empty, this);
        progress = (ProgressBar) findViewById(R.id.progress);
        text = (TextView) findViewById(R.id.text);
        image = (ImageView) findViewById(R.id.image);
    }

    public void showText() {
        progress.setVisibility(GONE);
        text.setVisibility(VISIBLE);
        image.setVisibility(GONE);
    }

    public void showProgress() {
        progress.setVisibility(VISIBLE);
        text.setVisibility(GONE);
        image.setVisibility(GONE);
    }

    public void showImage() {
        progress.setVisibility(GONE);
        text.setVisibility(GONE);
        image.setVisibility(VISIBLE);
    }

    public void setText(String text) {
        this.text.setText(text);
        showText();
    }

    public void setText(int textRes) {
        text.setText(textRes);
        showText();
    }

    public void setImage(int imageRes) {
        image.setBackgroundResource(imageRes);
        showImage();
    }
}
