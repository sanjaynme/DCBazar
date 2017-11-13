package com.dcbazar.np.homeModule.homeFragment.syncmodels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Sanjay on 5/26/2017.
 */

public class ScenarioResponseModel {

    @SerializedName("scenario")
    @Expose
    public Integer scenario;

    public String choice;

    public Integer getAnswer() {
        return answer;
    }

    public void setAnswer(Integer answer) {
        this.answer = answer;
    }

    @SerializedName("answer")
    @Expose
    private Integer answer;

    @SerializedName("attributes")
    @Expose
    public List<ScenarioAttributeResponseModel> attributes;

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    public Integer getScenario() {
        return scenario;
    }

    public void setScenario(Integer scenario) {
        this.scenario = scenario;
    }

    public List<ScenarioAttributeResponseModel> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<ScenarioAttributeResponseModel> attributes) {
        this.attributes = attributes;
    }

    public class DCEAnswers {

        @SerializedName("best_attribute_id")
        @Expose
        private Integer bestAttributeId;

        @SerializedName("worst_attribute_id")
        @Expose
        private Integer worstAttributeId;
        public Integer getBestAttributeId() {
            return bestAttributeId;
        }

        public void setBestAttributeId(Integer bestAttributeId) {
            this.bestAttributeId = bestAttributeId;
        }

        public Integer getWorstAttributeId() {
            return worstAttributeId;
        }

        public void setWorstAttributeId(Integer worstAttributeId) {
            this.worstAttributeId = worstAttributeId;
        }

    }
}
