package com.dcbazar.np.homeModule.homeFragment.syncmodels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Sanjay on 7/19/2017.
 */

public class SurveyLogResponseModel {
    @SerializedName("modified_date")
    @Expose
    private String lastModifiedDate;
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("survey_answer_times")
    @Expose
    private int surveyAnswerTimes;
  @SerializedName("is_deleted")
    @Expose
    private int isDeleted;

    public String getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(String lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSurveyAnswerTimes() {
        return surveyAnswerTimes;
    }

    public void setSurveyAnswerTimes(int surveyAnswerTimes) {
        this.surveyAnswerTimes = surveyAnswerTimes;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }
}
