package com.dcbazar.np.loginModule.forgotPassword.presenter;

import com.ebpearls.choiceapp.base.BaseCallBack;
import com.ebpearls.choiceapp.loginModule.forgotPassword.interactor.ForgotPasswordInteractor;
import com.ebpearls.choiceapp.loginModule.forgotPassword.interactor.IForgotPasswordInteractor;
import com.ebpearls.choiceapp.loginModule.forgotPassword.model.ForgotPasswordResponseModel;
import com.ebpearls.choiceapp.loginModule.forgotPassword.view.IForgotPasswordView;

/**
 * Created by Sanjay on 4/5/2017.
 */

public class ForgetPasswordPresenter implements IForgetPasswordPresenter {
    private IForgotPasswordInteractor interactor;
    private IForgotPasswordView view;
    private ValidationOfEmail user;

    public ForgetPasswordPresenter(IForgotPasswordView view) {
        this.view = view;
        user = new ValidationOfEmail(view);
        this.interactor = new ForgotPasswordInteractor(this.view);
    }

    @Override
    public void submitEmail(String email) {
        user.checkUserValidity(email, this);
    }

    @Override
    public void onValidationSuccess(String email) {
        view.showProgressDialog("Resetting Password...");
        interactor.submitEmail(email, new BaseCallBack<ForgotPasswordResponseModel>() {
            @Override
            public void onSuccess(ForgotPasswordResponseModel responseData) {
                if (view != null) {
                    view.hideProgressDialog();
                    view.successReset(responseData.getMessage());
                }

            }

            @Override
            public void onError(String message) {
                if (view != null) {
                    view.hideProgressDialog();
                    view.showError(message);
                }
            }

            @Override
            public void onFailure(String message) {
                if (view != null) {
                    view.hideProgressDialog();
                    view.showMessageDialog(message);

                }
            }

            @Override
            public void onAuthenticationFailure(String message) {
                if (view != null) {
                    view.hideProgressDialog();
                    view.showMessageDialog(message);
                }

            }
        });
    }

    @Override
    public void onValidationError(String error) {
        if (view != null) {
            view.showError(error);
        }
    }

    @Override
    public void onDestroy() {
        //ChoiceApp.closeRealm();

        view = null;
    }
}

