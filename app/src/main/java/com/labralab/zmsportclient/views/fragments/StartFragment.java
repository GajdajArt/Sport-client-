package com.labralab.zmsportclient.views.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.labralab.zmsportclient.R;
import com.labralab.zmsportclient.adapters.TournAdapter;
import com.labralab.zmsportclient.models.Tournament;
import com.labralab.zmsportclient.views.MainActivity;

import java.util.ArrayList;
import java.util.List;


public class StartFragment extends Fragment {

    private List<Tournament> items = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private static TournAdapter adapter;
    private Tournament tournament;
    private ProgressBar progressBar;
    private TextView hint;

    Handler handler;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_start, container, false);

        //Connection the RecyclerView, layoutManager
        recyclerView = (RecyclerView) rootView.findViewById(R.id.tournList);
        progressBar = (ProgressBar) rootView.findViewById(R.id.progressBar);
        hint = (TextView) rootView.findViewById(R.id.first_hint);
        layoutManager = new LinearLayoutManager(getContext());

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

        AppCompatActivity mainActivity = (AppCompatActivity) getActivity();
        mainActivity.getSupportActionBar().show();


        adapter = new TournAdapter(items);
        //Creating new tournament to get a list items(Tournaments list)
        tournament = new Tournament();
        items = tournament.getTournList(this);

        showProgressBar();

        //Starting first hint
        firstHint();

        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                hideProgressBar();
                }
        };


        //Connection adapter
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));

        recyclerView.setAdapter(adapter);

        handler.sendEmptyMessageDelayed(0, 2000);
    }

    @Override
    public void onResume() {
        MainActivity.getToolbar().setTitle(R.string.app_name);
        super.onResume();
    }

    public void firstHint() {
        //Check if there are rows in the table


        if (items.size() < 1) {

            //Creating first hint
            hint.setText(R.string.First_hint);

        } else {
            hint.setText(null);
            hint.setBackgroundColor(getResources().getColor(R.color.backgroundTransparent));
        }
    }

    public void newTournFragmentStart() {

//        //Starting newTournFragment
//        getActivity().getSupportFragmentManager().beginTransaction()
//                .replace(R.id.container, MainActivity.getNewTournFragment())
//                .addToBackStack(null)
//                .commit();
    }

    //For getting adapter
    public static TournAdapter getAdapter() {
        return adapter;
    }

    public void setData(List<Tournament> items) {
        this.items = items;
        adapter.setData(items);
        hideProgressBar();
        firstHint();
    }

    public void showProgressBar() {
        hint.setText(null);
        hint.setBackgroundColor(getResources().getColor(R.color.backgroundTransparent));
        progressBar.setVisibility(View.VISIBLE);

    }

    public void hideProgressBar() {
        progressBar.setVisibility(View.INVISIBLE);
        firstHint();
    }
}


