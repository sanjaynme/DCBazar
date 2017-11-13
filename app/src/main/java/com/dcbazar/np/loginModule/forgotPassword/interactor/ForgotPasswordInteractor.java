package com.dcbazar.np.loginModule.forgotPassword.interactor;

import com.ebpearls.choiceapp.base.BaseCallBack;
import com.ebpearls.choiceapp.base.RetrofitCallBack;
import com.ebpearls.choiceapp.loginModule.forgotPassword.model.ForgetPasswordRequestModel;
import com.ebpearls.choiceapp.loginModule.forgotPassword.model.ForgotPasswordResponseModel;
import com.ebpearls.choiceapp.loginModule.forgotPassword.view.IForgotPasswordView;
import com.ebpearls.choiceapp.retrofitApi.RetrofitSingleton;
import com.ebpearls.choiceapp.retrofitApi.WebserviceApi;
import com.google.gson.Gson;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;

/**
 * Created by Sanjay on 4/5/2017.
 */

public class ForgotPasswordInteractor implements IForgotPasswordInteractor {
    private final IForgotPasswordView iForgotView;
    private WebserviceApi apiService;


    public ForgotPasswordInteractor(IForgotPasswordView iForgotPasswordView) {
        this.apiService = RetrofitSingleton.getApiService();
        this.iForgotView = iForgotPasswordView;
    }


    @Override
    public void submitEmail(String email, final BaseCallBack<ForgotPasswordResponseModel> forgotPasswordResponseModel) {
        final ForgetPasswordRequestModel forgetPasswordRequestModel = new ForgetPasswordRequestModel();

        forgetPasswordRequestModel.email = email;
        String jsonString = new Gson().toJson(forgetPasswordRequestModel);

        final RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), jsonString);
        final Call<ForgotPasswordResponseModel> forgotPassword = apiService.forgetPassword(requestBody);
        forgotPassword.enqueue(new RetrofitCallBack<ForgotPasswordResponseModel>(forgotPasswordResponseModel) {
            @Override
            public ForgotPasswordResponseModel modifyData(ForgotPasswordResponseModel response) {
                return response;
            }
        });
    }
}
