package com.dcbazar.np.loginModule.forgotPassword.view;

import com.ebpearls.choiceapp.base.BaseView;

/**
 * Created by Sanjay on 4/5/2017.
 */

public interface IForgotPasswordView extends BaseView {
    void setupViews();

    void showError(String message);

    void successReset(String message);

    void navigateToLogin(String message);

    void showForgetMessageDialog(final String message);



}
