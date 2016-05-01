// Generated code from Butter Knife. Do not modify!
package com.vnapnic.streamgames.ui;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class TDActivity$$ViewInjector {
  public static void inject(Finder finder, final com.vnapnic.streamgames.ui.TDActivity target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131689629, "field 'toolbar'");
    target.toolbar = (android.support.v7.widget.Toolbar) view;
    view = finder.findRequiredView(source, 2131689541, "field 'title'");
    target.title = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131689628, "field 'contentLayout'");
    target.contentLayout = (android.widget.LinearLayout) view;
    view = finder.findRequiredView(source, 2131689631, "field 'splash'");
    target.splash = (android.widget.RelativeLayout) view;
    view = finder.findRequiredView(source, 2131689632, "field 'logo'");
    target.logo = (android.widget.ImageView) view;
    view = finder.findRequiredView(source, 2131689630, "field 'mAdView'");
    target.mAdView = (com.google.android.gms.ads.AdView) view;
  }

  public static void reset(com.vnapnic.streamgames.ui.TDActivity target) {
    target.toolbar = null;
    target.title = null;
    target.contentLayout = null;
    target.splash = null;
    target.logo = null;
    target.mAdView = null;
  }
}
