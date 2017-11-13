package com.dcbazar.np.loginModule.forgotPassword.interactor;

import com.ebpearls.choiceapp.base.BaseCallBack;
import com.ebpearls.choiceapp.loginModule.forgotPassword.model.ForgotPasswordResponseModel;

/**
 * Created by Sanjay on 4/5/2017.
 */

public interface IForgotPasswordInteractor {
    void submitEmail(String email, BaseCallBack<ForgotPasswordResponseModel> forgotPasswordRequestModel);

}
