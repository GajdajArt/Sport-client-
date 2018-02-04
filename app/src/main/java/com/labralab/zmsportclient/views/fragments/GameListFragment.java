package com.labralab.zmsportclient.views.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;

import com.labralab.zmsportclient.R;
import com.labralab.zmsportclient.adapters.GameAdapter;
import com.labralab.zmsportclient.models.Tournament;
import com.labralab.zmsportclient.views.TeamActivity;


public class GameListFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    public static GameAdapter gameAdapter;
    boolean rvTouch;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_game_list, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.gameList);
        layoutManager = new LinearLayoutManager(getContext());
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

        recyclerView.setLayoutManager(layoutManager);
        gameAdapter = new GameAdapter(Tournament.getInstance().getGameList());
        recyclerView.setAdapter(gameAdapter);


        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    rvTouch = true;
                }
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    rvTouch = false;
                }
                return false;
            }

        });

    }
}

