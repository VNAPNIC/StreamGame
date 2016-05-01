// Generated code from Butter Knife. Do not modify!
package com.vnapnic.streamgames.ui.fragments;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class ChannelFragment$$ViewInjector {
  public static void inject(Finder finder, final com.vnapnic.streamgames.ui.fragments.ChannelFragment target, Object source) {
    com.vnapnic.streamgames.ui.fragments.TDFragment$$ViewInjector.inject(finder, target, source);

    View view;
    view = finder.findRequiredView(source, 2131689579, "field 'content'");
    target.content = (android.view.ViewGroup) view;
    view = finder.findRequiredView(source, 2131689580, "field 'imgLogo'");
    target.imgLogo = (android.widget.ImageView) view;
    view = finder.findRequiredView(source, 2131689581, "field 'lblTitle'");
    target.lblTitle = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131689583, "field 'lblStatus'");
    target.lblStatus = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131689584, "field 'btnStream'");
    target.btnStream = (android.widget.Button) view;
  }

  public static void reset(com.vnapnic.streamgames.ui.fragments.ChannelFragment target) {
    com.vnapnic.streamgames.ui.fragments.TDFragment$$ViewInjector.reset(target);

    target.content = null;
    target.imgLogo = null;
    target.lblTitle = null;
    target.lblStatus = null;
    target.btnStream = null;
  }
}
