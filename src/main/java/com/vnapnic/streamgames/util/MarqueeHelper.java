package com.vnapnic.streamgames.util;

import android.text.TextUtils;
import android.widget.TextView;

public class MarqueeHelper {

    public static void setupMarquee(TextView textView, String text) {
        textView.setText(text);
        textView.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        textView.setSingleLine();
        textView.setSelected(true);
    }
}
