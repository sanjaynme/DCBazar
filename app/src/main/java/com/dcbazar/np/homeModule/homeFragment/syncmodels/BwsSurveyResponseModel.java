package com.dcbazar.np.homeModule.homeFragment.syncmodels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Sanjay on 6/6/2017.
 */

public class BwsSurveyResponseModel {

    @SerializedName("desc")
    @Expose
    public String desc;
    @SerializedName("left_icon")
    @Expose
    public String leftIcon;
    @SerializedName("right_icon")
    @Expose
    public String rightIcon;
    @SerializedName("best_text")
    @Expose
    private String bestText;
    @SerializedName("worst_text")
    @Expose
    private String worstText;
    @SerializedName("scenarios")
    @Expose
    public List<BWSScenariosResponseModel> scenarios ;

    public String getBestText() {
        return bestText;
    }

    public void setBestText(String bestText) {
        this.bestText = bestText;
    }

    public String getWorstText() {
        return worstText;
    }

    public void setWorstText(String worstText) {
        this.worstText = worstText;
    }

    public List<BWSScenariosResponseModel> getScenarios() {
        return scenarios;
    }

    public void setScenarios(List<BWSScenariosResponseModel> scenarios) {
        this.scenarios = scenarios;
    }


    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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

    public List<BWSScenariosResponseModel> getPositions() {
        return scenarios;
    }

    public void setPositions(List<BWSScenariosResponseModel> scenarios) {
        this.scenarios = scenarios;
    }
}
