package com.dcbazar.np.homeModule.homeFragment.syncmodels;

import com.ebpearls.choiceapp.base.BaseResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Sanjay on 5/25/2017.
 */

public class SyncResponseModel extends BaseResponse {
    @SerializedName("results")
    @Expose
    public Results results;

    public Results getResults() {
        return results;
    }

    public void setResults(Results results) {
        this.results = results;
    }
}
