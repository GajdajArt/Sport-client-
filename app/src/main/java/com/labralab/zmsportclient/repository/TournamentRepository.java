package com.labralab.zmsportclient.repository;

import android.database.sqlite.SQLiteDatabase;


import com.labralab.zmsportclient.models.Game;
import com.labralab.zmsportclient.models.Playoff;
import com.labralab.zmsportclient.models.Team;
import com.labralab.zmsportclient.models.Tournament;
import com.labralab.zmsportclient.views.fragments.StartFragment;

import java.util.List;

/**
 * Created by pc on 24.09.2017.
 */

public interface TournamentRepository {

    void createTournament(String tournTitle, String year, String type, List<Team> teamItems,
                          List<Game> gameList, int teamInPlayoff, int loops, Boolean isPlayoffFlag);

    void createTournament(Tournament tournament);

    void createTeamTable(SQLiteDatabase sqLiteDatabase, String tournTitle);

    void createGameTable(SQLiteDatabase sqLiteDatabase, String tournTitle);

    List<Tournament> readDBTourn(StartFragment startFragment);

    void delTournament(String title, Boolean isPlayoff);

    Tournament getTournament(String title);

    void createPlayoff(String playoffTitle, int countGames, int teamInPlayoff, List<Team> teamItems,
                       List<Game> gameList);

    Playoff getPlayoff(String playoffTitle);

    void delPlayoff(String title);

}
