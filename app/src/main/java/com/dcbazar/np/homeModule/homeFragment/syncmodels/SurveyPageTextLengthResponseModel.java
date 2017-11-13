package com.dcbazar.np.homeModule.homeFragment.syncmodels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SurveyPageTextLengthResponseModel {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("survey_page_attitude_id")
    @Expose
    private Integer surveyPageAttitudeId;
    @SerializedName("length")
    @Expose
    private Integer length;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSurveyPageAttitudeId() {
        return surveyPageAttitudeId;
    }

    public void setSurveyPageAttitudeId(Integer surveyPageAttitudeId) {
        this.surveyPageAttitudeId = surveyPageAttitudeId;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }
}