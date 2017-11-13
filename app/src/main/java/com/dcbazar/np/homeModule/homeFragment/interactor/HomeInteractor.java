package com.dcbazar.np.homeModule.homeFragment.interactor;

import com.ebpearls.choiceapp.base.BaseCallBack;
import com.ebpearls.choiceapp.base.BaseDatabaseCallback;
import com.ebpearls.choiceapp.base.BaseDatabaseListCallback;
import com.ebpearls.choiceapp.base.BaseInteractor;
import com.ebpearls.choiceapp.helpers.AppLog;
import com.ebpearls.choiceapp.helpers.CommonMethods;
import com.ebpearls.choiceapp.helpers.Contracts;
import com.ebpearls.choiceapp.helpers.SharedPreferenceManager;
import com.ebpearls.choiceapp.helpers.UrlHelper;
import com.ebpearls.choiceapp.homeModule.homeFragment.syncmodels.SurveyResponseModel;
import com.ebpearls.choiceapp.homeModule.homeFragment.syncmodels.SyncResponseModel;
import com.ebpearls.choiceapp.inappdatabase.repository.HistoryRepository.HistoryRepository;
import com.ebpearls.choiceapp.inappdatabase.repository.UserRepository.UserRepository;
import com.ebpearls.choiceapp.inappdatabase.repository.surveyRepository.SurveysRepository;
import com.ebpearls.choiceapp.inappdatabase.tables.UserTable;
import com.ebpearls.choiceapp.loginModule.login.model.DeviceUpdateRequest;
import com.ebpearls.choiceapp.loginModule.login.model.DeviceUpdateResponse;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Amir on 20/04/2017.
 */

public class HomeInteractor extends BaseInteractor implements IHomeInteractor {

    private int userId;
    private UserRepository userRepository;
    private SharedPreferenceManager sharedPreferenceManager;
    private SurveysRepository surveysRepository;
    SurveyResponseModel dbSurveyResponseModel;
    HistoryRepository historyRepository;

    public HomeInteractor(SharedPreferenceManager sharedPreferenceManager) {
        this.sharedPreferenceManager = sharedPreferenceManager;
        surveysRepository = new SurveysRepository();
        dbSurveyResponseModel = new SurveyResponseModel();
        userRepository = new UserRepository(sharedPreferenceManager);
        userId = sharedPreferenceManager.getIntValues(Contracts.SharedPrefKeys.USER_ID);
        historyRepository = new HistoryRepository(sharedPreferenceManager);

    }


    @Override
    public void insertSyncDataIntoHistory(SurveyResponseModel surveyResponseModel, BaseDatabaseCallback<SurveyResponseModel> baseDatabaseCallback) {
        historyRepository = new HistoryRepository(sharedPreferenceManager);
        historyRepository.insertSurveysToHistory(surveyResponseModel);
        baseDatabaseCallback.onSuccess(surveyResponseModel);
    }


