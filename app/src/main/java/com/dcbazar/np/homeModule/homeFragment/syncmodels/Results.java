package com.dcbazar.np.homeModule.homeFragment.syncmodels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Sanjay on 5/25/2017.
 */

public class Results {
    @SerializedName("current_time")
    @Expose
    public int currentTime;
    @SerializedName("survey")
    @Expose
    public List<SurveyResponseModel> survey;
    @SerializedName("survey_log")
    @Expose
    public List<SurveyLogResponseModel> surveyLog = null;

    public List<SurveyLogResponseModel> getSurveyLog() {
        return surveyLog;
    }

    public void setSurveyLog(List<SurveyLogResponseModel> surveyLog) {
        this.surveyLog = surveyLog;
    }

    public int getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(int currentTime) {
        this.currentTime = currentTime;
    }

    public List<SurveyResponseModel> getSurvey() {
        return survey;
    }

    public void setSurvey(List<SurveyResponseModel> survey) {
        this.survey = survey;
    }
}
