package com.dcbazar.np.homeModule.homeFragment.interactor;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Sanjay on 6/19/2017.
 */

public class SyncRequestModel {
    @SerializedName("timestamp")
    @Expose
    public int timeStamp;
    @Override
    public String toString() {
        return "{" +
                "timestamp='" + timeStamp + '\'' +
                '}';
    }
}
