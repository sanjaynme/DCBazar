package com.dcbazar.np.homeModule.homeFragment.syncmodels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Sanjay on 6/6/2017.
 */

public class BWSScenariosResponseModel {
    @SerializedName("scenario")
    @Expose
    private int scenario;
    @SerializedName("attributes")
    @Expose
    private List<PositionAttributeResponseModel> attributes = null;

    @SerializedName("answer")
    @Expose
    private BwsAnswersResponseModel answer;

    private Integer bestAttributeId;
    private Integer worstAttributeId;

    public int getScenario() {
        return scenario;
    }

    public void setScenario(int scenario) {
        this.scenario = scenario;
    }

    public List<PositionAttributeResponseModel> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<PositionAttributeResponseModel> attributes) {
        this.attributes = attributes;
    }

    public Integer getBestAttributeId() {
        return bestAttributeId;
    }

    public void setBestAttributeId(Integer bestAttributeId) {
        this.bestAttributeId = bestAttributeId;
    }

    public BwsAnswersResponseModel getAnswer() {
        return answer;
    }

    public void setAnswer(BwsAnswersResponseModel answer) {
        this.answer = answer;
    }

    public Integer getWorstAttributeId() {
        return worstAttributeId;
    }

    public void setWorstAttributeId(Integer worstAttributeId) {
        this.worstAttributeId = worstAttributeId;
    }

    public static class BwsAnswersResponseModel {
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
