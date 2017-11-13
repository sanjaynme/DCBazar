package com.dcbazar.np.homeModule.homeFragment.model;

import com.ebpearls.choiceapp.base.BaseResponse;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Amir on 07/07/2017.
 */

public class SaveSurveyResponse extends BaseResponse {

    @SerializedName("results")
    private Results results;

    public class Results{
        @SerializedName("modified_date")
        private String modifiedDate;

        public String getModifiedDate() {
            return modifiedDate;
        }

        public void setModifiedDate(String modifiedDate) {
            this.modifiedDate = modifiedDate;
        }
    }

    public Results getResults() {
        return results;
    }

    public void setResults(Results results) {
        this.results = results;
    }
}
