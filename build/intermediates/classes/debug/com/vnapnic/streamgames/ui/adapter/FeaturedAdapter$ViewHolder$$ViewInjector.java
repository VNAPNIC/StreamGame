// Generated code from Butter Knife. Do not modify!
package com.vnapnic.streamgames.ui.adapter;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class FeaturedAdapter$ViewHolder$$ViewInjector {
  public static void inject(Finder finder, final com.vnapnic.streamgames.ui.adapter.FeaturedAdapter.ViewHolder target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131689611, "field 'screenVideo'");
    target.screenVideo = (android.widget.RelativeLayout) view;
    view = finder.findRequiredView(source, 2131689612, "field 'banner'");
    target.banner = (android.widget.ImageView) view;
    view = finder.findRequiredView(source, 2131689613, "field 'logo'");
    target.logo = (de.hdodenhof.circleimageview.CircleImageView) view;
    view = finder.findRequiredView(source, 2131689614, "field 'lblAuthor'");
    target.lblAuthor = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131689583, "field 'lblStatus'");
    target.lblStatus = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131689615, "field 'lblText'");
    target.lblText = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131689604, "field 'lblViewers'");
    target.lblViewers = (android.widget.TextView) view;
  }

  public static void reset(com.vnapnic.streamgames.ui.adapter.FeaturedAdapter.ViewHolder target) {
    target.screenVideo = null;
    target.banner = null;
    target.logo = null;
    target.lblAuthor = null;
    target.lblStatus = null;
    target.lblText = null;
    target.lblViewers = null;
  }
}
