// Generated code from Butter Knife. Do not modify!
package com.vnapnic.streamgames.ui.adapter;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class GameOverviewAdapter$ViewHolder$$ViewInjector {
  public static void inject(Finder finder, final com.vnapnic.streamgames.ui.adapter.GameOverviewAdapter.ViewHolder target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131689601, "field 'imgBox'");
    target.imgBox = (android.widget.ImageView) view;
    view = finder.findRequiredView(source, 2131689602, "field 'lblName'");
    target.lblName = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131689604, "field 'lblViewers'");
    target.lblViewers = (android.widget.TextView) view;
  }

  public static void reset(com.vnapnic.streamgames.ui.adapter.GameOverviewAdapter.ViewHolder target) {
    target.imgBox = null;
    target.lblName = null;
    target.lblViewers = null;
  }
}
