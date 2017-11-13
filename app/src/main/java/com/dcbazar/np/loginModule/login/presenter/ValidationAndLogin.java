package com.dcbazar.np.loginModule.login.presenter;

import android.content.Context;

import com.ebpearls.choiceapp.R;
import com.ebpearls.choiceapp.helpers.CommonMethods;
import com.ebpearls.choiceapp.loginModule.login.view.ILoginView;
import com.facebook.AccessToken;
import com.facebook.login.LoginManager;

/**
 * Created by Sanjay on 3/30/2017.
 */

public class ValidationAndLogin {
    private final Context context;

    public ValidationAndLogin(ILoginView activity) {
        this.context = (Context) activity;

    }


    public void checkUserValidity(String email, String password, ILoginPresenter loginListener) {
        if (email.isEmpty()) {
            loginListener.onValidationError(this.context.getResources().getString(R.string.err_empty_email));
        } else if (!CommonMethods.isEmailValid(email.trim())) {
            loginListener.onValidationError(this.context.getResources().getString(R.string.err_valid_email));
        } else if (password.isEmpty()) {
            loginListener.onValidationError(this.context.getResources().getString(R.string.err_empty_password));
        } else {
            loginListener.onValidationSuccess(email, password);
        }
    }


    public void callFacebookLogout(Context context) {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        if (accessToken != null) {
            LoginManager.getInstance().logOut();
        }
    }
}
