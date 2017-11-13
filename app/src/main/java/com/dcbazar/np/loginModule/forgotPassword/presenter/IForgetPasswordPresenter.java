package com.dcbazar.np.loginModule.forgotPassword.presenter;

import com.ebpearls.choiceapp.base.BasePresenter;

/**
 * Created by Sanjay on 4/5/2017.
 */

public interface IForgetPasswordPresenter extends BasePresenter{
    void submitEmail(String email);
    void onValidationSuccess(String email);



    void onValidationError(String error);
}
