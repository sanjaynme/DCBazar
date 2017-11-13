package com.dcbazar.np.homeModule.homeFragment.interactor;

import com.dcbazar.np.base.BaseInteractor;
import com.dcbazar.np.helpers.SharedPreferenceManager;

/**
 * Created by Amir on 18/07/2017.
 */

public class DialogInteractor extends BaseInteractor implements IDialogInteractor {

    private SharedPreferenceManager sharedPreferenceManager;

    public DialogInteractor(SharedPreferenceManager sharedPreferenceManager) {
        this.sharedPreferenceManager = sharedPreferenceManager;
    }



 /*   @Override
    public void saveSurveyStartLog(String surveyId, final BaseCallBack<AnswersPageResponse> baseCallBack) {
        String authenticationKey = sharedPreferenceManager.getStringValues(Contracts.SharedPrefKeys.AUTHENTICATION_KEY);
        SaveSurveyRequest saveSurveyRequest = new SaveSurveyRequest();
        saveSurveyRequest.setSurveyId(surveyResponseModel.getId().toString());
        Call<AnswersPageResponse> call = apiService.saveSurveyStartLog(authenticationKey, surveyId);
        call.enqueue(new Callback<AnswersPageResponse>() {
            @Override
            public void onResponse(Call<AnswersPageResponse> call, Response<AnswersPageResponse> response) {
                if (response.isSuccessful()) {
                    switch (response.body().getStatusCode()) {
                        case UrlHelper.ResponseCodes.SUCCESS:
                            baseCallBack.onSuccess(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<AnswersPageResponse> call, Throwable t) {

            }
        });
    }*/
}
