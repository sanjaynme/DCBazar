package com.dcbazar.np.homeModule.homeFragment.syncmodels;

import com.ebpearls.choiceapp.base.BaseResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

/**
 * Created by Sanjay on 5/25/2017.
 */

public class SurveyResponseModel extends BaseResponse {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("user_Id")
    @Expose
    private Integer userId;
    @SerializedName("created_by")
    @Expose
    private Integer createdBy;
    @SerializedName("survey_type_id")
    @Expose
    private int surveyTypeId;
    @SerializedName("is_recurring")
    @Expose
    private Integer isRecurring;
    @SerializedName("is_limited")
    @Expose
    private Integer isLimited;
    @SerializedName("times")
    @Expose
    private int times;
    @SerializedName("recurring_type_id")
    @Expose
    private Integer recurringTypeId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("start_date")
    @Expose
    private Date startDate;
    @SerializedName("end_date")
    @Expose
    private Date endDate;
    @SerializedName("profile_image")
    @Expose
    private String profileImage;
    @SerializedName("desc")
    @Expose
    private String description;
    @SerializedName("introduction")
    @Expose
    private String introduction;
    @SerializedName("background_image")
    @Expose
    private String backgroundImage;
    @SerializedName("survey_question_text")
    @Expose
    private String surveyQuestionText;
    @SerializedName("deleted_at")
    @Expose
    private String deletedAt;

    @SerializedName("bws_survey")
    @Expose
    private BwsSurveyResponseModel bwsSurvey;

    @SerializedName("modified_date")
    @Expose
    private String modifiedDate;

    @SerializedName("created_at")
    @Expose
    private String createdAt;

    @SerializedName("answer_times")
    @Expose
    private Integer surveyAnswerTimes;

    @SerializedName("survey_page")
    @Expose
    public List<SurveyPageResponseModel> surveyPage;

    @SerializedName("dce_survey")
    @Expose
    private DCESurveyResponseModel dceSurvey;

    private int surveyCount;
    private String lastModifiedDate;
    private int isDeleted;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public int getSurveyTypeId() {
        return surveyTypeId;
    }

    public void setSurveyTypeId(int surveyTypeId) {
        this.surveyTypeId = surveyTypeId;
    }

    public Integer getIsRecurring() {
        return isRecurring;
    }

    public void setIsRecurring(Integer isRecurring) {
        this.isRecurring = isRecurring;
    }

    public Integer getIsLimited() {
        return isLimited;
    }

    public void setIsLimited(Integer isLimited) {
        this.isLimited = isLimited;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public Integer getRecurringTypeId() {
        return recurringTypeId;
    }

    public void setRecurringTypeId(Integer recurringTypeId) {
        this.recurringTypeId = recurringTypeId;
    }

    public String getName() {
        return name;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(String backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public BwsSurveyResponseModel getBwsSurvey() {
        return bwsSurvey;
    }

    public void setBwsSurvey(BwsSurveyResponseModel bwsSurvey) {
        this.bwsSurvey = bwsSurvey;
    }

    public List<SurveyPageResponseModel> getSurveyPage() {
        return surveyPage;
    }

    public void setSurveyPage(List<SurveyPageResponseModel> surveyPage) {
        this.surveyPage = surveyPage;
    }

    public String getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(String deletedAt) {
        this.deletedAt = deletedAt;
    }

    public DCESurveyResponseModel getDceSurvey() {
        return dceSurvey;
    }

    public void setDceSurvey(DCESurveyResponseModel dceSurvey) {
        this.dceSurvey = dceSurvey;
    }

    public String getModifieddate() {
        return modifiedDate;
    }

    public void setModifieddate(String modifieddate) {
        this.modifiedDate = modifieddate;
    }

    public Integer getSurveyAnswerTimes() {
        return surveyAnswerTimes;
    }

    public void setSurveyAnswerTimes(Integer surveyAnswerTimes) {
        this.surveyAnswerTimes = surveyAnswerTimes;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public int getSurveyCount() {
        return surveyCount;
    }

    public void setSurveyCount(int surveyCount) {
        this.surveyCount = surveyCount;
    }

    public String getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(String lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getSurveyQuestionText() {
        return surveyQuestionText;
    }

    public void setSurveyQuestionText(String surveyQuestionText) {
        this.surveyQuestionText = surveyQuestionText;
    }
}
