package com.labralab.zmsportclient.adapters;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.labralab.zmsportclient.R;
import com.labralab.zmsportclient.models.Game;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc on 14.10.2017.
 */

public class GameInPlayoffAdapter extends RecyclerView.Adapter<GameInPlayoffViewHolder> {

    List<Game> items = new ArrayList<>();
    int tur = 0;
    int teamInPlayoff;
    Display display;

    public GameInPlayoffAdapter(List<Game> items, int tur, int teamInPlayoff, Display display) {

        this.items = items;
        this.tur = tur;
        this.teamInPlayoff = teamInPlayoff;
        this.display = display;
    }

    @Override
    public GameInPlayoffViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        switch (teamInPlayoff) {
            case 2:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.game_maket, parent, false);
                break;
            case 4:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.game_in_playoff, parent, false);
                break;
            case 8:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.game_in_playoff, parent, false);
                break;
        }
        return new GameInPlayoffViewHolder(view, teamInPlayoff);
    }

    @Override
    public void onBindViewHolder(GameInPlayoffViewHolder holder, int position) {

        Game item = items.get(position);
        holder.teamOneTV.setText(item.getTeam_1().getTitle());
        holder.teamTwoTV.setText(item.getTeam_2().getTitle());
        holder.scoreOneTV.setText(Integer.toString(item.getScore_1()));
        holder.scoreTwoTV.setText(Integer.toString(item.getScore_2()));

        int width = display.getWidth();

        if (teamInPlayoff == 2) {

            String myDay = String.format("%02d", item.getDay());
            String myMonth = String.format("%02d", item.getMonth());
            holder.dateTV.setText(myDay + "." +
                    myMonth + "." +
                    item.getYear());

            holder.linearLayout.setLayoutParams(new LinearLayout.LayoutParams(width, 200));
        }

        if (teamInPlayoff == 4) {

            int cardHeight = (int) holder.itemView.getContext().getResources().getDimensionPixelSize(R.dimen.card_hides);
            int cardMarginTop = (int) holder.itemView.getContext().getResources().getDimensionPixelSize(R.dimen.margin_top_in_playoff);
            int firstTurHeight = (cardHeight + cardMarginTop) * 2;
            int secondTurPadding = ((firstTurHeight + cardMarginTop) - cardHeight) / 2;


            holder.linearLayout.setLayoutParams(new LinearLayout.LayoutParams((width / 2) - 10, ViewGroup.LayoutParams.WRAP_CONTENT));

            switch (tur) {
                case 1:
                    holder.linearLayout.setPadding(5, cardMarginTop, 5, 0);
                    break;
                case 3:
                    holder.linearLayout.setPadding(5, secondTurPadding, 5, secondTurPadding);
                    break;
            }
        }

        if (teamInPlayoff == 8) {

            int cardHeight = (int) holder.itemView.getContext().getResources().getDimensionPixelSize(R.dimen.card_hides);
            int cardMarginTop = (int) holder.itemView.getContext().getResources().getDimensionPixelSize(R.dimen.margin_top_in_playoff);
            int firstTurHeight = (cardHeight + cardMarginTop) * 4;
            int secondTurPadding = (firstTurHeight - (cardHeight + cardMarginTop) * 2) / 4;
            int lastTutPadding = secondTurPadding * 3;

            switch (tur) {
                case 1:
                    break;
                case 2:
                    holder.linearLayout.setPadding(5, secondTurPadding, 5, secondTurPadding);
                    break;
                case 3:
                    holder.linearLayout.setPadding(5, lastTutPadding, 5, lastTutPadding);
                    break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}

class GameInPlayoffViewHolder extends RecyclerView.ViewHolder {

    TextView teamOneTV;
    TextView teamTwoTV;
    TextView scoreTwoTV;
    TextView scoreOneTV;
    TextView twoP1;
    TextView twoP2;
    LinearLayout linearLayout;
    GridLayout cardView;

    TextView dateTV;

    View itemView;

    public GameInPlayoffViewHolder(View itemView, int teamInPlayoff) {
        super(itemView);
        this.itemView = itemView;


        if (teamInPlayoff == 8 || teamInPlayoff == 4) {

            cardView = (GridLayout) itemView.findViewById(R.id.playoff_card);
            teamOneTV = (TextView) itemView.findViewById(R.id.team_one_in_playoff);
            teamTwoTV = (TextView) itemView.findViewById(R.id.team_two_in_playoff);
            scoreOneTV = (TextView) itemView.findViewById(R.id.score_one_in_playoff);
            scoreTwoTV = (TextView) itemView.findViewById(R.id.score_two_in_playoff);
            twoP1 = (TextView) itemView.findViewById(R.id.twoP1);
            twoP2 = (TextView) itemView.findViewById(R.id.twoP2);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.playoff_container);

        }
        if (teamInPlayoff == 2) {

            teamOneTV = (TextView) itemView.findViewById(R.id.show_team_one);
            teamTwoTV = (TextView) itemView.findViewById(R.id.show_team_two);
            scoreOneTV = (TextView) itemView.findViewById(R.id.score1);
            scoreTwoTV = (TextView) itemView.findViewById(R.id.score2);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.game_linear_layout);
            dateTV = (TextView) itemView.findViewById(R.id.t_v_date);
        }
    }
}