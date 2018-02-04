package com.labralab.zmsportclient.repository;

import com.labralab.zmsportclient.models.simpleModels.BasePOJO;
import com.labralab.zmsportclient.models.simpleModels.SimpleTournament;
import com.labralab.zmsportclient.models.simpleModels.TournList;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by pc on 06.01.2018.
 */

public interface Api {

    @GET("/.json")
    Call<BasePOJO> detData();
}