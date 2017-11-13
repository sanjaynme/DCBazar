package com.dcbazar.np.loginModule.login.interactor;

import com.ebpearls.choiceapp.base.BaseCallBack;
import com.ebpearls.choiceapp.loginModule.login.model.LoginResponseModel;


/**
 * Created by Sanjay on 2/23/2017.
 */

public interface ILoginInteractor  {

    void loginUser(String email, String password, BaseCallBack<LoginResponseModel> normalLoginResponseCallback);

    void loginWithFacebook(String token, BaseCallBack<LoginResponseModel> loginResponseCallBack);



}
