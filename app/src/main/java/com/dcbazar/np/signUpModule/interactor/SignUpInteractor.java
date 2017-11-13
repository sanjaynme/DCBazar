package com.dcbazar.np.signUpModule.interactor;

import com.dcbazar.np.base.BaseCallBack;
import com.dcbazar.np.base.BaseInteractor;
import com.dcbazar.np.base.RetrofitCallBack;
import com.dcbazar.np.helpers.Contracts;
import com.dcbazar.np.helpers.SharedPreferenceManager;
import com.dcbazar.np.loginModule.login.model.FbRequestModel;
import com.dcbazar.np.loginModule.login.model.LoginResponseModel;
import com.dcbazar.np.signUpModule.model.SignUpModel;
import com.dcbazar.np.signUpModule.model.SignUpResponseModel;

import retrofit2.Call;

/**
 * Created by Amir on 31/03/2017.
 */

public class SignUpInteractor extends BaseInteractor implements ISignUpInteractor{


    SharedPreferenceManager sharedPreferenceManager;
    public SignUpInteractor(SharedPreferenceManager sharedPreferenceManager){
        this.sharedPreferenceManager = sharedPreferenceManager;
    }


    @Override
    public void registerinApi(final SignUpModel signUpModel, BaseCallBack<SignUpResponseModel> baseCallBack) {
        Call<SignUpResponseModel> call = apiService.registerUser(signUpModel);
        call.enqueue(new RetrofitCallBack<SignUpResponseModel>(baseCallBack) {
            @Override
            public SignUpResponseModel modifyData(SignUpResponseModel response) {
                storeUsers(signUpModel);
                return response;
            }

            private void storeUsers(SignUpModel signUpModel) {
                sharedPreferenceManager.setKeyValues(Contracts.SharedPrefKeys.PROFILE_FIRST_NAME,signUpModel.getFirstName());
                sharedPreferenceManager.setKeyValues(Contracts.SharedPrefKeys.PROFILE_LAST_NAME,signUpModel.getLastName());
                sharedPreferenceManager.setKeyValues(Contracts.SharedPrefKeys.PROFILE_EMAIL,signUpModel.getEmail());
            }
        });
    }

    @Override
    public void getFbId(String fbToken, BaseCallBack<LoginResponseModel> fbResponseModelBaseCallBack) {
        FbRequestModel fbRequestModel = new FbRequestModel();
        fbRequestModel.fbToken = fbToken;
        Call<LoginResponseModel> fbResponseModelCall = apiService.fbLoginUser(fbRequestModel);
        fbResponseModelCall.enqueue(new RetrofitCallBack<LoginResponseModel>(fbResponseModelBaseCallBack) {
            @Override
            public LoginResponseModel modifyData(LoginResponseModel response) {
                return response;
            }
        });
    }


}
