package com.vnapnic.streamgames.ui;

import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.vnapnic.streamgames.R;
import com.vnapnic.streamgames.data.model.TwitchBroadcast;
import com.vnapnic.streamgames.data.model.TwitchChannel;
import com.vnapnic.streamgames.data.service.TDServiceImpl;
import com.vnapnic.streamgames.data.worker.TDCallback;
import com.vnapnic.streamgames.data.worker.TDTaskManager;
import com.vnapnic.streamgames.ui.fragments.GameStreamsFragment;
import com.vnapnic.streamgames.ui.fragments.HomeFragment;
import com.vnapnic.streamgames.ui.fragments.SearchFragment;
import com.vnapnic.streamgames.ui.fragments.VideoFragment;
import com.vnapnic.streamgames.util.CheckNetwok;
import com.vnapnic.streamgames.util.Log;

public class TDActivity extends ActionBarActivity implements
        TDCallback<TwitchChannel>,
        View.OnFocusChangeListener, HomeFragment.OnTabAction {

    public static final String QUALITY = "stream_quality";
    public static final String QUALITY_VALUE = "stream_quality_value";
    public static final String QUALITY_FISH_LOAD = "stream_quality_fish_load";
    public static final String QUALITY_POSITION = "stream_quality_position";

    private HomeFragment homeFragment;
    private GameStreamsFragment gameStreamsFragment;
    private SearchFragment searchFragment;
    private VideoFragment videoFragment;
    private MenuItem refreshItem;
    private MenuItem searchItem;
    SearchView searchView;
    private View refreshView;
    private Toast toast;
    private boolean isLoading;
    private View root;

    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.title)
    TextView title;
    @InjectView(R.id.contentLayout)
    LinearLayout contentLayout;
    @InjectView(R.id.splash)
    RelativeLayout splash;
    @InjectView(R.id.logo)
    ImageView logo;
    @InjectView(R.id.adView)
    AdView mAdView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        try {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            super.onCreate(savedInstanceState);
            root = LayoutInflater.from(this).inflate(R.layout.main, null, false);
            setContentView(root);
            ButterKnife.inject(this);
            initNavigation();
            getSupportActionBar().hide();
            Context contextThemeWrapper = new ContextThemeWrapper(this, R.style.Theme_TD_Light);
            LayoutInflater inflater = LayoutInflater.from(this).cloneInContext(contextThemeWrapper);
            refreshView = inflater.inflate(R.layout.action_refresh, null);
            Animation animationIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha_in);
            logo.startAnimation(animationIn);
            showHome();
        } catch (Exception e) {
            root = new View(this);
            setContentView(root);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (CheckNetwok.isNetworkConnected(getApplicationContext())) {
            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);
        } else {
            mAdView.setVisibility(View.GONE);
        }
    }

    private void initNavigation() {
        setSupportActionBar(toolbar);
        this.getSupportActionBar().setTitle(null);
        this.getSupportActionBar().setHomeButtonEnabled(true);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_logo);
        toolbar.setTitle(R.string.stream_title);
    }


    public void showHome() {
        if (homeFragment == null) {
            homeFragment = HomeFragment.newInstance(this);
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.content, homeFragment)
                    .commit();
        } else {
            replaceFragment(homeFragment);
        }
        setGoneAds();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshData();
    }

    public void setGoneAds() {
        mAdView.setVisibility(View.GONE);
    }

    public void setShowAds() {
        mAdView.setVisibility(View.VISIBLE);
    }

    public Fragment getFragment() {
        return getSupportFragmentManager().findFragmentById(R.id.content);
    }

    private void replaceFragment(Fragment fragment) {
        replaceFragment(fragment, true);
    }

    private void replaceFragment(Fragment fragment, boolean backstack) {
        if (fragment != null && !fragment.isAdded()) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.content, fragment);
            if (backstack) {
                transaction.addToBackStack(null);
            }
            transaction.commitAllowingStateLoss();
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            setTitle(R.string.search_title);
            String query = intent.getStringExtra(SearchManager.QUERY);

            if (searchFragment == null) {
                searchFragment = new SearchFragment();
            }

            searchFragment.setQuery(query);
            searchFragment.loadData();

            replaceFragment(searchFragment);
        }
    }


    @Override
    public void onPause() {
        TDTaskManager.cancelAllTasks();
        super.onPause();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        refreshItem = menu.findItem(R.id.menu_refresh);
        searchItem = menu.findItem(R.id.menu_search);
        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextFocusChangeListener(this);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_refresh:
                if (!isLoading) {
                    refreshData();
                }
                return true;
            case android.R.id.home:
                if (getSupportFragmentManager().findFragmentById(R.id.content) instanceof VideoFragment) {
                    onBackPressed();
                } else {
                    setTitle(R.string.stream_title);
                    getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_logo);
                    if (getSupportFragmentManager().findFragmentById(R.id.content) instanceof HomeFragment) {
                        homeFragment.setGoStream();
                    } else {
                        showHome();
                    }
                }

                return true;
            case R.id.setting:
                showDialogQuality();
                return true;
            case R.id.about:
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        new AlertDialog.Builder(TDActivity.this)
                                .setTitle(R.string.licenses)
                                .setMessage(Html.fromHtml("VN APNIC\nStream games"))
                                .setCancelable(false).setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).create().show();
                    }
                });
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void showVideo(Bundle args) {
        if (videoFragment == null) {
            videoFragment = new VideoFragment();
        }
        videoFragment.setArgs(args);

        if (videoFragment.isAdded()) {

        } else {
            replaceFragment(videoFragment);
        }
        MenuItemCompat.collapseActionView(searchItem);
    }

    public void showStreams(Bundle args) {
        if (gameStreamsFragment == null) {
            gameStreamsFragment = new GameStreamsFragment();
        }
        gameStreamsFragment.setArgs(args);
        replaceFragment(gameStreamsFragment);
    }

    public void startLoading() {
        isLoading = true;
        if (refreshItem != null) {
            MenuItemCompat.setActionView(refreshItem, refreshView);
        }
    }

    public void stopLoading() {
        isLoading = false;
        if (refreshItem != null) {
            MenuItemCompat.setActionView(refreshItem, null);
        }
    }

    @Override
    public TwitchChannel startRequest() {
        return TDServiceImpl.getInstance().getChannel("");
    }

    @Override
    public void onResponse(TwitchChannel response) {
    }

    @Override
    public void onError(String title, String message) {
        if (!CheckNetwok.isNetworkConnected(getApplicationContext())) {
            final AlertDialog.Builder adb = new AlertDialog.Builder(this);
            adb.setMessage(message);
            adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            adb.show();
        } else {
            toast = Toast.makeText(this, title + ": " + message, Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    @Override
    public boolean isAdded() {
        return true;
    }

    @Override
    public void setTitle(int titleId) {
        this.title.setText(titleId);
    }

    @Override
    public void setTitle(CharSequence title) {
        this.title.setText(title);
    }

    protected void refreshData() {
        if (searchFragment != null && searchFragment.isAdded()) {
            searchFragment.refreshData();
        }

        if (gameStreamsFragment != null && gameStreamsFragment.isAdded()) {
            gameStreamsFragment.refreshData();
        }
        if (homeFragment != null && homeFragment.isAdded()) {
            homeFragment.refreshData();
        }
    }

    @Override
    public void onFocusChange(View view, boolean queryTextFocused) {
        if (!queryTextFocused) {
            MenuItemCompat.collapseActionView(searchItem);
            showHome();
        }
    }

    @Override
    public void onBackPressed() {

        if (getSupportFragmentManager().findFragmentById(R.id.content) instanceof VideoFragment) {
            super.onBackPressed();
        } else if (getSupportFragmentManager().findFragmentById(R.id.content) instanceof HomeFragment) {
            final AlertDialog.Builder adb = new AlertDialog.Builder(this);
            adb.setMessage(getResources().getString(R.string.dialog_messenger));
            adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    finish();
                }
            });
            adb.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            adb.show();
        } else {
            setTitle(R.string.stream_title);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_logo);
            super.onBackPressed();
        }
    }

    private boolean fishLoad = true;

    public boolean isSplash() {
        Log.d("namit", "isSplash");
        return fishLoad;
    }

    public void unSplash() {
        Log.d("namit", "unSplash");
        Animation animationLeftOut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha_out);
        splash.startAnimation(animationLeftOut);
        animationLeftOut.setAnimationListener(new Animation.AnimationListener() {
                                                  @Override
                                                  public void onAnimationStart(Animation animation) {
                                                      runOnUiThread(new Runnable() {
                                                          @Override
                                                          public void run() {
                                                              contentLayout.setVisibility(View.VISIBLE);
                                                              getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                                                              getSupportActionBar().show();
                                                          }

                                                      });
                                                  }

                                                  @Override
                                                  public void onAnimationEnd(Animation animation) {
                                                      runOnUiThread(new Runnable() {
                                                          @Override
                                                          public void run() {
                                                              try {
                                                                  Thread.sleep(200);
                                                              } catch (InterruptedException e) {
                                                                  e.printStackTrace();
                                                              }
                                                              splash.setVisibility(View.GONE);
                                                              fishLoad = false;
                                                          }
                                                      });
                                                      final SharedPreferences sharedpreferences = getSharedPreferences(QUALITY, Context.MODE_PRIVATE);
                                                      Log.d("namit", sharedpreferences.getBoolean(QUALITY_FISH_LOAD, false) + " -------------");
                                                      boolean isFload = sharedpreferences.getBoolean(QUALITY_FISH_LOAD, false);

                                                      if (!isFload) {
                                                          showDialogQuality();
                                                      }
                                                  }

                                                  @Override
                                                  public void onAnimationRepeat(Animation animation) {

                                                  }
                                              }
        );
    }

    public void showDialogQuality() {
        final CharSequence[] quality = getResources().getStringArray(R.array.dialog_stream_quality_entries_values);
        final SharedPreferences sharedpreferences = getSharedPreferences(QUALITY, Context.MODE_PRIVATE);
        String defaultValue = sharedpreferences.getString(QUALITY_VALUE, quality[2].toString());
        int defaultWhich = 0;
        for (int i = 0; i < quality.length; i++) {
            if (quality[i].equals(defaultValue)) {
                defaultWhich = i;
            }
        }
        android.app.AlertDialog.Builder alert = new android.app.AlertDialog.Builder(TDActivity.this);
        alert.setTitle(R.string.dialog_stream_quality_title);
        alert.setSingleChoiceItems(quality, defaultWhich, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString(QUALITY_VALUE, quality[which].toString());
                editor.putInt(QUALITY_POSITION, which);
                editor.putBoolean(QUALITY_FISH_LOAD, true);
                editor.commit();
            }
        });

        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alert.show();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (fishLoad) {
            fishLoad = false;
        } else {
            onOrientationChange(newConfig.orientation);
        }
    }

    private void onOrientationChange(int orientation) {
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getSupportActionBar().hide();

        } else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getSupportActionBar().show();
        }
    }
}
