package com.dcbazar.np.loginModule.forgotPassword.presenter;

import android.content.Context;

import com.ebpearls.choiceapp.R;
import com.ebpearls.choiceapp.helpers.CommonMethods;
import com.ebpearls.choiceapp.loginModule.forgotPassword.interactor.ForgotPasswordInteractor;
import com.ebpearls.choiceapp.loginModule.forgotPassword.view.IForgotPasswordView;

/**
 * Created by Sanjay on 4/17/2017.
 */

public class ValidationOfEmail {
    private Context context;
    private ForgotPasswordInteractor iForgotPasswordInteractor;
    private IForgotPasswordView iForgotPasswordView;

    public ValidationOfEmail(IForgotPasswordView iForgotPasswordView) {
        this.context = (Context) iForgotPasswordView;
        this.iForgotPasswordView = iForgotPasswordView;
        iForgotPasswordInteractor = new ForgotPasswordInteractor(this.iForgotPasswordView);
    }

    public void checkUserValidity(String email, IForgetPasswordPresenter forgetpasswordListener) {
        if (email.isEmpty()) {
            forgetpasswordListener.onValidationError(this.context.getResources().getString(R.string.err_empty_email));
        } else if (!CommonMethods.isEmailValid(email.trim())) {
            forgetpasswordListener.onValidationError(this.context.getResources().getString(R.string.err_valid_email));
        } else {
            forgetpasswordListener.onValidationSuccess(email);
        }
    }
}
