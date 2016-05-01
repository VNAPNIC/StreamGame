package com.vnapnic.streamgames.ui.fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vnapnic.streamgames.R;
import com.vnapnic.streamgames.ui.TDActivity;


@SuppressLint("ValidFragment")
public class HomeFragment extends Fragment implements FishLoad {

    private TabLayout tabLayout;
    private OnTabAction onTabAction;
    private ViewPager viewPager;
    private TDActivity activity;
    private TabAdapter tabAdapter;

    public HomeFragment(OnTabAction onTabAction) {
        // Required empty public constructor
        this.onTabAction = onTabAction;
    }

    public static HomeFragment newInstance(OnTabAction onTabAction) {
        HomeFragment fragment = new HomeFragment(onTabAction);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewPager = (ViewPager) view.findViewById(R.id.viewPage);
        tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        setUpTabLayout();
    }

    private void setUpTabLayout() {
        tabAdapter = new TabAdapter(getChildFragmentManager());
        viewPager.setAdapter(tabAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setText(R.string.stream_title);
        tabLayout.getTabAt(1).setText(R.string.fatureds_title);
        tabLayout.setOnTabSelectedListener(onTabSelectedListener);
        viewPager.setOffscreenPageLimit(2);
    }

    public void refreshData() {
        Log.d("namit", "Home refreshData");
        ((GameOverviewFragment) tabAdapter.getItem(0)).refreshData();
        ((FeaturedFragment) tabAdapter.getItem(1)).refreshData();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void setGoStream() {
        viewPager.setCurrentItem(0);
    }

    TabLayout.OnTabSelectedListener onTabSelectedListener = new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            viewPager.setCurrentItem(tab.getPosition());
            switch (tab.getPosition()) {
                case 0:
                    onTabAction.setTitle(R.string.stream_title);
                    break;
                case 1:
                    onTabAction.setTitle(R.string.fatureds_title);
                    break;
            }
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    };

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = (TDActivity) activity;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void loadSuccess() throws Exception {
        Log.d("namit", "success.!");
        if (this.activity.isSplash()) {
            this.activity.unSplash();
        }
    }

    public interface OnTabAction {
        void setTitle(int title);
    }

    private class TabAdapter extends FragmentPagerAdapter {

        public TabAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment;
            switch (position) {
                case 0:
                    fragment = new GameOverviewFragment();
                    onTabAction.setTitle(R.string.stream_title);
                    break;
                case 1:
                    fragment = new FeaturedFragment();
                    onTabAction.setTitle(R.string.fatureds_title);
                    break;
                default:
                    fragment = new Fragment();
                    break;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}
