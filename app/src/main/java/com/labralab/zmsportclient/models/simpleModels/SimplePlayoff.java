package com.labralab.zmsportclient.models.simpleModels;

import com.labralab.zmsportclient.models.Game;
import com.labralab.zmsportclient.models.Team;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc on 25.12.2017.
 */

public class SimplePlayoff {


    private String playoffTitle;

    private int countGames = -1;
    private int teamInPlayoff;

    private List<Game> playoffGameList = new ArrayList<>();
    private List<Team> playoffTeamList = new ArrayList<>();

    private List<Game> playoffFirstTurGames = new ArrayList<>();
    private List<Game> playoffSecondTurGames = new ArrayList<>();
    private List<Game> playoffLastTurGames = new ArrayList<>();

    public SimplePlayoff() {
    }

    public String getPlayoffTitle() {
        return playoffTitle;
    }

    public void setPlayoffTitle(String playoffTitle) {
        this.playoffTitle = playoffTitle;
    }

    public int getCountGames() {
        return countGames;
    }

    public void setCountGames(int countGames) {
        this.countGames = countGames;
    }

    public int getTeamInPlayoff() {
        return teamInPlayoff;
    }

    public void setTeamInPlayoff(int teamInPlayoff) {
        this.teamInPlayoff = teamInPlayoff;
    }

    public List<Game> getPlayoffGameList() {
        return playoffGameList;
    }

    public void setPlayoffGameList(List<Game> playoffGameList) {
        this.playoffGameList = playoffGameList;
    }

    public List<Team> getPlayoffTeamList() {
        return playoffTeamList;
    }

    public void setPlayoffTeamList(List<Team> playoffTeamList) {
        this.playoffTeamList = playoffTeamList;
    }

    public List<Game> getPlayoffFirstTurGames() {
        return playoffFirstTurGames;
    }

    public void setPlayoffFirstTurGames(List<Game> playoffFirstTurGames) {
        this.playoffFirstTurGames = playoffFirstTurGames;
    }

    public List<Game> getPlayoffSecondTurGames() {
        return playoffSecondTurGames;
    }

    public void setPlayoffSecondTurGames(List<Game> playoffSecondTurGames) {
        this.playoffSecondTurGames = playoffSecondTurGames;
    }

    public List<Game> getPlayoffLastTurGames() {
        return playoffLastTurGames;
    }

    public void setPlayoffLastTurGames(List<Game> playoffLastTurGames) {
        this.playoffLastTurGames = playoffLastTurGames;
    }
}
