package com.labralab.zmsportclient.views;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.Toast;

import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.labralab.zmsportclient.R;
import com.labralab.zmsportclient.models.Tournament;
import com.labralab.zmsportclient.views.fragments.PlayoffFragment;
import com.labralab.zmsportclient.views.fragments.TeamTabFragment;


import java.util.ArrayList;


public class TeamActivity extends AppCompatActivity {

    private TeamTabFragment teamTabFragment;
    private PlayoffFragment playoffFragment;
    private Tournament tournament;
    private String tournTitle;
    private Toolbar toolbar;
    private SegmentTabLayout segmentTabLayout;
    public Display display;
    public int displayHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);

        //Getting tournament title from intent
        Intent intent = getIntent();
        tournTitle = intent.getStringExtra("title");

        //Connecting to toolbar
        toolbar = (Toolbar) findViewById(R.id.teamToolBar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        //Setting tittle
        toolbar.setTitle(tournTitle);

        //Creating instance of Tournament
        tournament = Tournament.getInstance(tournTitle, this);
        showContent();


    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        //Killing Tournament instance
        tournament.onDestroy();
    }

    //For getting playoffFragment
    public PlayoffFragment getPlayoffFragment() {
        return playoffFragment;
    }

    //For getting segmentTabLayout
    public SegmentTabLayout getSegmentTabLayout() {
        return segmentTabLayout;
    }

    public TeamTabFragment getTeamTabFragment() {
        return teamTabFragment;
    }

    public String getTournTitle(){
        return tournTitle;
    }

    public Tournament getTournament(){
        return tournament;
    }

    public void showContent() {

        //List of Fragments for Segment Tab Layout
        ArrayList<Fragment> mFragments = new ArrayList<>();

        //Main fragment. Contains TeamList Tab Item and GameList Tab Item
        teamTabFragment = new TeamTabFragment();
        //Playoff fragment
        playoffFragment = new PlayoffFragment();
        mFragments.add(teamTabFragment);
        mFragments.add(playoffFragment);

        //Segment Tabs titles
        String[] mTitles = {getString(R.string.tournament), getString(R.string.playoff)};

        //Getting SegmentTab Layout and get him parameters
        segmentTabLayout = (SegmentTabLayout) findViewById(R.id.SlTL);
        segmentTabLayout.setTabData(mTitles, this, R.id.team_container, mFragments);

        display = getWindowManager().getDefaultDisplay();
        displayHeight = display.getHeight();
        int marginBottom = displayHeight / 14;


        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) segmentTabLayout.getLayoutParams();
        params.bottomMargin = marginBottom;
        segmentTabLayout.setLayoutParams(params);

    }
}

