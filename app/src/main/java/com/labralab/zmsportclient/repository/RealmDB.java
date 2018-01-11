package com.labralab.zmsportclient.repository;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.labralab.zmsportclient.models.Game;
import com.labralab.zmsportclient.models.Playoff;
import com.labralab.zmsportclient.models.Team;
import com.labralab.zmsportclient.models.Tournament;
import com.labralab.zmsportclient.models.simpleModels.SimpleTournament;
import com.labralab.zmsportclient.models.simpleModels.TournList;
import com.labralab.zmsportclient.utils.TournamentUtil;
import com.labralab.zmsportclient.views.fragments.StartFragment;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by pc on 23.12.2017.
 */

public class RealmDB implements TournamentRepository {

    Realm realm;
    FirebaseDatabase database;
    DatabaseReference myRef;
    Retrofit retrofit;
    Api api;
    Gson gson;
    TournList tournList;



    public RealmDB() {

        tournList = new TournList();

        realm = Realm.getDefaultInstance();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("tournaments");

        gson = new GsonBuilder()
                .setLenient()
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl("https://sporttournament-4679a.firebaseio.com")//url of firebase app
                .addConverterFactory(GsonConverterFactory.create(gson))//use for convert JSON file into object
                .build();

        api = retrofit.create(Api.class);

    }

    @Override
    public void createTournament(final Tournament tournament) {


        addToRealm(tournament);

        //copy to firebase
        SimpleTournament simpleTournament = TournamentUtil.tournToSimpleTourn(tournament);
        tournList.addTournament(simpleTournament);
        myRef.setValue(tournList);

    }

    @Override
    public void createTournament(String tournTitle, String year, String type, List<Team> teamItems, List<Game> gameList, int teamInPlayoff, int loops, Boolean isPlayoffFlag) {

    }

    @Override
    public void createTeamTable(SQLiteDatabase sqLiteDatabase, String tournTitle) {

    }

    @Override
    public void createGameTable(SQLiteDatabase sqLiteDatabase, String tournTitle) {

    }

    @Override
    public List<Tournament> readDBTourn(final StartFragment startFragment) {

        final List<Tournament> tournFromRealm = new ArrayList<>();
        RealmResults<Tournament> results = realm.where(Tournament.class).findAll();
        tournFromRealm.addAll(results);
        startFragment.setData(tournFromRealm);

        final List<Tournament> fBData = new ArrayList<>();

        Call<TournList> call = api.detData();

        call.enqueue(new Callback<TournList>() {
            @Override
            public void onResponse(Call<TournList> call, Response<TournList> response) {
                tournList = response.body();

            }

            @Override
            public void onFailure(Call<TournList> call, Throwable t) {

            }
        });
//
//        myRef.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//
//                SimpleTournament tournament = dataSnapshot.getValue(SimpleTournament.class);
//
//                if (tournament != null) {
//                    Tournament item = TournamentUtil.simpleTournToHard(tournament);
//                    delFromRealm(item.getTitle());
//                    addToRealm(item);
//
//                    fBData.add(item);
//                }
//                startFragment.setData(fBData);
//            }
//
//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onChildRemoved(DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });

        return tournFromRealm;
    }

    @Override
    public void delTournament(final String title, Boolean isPlayoff) {

        delFromRealm(title);
        readRealmDB();
        myRef.removeValue();
        myRef.setValue(tournList);

    }

    public Tournament getTournament(String title) {

        Tournament tournament = realm.where(Tournament.class)
                .equalTo("title", title)
                .findFirst();
        return tournament;
    }

    @Override
    public void createPlayoff(String playoffTitle, int countGames, int teamInPlayoff, List<Team> teamItems, List<Game> gameList) {
    }

    @Override
    public Playoff getPlayoff(String playoffTitle) {
        return null;
    }

    @Override
    public void delPlayoff(String title) {

    }

    public void delFromRealm(String title) {

        boolean flag = false;
        if (!realm.isInTransaction()) {
            realm.beginTransaction();
            flag = true;
        }

        RealmResults<Tournament> results = realm.where(Tournament.class)
                .equalTo("title", title)
                .findAll();
        results.deleteFirstFromRealm();

        if (flag) {
            realm.commitTransaction();
        }
    }

    public void addToRealm(Tournament tournament){

        //copy to Realm
        boolean flag = false;
        if (!realm.isInTransaction()) {
            realm.beginTransaction();
            flag = true;
        }

        realm.deleteAll();
        realm.copyToRealm(tournament);
        for(SimpleTournament simpleTournament : tournList.getList()){

            Tournament hardTournament = TournamentUtil.simpleTournToHard(simpleTournament);
            realm.copyToRealm(hardTournament);
        }

        if (flag) {
            realm.commitTransaction();
        }
    }
    public void readRealmDB (){

        RealmResults<Tournament> results = realm.where(Tournament.class).findAll();
        List<SimpleTournament> items = new ArrayList<>();
        for(Tournament tournament: results){
            items.add(TournamentUtil.tournToSimpleTourn(tournament));
        }
        tournList.setList(items);
    }
}
