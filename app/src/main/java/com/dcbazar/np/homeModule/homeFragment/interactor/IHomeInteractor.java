package com.dcbazar.np.homeModule.homeFragment.interactor;

import com.ebpearls.choiceapp.base.BaseCallBack;
import com.ebpearls.choiceapp.base.BaseDatabaseCallback;
import com.ebpearls.choiceapp.base.BaseDatabaseListCallback;
import com.ebpearls.choiceapp.homeModule.homeFragment.syncmodels.SurveyResponseModel;
import com.ebpearls.choiceapp.loginModule.login.model.DeviceUpdateRequest;

import java.util.List;

/**
 * Created by Amir on 20/04/2017.
 */

public interface IHomeInteractor {

    void insertSyncDataIntoHistory(SurveyResponseModel surveyResponseModel, BaseDatabaseCallback<SurveyResponseModel> baseDatabaseCallback);

    void getData(BaseDatabaseListCallback<List<SurveyResponseModel>> baseCallBack);

    void sendDeviceInfo(DeviceUpdateRequest deviceUpdateRequest, BaseCallBack<String> callBack);

    void getSurveyDataFromDb(int surveyId, int userid, BaseDatabaseListCallback<SurveyResponseModel> baseDatabaseListCallback);

//    void getSurveyList(int surveyId, int userid, BaseCallBack<SurveyResponseModel> baseCallBack);

//    void saveSurveyStartLog(String surveyId, BaseCallBack<AnswersPageResponse> baseCallBack);


}
