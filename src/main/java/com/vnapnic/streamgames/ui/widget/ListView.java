package com.vnapnic.streamgames.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.AbsListView;

public class ListView extends android.widget.ListView implements AbsListView.OnScrollListener {

    private OnLastItemVisibleListener onLastItemVisibleListener;
    private int itemCount;
    private boolean notified;

    public ListView(Context context) {
        super(context);
    }

    public ListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setOnLastItemVisibleListener(OnLastItemVisibleListener onLastItemVisibleListener) {
        this.onLastItemVisibleListener = onLastItemVisibleListener;
        setOnScrollListener(this);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (totalItemCount > 0 && totalItemCount == firstVisibleItem + visibleItemCount && onLastItemVisibleListener != null) {
            if (!notified || itemCount != totalItemCount) {
                itemCount = totalItemCount;
                onLastItemVisibleListener.onLastItemVisible();
                notified = true;
            }
        }
    }

    public interface OnLastItemVisibleListener {
        public void onLastItemVisible();
    }
}
