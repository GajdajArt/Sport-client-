package com.labralab.zmsportclient.models.simpleModels;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc on 06.01.2018.
 */

public class TournList {

    @SerializedName("tournaments")
    private List<SimpleTournament> list;

    public List<SimpleTournament> getList() {
        return list;
    }

    public void setList(List<SimpleTournament> list) {
        this.list = list;
    }

    public void addTournament(SimpleTournament tournament){

        if(list != null){
            list.add(tournament);
        }else {
            list = new ArrayList<>();
            list.add(tournament);
        }
    }
}