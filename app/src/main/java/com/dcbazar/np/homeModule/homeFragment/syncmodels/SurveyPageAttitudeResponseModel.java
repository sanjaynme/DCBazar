package com.dcbazar.np.homeModule.homeFragment.syncmodels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Sanjay on 5/25/2017.
 */

public class SurveyPageAttitudeResponseModel {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("survey_page_id")
    @Expose
    private Integer surveyPageId;
    @SerializedName("attitude_name")
    @Expose
    private String attitudeName;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("add_to_people_like_me")
    @Expose
    private Integer addToPeopleLikeMe;

    @SerializedName("deleted_at")
    @Expose
    private String deletedAt;

    @SerializedName("attitude_type_id")
    @Expose
    private Integer attitudeTypeId;

    @SerializedName("survey_page_range")
    @Expose
    public SurveyPageRangeResponseModel surveyPageRange;

    @SerializedName("survey_page_option")
    @Expose
    public ArrayList<SurveyPageOptionResponseModel> surveyPageOption = null;

    @SerializedName("survey_page_text_length")
    @Expose
    public SurveyPageTextLengthResponseModel surveyPageTextLength = null;

    @SerializedName("survey_answer")
    @Expose
    private String answer;

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSurveyPageId() {
        return surveyPageId;
    }

    public void setSurveyPageId(Integer surveyPageId) {
        this.surveyPageId = surveyPageId;
    }

    public String getAttitudeName() {
        return attitudeName;
    }

    public void setAttitudeName(String attitudeName) {
        this.attitudeName = attitudeName;
    }

    public String getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(String deletedAt) {
        this.deletedAt = deletedAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getAddToPeopleLikeMe() {
        return addToPeopleLikeMe;
    }

    public void setAddToPeopleLikeMe(Integer addToPeopleLikeMe) {
        this.addToPeopleLikeMe = addToPeopleLikeMe;
    }

    public Integer getAttitudeTypeId() {
        return attitudeTypeId;
    }

    public void setAttitudeTypeId(Integer attitudeTypeId) {
        this.attitudeTypeId = attitudeTypeId;
    }

    public SurveyPageRangeResponseModel getSurveyPageRange() {
        return surveyPageRange;
    }

    public void setSurveyPageRange(SurveyPageRangeResponseModel surveyPageRange) {
        this.surveyPageRange = surveyPageRange;
    }

    public ArrayList<SurveyPageOptionResponseModel> getSurveyPageOption() {
        return surveyPageOption;
    }

    public void setSurveyPageOption(ArrayList<SurveyPageOptionResponseModel> surveyPageOption) {
        this.surveyPageOption = surveyPageOption;
    }

    public SurveyPageTextLengthResponseModel getSurveyPageTextLength() {
        return surveyPageTextLength;
    }

    public void setSurveyPageTextLength(SurveyPageTextLengthResponseModel surveyPageTextLength) {
        this.surveyPageTextLength = surveyPageTextLength;
    }


    private class SurveyAnswer {
    }
}
