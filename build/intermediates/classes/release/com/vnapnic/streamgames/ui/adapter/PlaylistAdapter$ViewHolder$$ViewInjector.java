// Generated code from Butter Knife. Do not modify!
package com.vnapnic.streamgames.ui.adapter;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class PlaylistAdapter$ViewHolder$$ViewInjector {
  public static void inject(Finder finder, final com.vnapnic.streamgames.ui.adapter.PlaylistAdapter.ViewHolder target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131689620, "field 'lblPartIndex'");
    target.lblPartIndex = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131689622, "field 'lblPartCount'");
    target.lblPartCount = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131689581, "field 'lblTitle'");
    target.lblTitle = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131689623, "field 'chkPlayed'");
    target.chkPlayed = (android.widget.CheckBox) view;
  }

  public static void reset(com.vnapnic.streamgames.ui.adapter.PlaylistAdapter.ViewHolder target) {
    target.lblPartIndex = null;
    target.lblPartCount = null;
    target.lblTitle = null;
    target.chkPlayed = null;
  }
}
