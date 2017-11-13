package com.dcbazar.np.signUpModule.view;


import com.dcbazar.np.base.BaseView;

/**
 * Created by Amir on 31/03/2017.
 */

public interface ISignUpView extends BaseView {

    void navigateToLogin(String message);

    void onAlreadyUsedEmail();

    void showSignUpMessageDialog(final String message);



}
