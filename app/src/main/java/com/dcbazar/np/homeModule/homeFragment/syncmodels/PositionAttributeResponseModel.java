package com.dcbazar.np.homeModule.homeFragment.syncmodels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Sanjay on 6/1/2017.
 */

public class PositionAttributeResponseModel {
    @SerializedName("survey_attribute_id")
    @Expose
    private Integer surveyAttributeId;

    @SerializedName("attribute_name")
    @Expose
    private String attributeName;
    @SerializedName("survey_attribute_position_id")
    @Expose
    private Integer surveyAttributePositionId;
    @SerializedName("attribute_text")
    @Expose
    private String attributeText;

    public Integer getSurveyAttributeId() {
        return surveyAttributeId;
    }

    public void setSurveyAttributeId(Integer surveyAttributeId) {
        this.surveyAttributeId = surveyAttributeId;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public String getAttributeText() {
        return attributeText;
    }

    public void setAttributeText(String attributeText) {
        this.attributeText = attributeText;
    }

    /*public String getBestAttributeId() {
        return bestAttributeId;
    }

    public void setBestAttributeId(String bestAttributeId) {
        this.bestAttributeId = bestAttributeId;
    }

    public String getWorstAttributeId() {
        return worstAttributeId;
    }

    public void setWorstAttributeId(String worstAttributeId) {
        this.worstAttributeId = worstAttributeId;
    }*/

    public Integer getSurveyAttributePositionId() {
        return surveyAttributePositionId;
    }

    public void setSurveyAttributePositionId(Integer surveyAttributePositionId) {
        this.surveyAttributePositionId = surveyAttributePositionId;
    }
}
