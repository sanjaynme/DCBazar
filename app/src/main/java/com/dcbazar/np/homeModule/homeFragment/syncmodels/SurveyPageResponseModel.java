package com.dcbazar.np.homeModule.homeFragment.syncmodels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Sanjay on 5/25/2017.
 */

public class SurveyPageResponseModel {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("survey_id")
    @Expose
    private Integer surveyId;
    @SerializedName("page_no")
    @Expose
    private Integer pageNo;
    @SerializedName("deleted_at")
    @Expose
    private String deletedAt;

    @SerializedName("survey_page_attitude")
    @Expose
    public List<SurveyPageAttitudeResponseModel> surveyPageAttitude;

    public List<SurveyPageAttitudeResponseModel> getSurveyPageAttitude() {
        return surveyPageAttitude;
    }

    public void setSurveyPageAttitude(List<SurveyPageAttitudeResponseModel> surveyPageAttitude) {
        this.surveyPageAttitude = surveyPageAttitude;
    }

    public String getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(String deletedAt) {
        this.deletedAt = deletedAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(Integer surveyId) {
        this.surveyId = surveyId;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }


}
