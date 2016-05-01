// Generated code from Butter Knife. Do not modify!
package com.vnapnic.streamgames.ui.adapter;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class SearchAdapter$ViewHolder$$ViewInjector {
  public static void inject(Finder finder, final com.vnapnic.streamgames.ui.adapter.SearchAdapter.ViewHolder target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131689616, "field 'imgThumbnail'");
    target.imgThumbnail = (android.widget.ImageView) view;
    view = finder.findRequiredView(source, 2131689581, "field 'lblTitle'");
    target.lblTitle = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131689618, "field 'lblChannel'");
    target.lblChannel = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131689625, "field 'lblGame'");
    target.lblGame = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131689604, "field 'lblViewers'");
    target.lblViewers = (android.widget.TextView) view;
  }

  public static void reset(com.vnapnic.streamgames.ui.adapter.SearchAdapter.ViewHolder target) {
    target.imgThumbnail = null;
    target.lblTitle = null;
    target.lblChannel = null;
    target.lblGame = null;
    target.lblViewers = null;
  }
}
