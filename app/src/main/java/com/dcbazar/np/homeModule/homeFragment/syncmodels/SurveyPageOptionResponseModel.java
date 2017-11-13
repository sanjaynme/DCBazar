package com.dcbazar.np.homeModule.homeFragment.syncmodels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Sanjay on 5/26/2017.
 */

public class SurveyPageOptionResponseModel {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("survey_page_attitude_id")
    @Expose
    private Integer surveyPageAttitudeId;
    @SerializedName("option")
    @Expose
    private String option;
    @SerializedName("deleted_at")
    @Expose
    private String deletedAt;

    private boolean checked;



    public boolean getChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
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

    public Integer getSurveyPageAttitudeId() {
        return surveyPageAttitudeId;
    }

    public void setSurveyPageAttitudeId(Integer surveyPageAttitudeId) {
        this.surveyPageAttitudeId = surveyPageAttitudeId;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }
}
