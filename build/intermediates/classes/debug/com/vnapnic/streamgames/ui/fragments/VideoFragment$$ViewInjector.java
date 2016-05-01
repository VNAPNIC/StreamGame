// Generated code from Butter Knife. Do not modify!
package com.vnapnic.streamgames.ui.fragments;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class VideoFragment$$ViewInjector {
  public static void inject(Finder finder, final com.vnapnic.streamgames.ui.fragments.VideoFragment target, Object source) {
    com.vnapnic.streamgames.ui.fragments.TDFragment$$ViewInjector.inject(finder, target, source);

    View view;
    view = finder.findRequiredView(source, 16908292, "field 'emptyView'");
    target.emptyView = (com.vnapnic.streamgames.ui.widget.EmptyView) view;
  }

  public static void reset(com.vnapnic.streamgames.ui.fragments.VideoFragment target) {
    com.vnapnic.streamgames.ui.fragments.TDFragment$$ViewInjector.reset(target);

    target.emptyView = null;
  }
}
