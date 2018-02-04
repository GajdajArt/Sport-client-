package com.labralab.zmsportclient.models.simpleModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by pc on 28.01.2018.
 */

public class BasePOJO  {

    @SerializedName("tournaments")
    @Expose
    public TournList tournaments;
}