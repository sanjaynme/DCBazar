package com.dcbazar.np.updateProfileModule.model;

import com.dcbazar.np.base.BaseResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Sanjay on 6/18/2017.
 */

public class UpdateOccupationListResponse extends BaseResponse {
    @SerializedName("results")
    @Expose
    public Results results;

    public class Results {
        @SerializedName("occupations")
        @Expose
        public List<Occupation> occupations = null;
    }

    public static class Occupation {
        @SerializedName("id")
        @Expose
        public Integer id;
        @SerializedName("occupation")
        @Expose
        public String occupation;
    }
}
