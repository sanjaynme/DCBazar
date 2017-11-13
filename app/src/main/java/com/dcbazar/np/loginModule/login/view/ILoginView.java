package com.dcbazar.np.loginModule.login.view;


import com.ebpearls.choiceapp.base.BaseView;

/**
 * Created by Sanjay on 2/23/2017.
 */

public interface ILoginView extends BaseView {
    void showError(String error);

    void openForgetPasswordActivity();

    void navigateToUpdateProfile();

    void navigateToRegister(String firstName, String lastName, String email, String fbId);

    void navigateToHome();
}
