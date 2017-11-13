package com.dcbazar.np.homeModule.homeFragment.syncmodels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Sanjay on 6/1/2017.
 */

public class ScenarioAttributeResponseModel {
    @SerializedName("survey_attribute_id")
    @Expose
    private Integer surveyAttributeId;
    @SerializedName("attribute_name")
    @Expose
    private String attributeName;
    @SerializedName("attribute_text")
    @Expose
    private String attributeText;
    @SerializedName("level")
    @Expose
    private String level;

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

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


}