    @Override
    public void getData(final BaseDatabaseListCallback<List<SurveyResponseModel>> baseCallBack) {
        String authenticationKey = sharedPreferenceManager.getStringValues(Contracts.SharedPrefKeys.AUTHENTICATION_KEY);
        UserTable userTable = userRepository.getUserInfo();
        int syncTimeStamp = 0;
        if (userTable != null) {
            syncTimeStamp = userTable.getSyncTimeStamp();
            if (syncTimeStamp == 0) {
                syncTimeStamp = 0;
            }
        }
        final SyncRequestModel syncRequestModel = new SyncRequestModel();
        syncRequestModel.timeStamp = syncTimeStamp;
        Call<SyncResponseModel> call = apiService.syncServer(authenticationKey, syncRequestModel);
        call.enqueue(new Callback<SyncResponseModel>() {
            @Override
            public void onResponse(Call<SyncResponseModel> call, Response<SyncResponseModel> response) {
                if (response.isSuccessful()) {
                    switch (response.body().getStatusCode()) {
                        case UrlHelper.ResponseCodes.SUCCESS:
                            //insertSurveysForUserSurvey all data to userTables
                            userRepository.insertSurveysForUserSurvey(response.body(), baseCallBack);
                            break;

                        case UrlHelper.ResponseCodes.INVALID_EMAIL_PASSWORD:
                            baseCallBack.onFailure(response.body().getMessage());
                            break;

                        case UrlHelper.ResponseCodes.CANNOT_CREATE_TOKEN:
                            baseCallBack.onError(response.body().getMessage());
                            break;

                        case UrlHelper.ResponseCodes.INVALID_CREDENTIALS:
                            baseCallBack.onError(response.body().getMessage());
                            break;

                        case UrlHelper.ResponseCodes.INVALID_USERS:
                            baseCallBack.onError(response.body().getMessage());
                            break;

                        case UrlHelper.ResponseCodes.OTHER_FAILURES:
                            baseCallBack.onError(response.body().getMessage());
                            break;

                        case UrlHelper.ResponseCodes.FAILURE:
                            baseCallBack.onSuccess(response.body().results.survey);
                            break;
                    }
                }
            }

            @Override
            public void onFailure(Call<SyncResponseModel> call, Throwable t) {
                if (t != null) {
                    if (t instanceof UnknownHostException || t instanceof SocketTimeoutException) {
                        //load from DB
                        int userId = sharedPreferenceManager.getIntValues(Contracts.SharedPrefKeys.USER_ID);
                        List<SurveyResponseModel> list;
                        Date deviceDate = CommonMethods.convertDeviceDate(Calendar.getInstance().getTime());
                        list = surveysRepository.getListByUserId(deviceDate, userId);

                        if (list.isEmpty()) {
                            baseCallBack.onListEmpty("No Survey Lists Available");
                        } else {
                            for (int i = 0; i < list.size(); i++) {
                                if (list.get(i).getIsRecurring() == Contracts.IsRecurring.NO || list.get(i).getRecurringTypeId() == Contracts.RepeatingType.NONREPEATING) {
                                    if (list.get(i).getTimes() == list.get(i).getSurveyCount()) {
                                        list.remove(i);
                                        i--;
                                    }
                                }
                            }
                            baseCallBack.onSuccess(list);
                        }
                    } else {
                        baseCallBack.onError(Contracts.Errors.ERROR);
                    }
                }
            }
        });
    }

    @Override
    public void sendDeviceInfo(DeviceUpdateRequest deviceUpdateRequest, final BaseCallBack<String> callBack) {
        Call<DeviceUpdateResponse> updatedevice = apiService.addUpdateDeviceInfo(sharedPreferenceManager.getStringValues(Contracts.SharedPrefKeys.AUTHENTICATION_KEY), deviceUpdateRequest);
        updatedevice.enqueue(new Callback<DeviceUpdateResponse>() {
            @Override
            public void onResponse(Call<DeviceUpdateResponse> call, Response<DeviceUpdateResponse> response) {
                if (response.isSuccessful()) {
                    switch (response.body().getStatusCode()) {
                        case UrlHelper.ResponseCodes.SUCCESS:
                            callBack.onSuccess(response.body().getMessage());
                            break;
                        default:
                            callBack.onFailure(response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<DeviceUpdateResponse> call, Throwable t) {
                callBack.onFailure(t.getMessage());
                AppLog.d("message", t.getMessage());
            }
        });
    }

    @Override
    public void getSurveyDataFromDb(int surveyId, int userid, BaseDatabaseListCallback<SurveyResponseModel> baseDatabaseListCallback) {
        Date deviceDate = CommonMethods.convertDeviceDate(Calendar.getInstance().getTime());
        SurveyResponseModel surveyResponseModel = surveysRepository.getBySurveyId(surveyId, userid, deviceDate);
        baseDatabaseListCallback.onSuccess(surveyResponseModel);
    }
}

