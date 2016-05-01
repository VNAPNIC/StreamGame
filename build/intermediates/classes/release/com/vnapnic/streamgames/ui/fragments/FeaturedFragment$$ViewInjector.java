// Generated code from Butter Knife. Do not modify!
package com.vnapnic.streamgames.ui.fragments;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class FeaturedFragment$$ViewInjector {
  public static void inject(Finder finder, final com.vnapnic.streamgames.ui.fragments.FeaturedFragment target, Object source) {
    com.vnapnic.streamgames.ui.fragments.TDFragment$$ViewInjector.inject(finder, target, source);

    View view;
    view = finder.findRequiredView(source, 2131689598, "field 'emptyView'");
    target.emptyView = (com.vnapnic.streamgames.ui.widget.EmptyView) view;
    view = finder.findRequiredView(source, 2131689597, "field 'listView'");
    target.listView = (com.vnapnic.streamgames.ui.widget.ListView) view;
  }

  public static void reset(com.vnapnic.streamgames.ui.fragments.FeaturedFragment target) {
    com.vnapnic.streamgames.ui.fragments.TDFragment$$ViewInjector.reset(target);

    target.emptyView = null;
    target.listView = null;
  }
}
