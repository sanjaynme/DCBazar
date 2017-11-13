package com.dcbazar.np.homeModule.homeFragment.syncmodels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Sanjay on 5/25/2017.
 */

public class SurveyPageRangeResponseModel {
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("survey_page_attitude_id")
    @Expose
    public Integer surveyPageAttitudeId;
    @SerializedName("range_start")
    @Expose
    public Integer rangeStart;
    @SerializedName("range_end")
    @Expose
    public Integer rangeEnd;
    @SerializedName("included_range")
    @Expose
    public double includedRange;
    @SerializedName("interval")
    @Expose
    public Integer interval;

    @SerializedName("deleted_at")
    @Expose
    private String deletedAt;

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

    public Integer getRangeStart() {
        return rangeStart;
    }

    public void setRangeStart(Integer rangeStart) {
        this.rangeStart = rangeStart;
    }

    public Integer getRangeEnd() {
        return rangeEnd;
    }

    public void setRangeEnd(Integer rangeEnd) {
        this.rangeEnd = rangeEnd;
    }

    public double getIncludedRange() {
        return includedRange;
    }

    public void setIncludedRange(double includedRange) {
        this.includedRange = includedRange;
    }

    public Integer getInterval() {
        return interval;
    }

    public void setInterval(Integer interval) {
        this.interval = interval;
    }
}
