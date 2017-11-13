package com.dcbazar.np.updateProfileModule.model;

import com.dcbazar.np.base.BaseResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Sanjay on 6/18/2017.
 */

public class UpdateIncomeRangeResponse extends BaseResponse {
    @SerializedName("results")
    @Expose
    public Results results;

    public class Results {

        @SerializedName("income_ranges")
        @Expose
        public List<IncomeRange> incomeRanges;
    }

    public static class IncomeRange {
        @SerializedName("id")
        @Expose
        public int id;
        @SerializedName("min")
        @Expose
        public int min;
        @SerializedName("max")
        @Expose
        public int max;

    }
}
