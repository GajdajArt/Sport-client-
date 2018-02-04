package com.labralab.zmsportclient.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.labralab.zmsportclient.R;
import com.labralab.zmsportclient.models.Game;

import java.util.ArrayList;
import java.util.List;

public class GameAdapter extends RecyclerView.Adapter<GameViewHolder> {

    List<Game> items = new ArrayList<>();

    public GameAdapter(List<Game> items) {
        this.items = items;
    }

    @Override
    public GameViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.game_maket, parent, false);
        return new GameViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GameViewHolder holder, int position) {
        Game item = items.get(position);
        holder.teamOneTV.setText(item.getTeam_1().getTitle());
        holder.teamTwoTV.setText(item.getTeam_2().getTitle());
        holder.scoreOneTV.setText(Integer.toString(item.getScore_1()));
        holder.scoreTwoTV.setText(Integer.toString(item.getScore_2()));
        String myDay = String.format("%02d", item.getDay());
        String myMonth = String.format("%02d", item.getMonth());
        holder.dateTV.setText(myDay + "." +
                myMonth + "." +
                item.getYear());


    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}

class GameViewHolder extends RecyclerView.ViewHolder  {

    TextView teamOneTV;
    TextView teamTwoTV;
    TextView scoreTwoTV;
    TextView scoreOneTV;
    TextView dateTV;

    View itemView;


    public GameViewHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;


        teamOneTV = (TextView) itemView.findViewById(R.id.show_team_one);
        teamTwoTV = (TextView) itemView.findViewById(R.id.show_team_two);
        scoreOneTV = (TextView) itemView.findViewById(R.id.score1);
        scoreTwoTV = (TextView) itemView.findViewById(R.id.score2);
        dateTV = (TextView) itemView.findViewById(R.id.t_v_date);

    }
}