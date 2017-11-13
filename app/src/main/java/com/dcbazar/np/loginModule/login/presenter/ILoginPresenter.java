package com.dcbazar.np.loginModule.login.presenter;

import com.ebpearls.choiceapp.base.BasePresenter;
import com.ebpearls.choiceapp.loginModule.login.model.LoginResponseModel;

import retrofit2.Response;

/**
 * Created by Sanjay on 2/23/2017.
 */

public interface ILoginPresenter extends BasePresenter {
    void doNormalLogin(String email, String password);

    void errorFacebookLogin(String message);

    void forgetPasswordButtonClicked();

    void onFacebookResponse(String fbToken);

    void onLoginResponse(String login_type, Response<LoginResponseModel> response, String name, String email, String token);

    void onValidationSuccess(String name, String passwd);

    void onValidationError(String error);
}
