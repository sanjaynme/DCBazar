package com.dcbazar.np.homeModule.homeFragment.syncmodels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Sanjay on 5/30/2017.
 */

public class DCESurveyResponseModel {
    @SerializedName("desc")
    @Expose
    public String description;
    @SerializedName("left_icon")
    @Expose
    public String leftIcon;
    @SerializedName("right_icon")
    @Expose
    public String rightIcon;

    @SerializedName("scenarios")
    @Expose
    private List<ScenarioResponseModel> scenarios=null;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLeftIcon() {
        return leftIcon;
    }

    public void setLeftIcon(String leftIcon) {
        this.leftIcon = leftIcon;
    }

    public String getRightIcon() {
        return rightIcon;
    }

    public void setRightIcon(String rightIcon) {
        this.rightIcon = rightIcon;
    }

    public List<ScenarioResponseModel> getScenarios() {
        return scenarios;
    }

    public void setScenarios(List<ScenarioResponseModel> scenarios) {
        this.scenarios = scenarios;
    }


}
