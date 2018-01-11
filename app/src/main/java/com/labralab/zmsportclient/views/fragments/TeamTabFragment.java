package com.labralab.zmsportclient.views.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.OvershootInterpolator;

import com.flyco.tablayout.SlidingTabLayout;
import com.labralab.zmsportclient.R;

import java.util.ArrayList;


public class TeamTabFragment extends Fragment {

    public static TeamListFragment teamListFragment;
    public static GameListFragment gameListFragment;
    private SlidingTabLayout mTabLayout;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private String[] mTitles = {"КОМАНДЫ", "ИГРЫ"};
    public static Context context;

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_team_tab, container, false);
        context = view.getContext();
        return view;

    }

    @Override
    public void onStart() {
                super.onStart();

        //Getting UI elements
        mTabLayout = (SlidingTabLayout) view.findViewById(R.id.STL);
        teamListFragment = new TeamListFragment();
        gameListFragment = new GameListFragment();
        mFragments.add(teamListFragment);
        mFragments.add(gameListFragment);

        ViewPager pager = (ViewPager) view.findViewById(R.id.viewPager);
        mTabLayout.setViewPager(pager, mTitles, getActivity(), mFragments);

    }
}