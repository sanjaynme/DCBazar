package com.dcbazar.np.signUpModule.interactor;


import com.dcbazar.np.base.BaseCallBack;
import com.dcbazar.np.loginModule.login.model.LoginResponseModel;
import com.dcbazar.np.signUpModule.model.SignUpModel;
import com.dcbazar.np.signUpModule.model.SignUpResponseModel;

/**
 * Created by Amir on 31/03/2017.
 */

public interface ISignUpInteractor {

    void registerinApi(SignUpModel signUpModel, BaseCallBack<SignUpResponseModel> baseCallBack);

    void getFbId(String fbToken, BaseCallBack<LoginResponseModel> fbResponseModelBaseCallBack);

}
