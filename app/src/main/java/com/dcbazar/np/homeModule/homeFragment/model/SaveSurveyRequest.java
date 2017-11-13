package com.dcbazar.np.homeModule.homeFragment.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Amir on 07/07/2017.
 */

public class SaveSurveyRequest {

    public String getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(String surveyId) {
        this.surveyId = surveyId;
    }

    @SerializedName("survey_id")
    private String surveyId;
}
