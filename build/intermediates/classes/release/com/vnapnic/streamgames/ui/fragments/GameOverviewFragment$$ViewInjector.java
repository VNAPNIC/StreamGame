// Generated code from Butter Knife. Do not modify!
package com.vnapnic.streamgames.ui.fragments;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class GameOverviewFragment$$ViewInjector {
  public static void inject(Finder finder, final com.vnapnic.streamgames.ui.fragments.GameOverviewFragment target, Object source) {
    com.vnapnic.streamgames.ui.fragments.TDFragment$$ViewInjector.inject(finder, target, source);

    View view;
    view = finder.findRequiredView(source, 2131689598, "field 'emptyView'");
    target.emptyView = (com.vnapnic.streamgames.ui.widget.EmptyView) view;
    view = finder.findRequiredView(source, 2131689605, "field 'gridView'");
    target.gridView = (com.vnapnic.streamgames.ui.widget.GridView) view;
  }

  public static void reset(com.vnapnic.streamgames.ui.fragments.GameOverviewFragment target) {
    com.vnapnic.streamgames.ui.fragments.TDFragment$$ViewInjector.reset(target);

    target.emptyView = null;
    target.gridView = null;
  }
}
