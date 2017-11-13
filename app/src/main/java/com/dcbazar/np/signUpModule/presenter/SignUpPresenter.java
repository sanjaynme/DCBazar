package com.dcbazar.np.signUpModule.presenter;

import android.content.Context;
import android.util.Patterns;

import com.dcbazar.np.R;
import com.dcbazar.np.base.BaseCallBack;
import com.dcbazar.np.helpers.SharedPreferenceManager;
import com.dcbazar.np.loginModule.login.model.LoginResponseModel;
import com.dcbazar.np.signUpModule.interactor.ISignUpInteractor;
import com.dcbazar.np.signUpModule.interactor.SignUpInteractor;
import com.dcbazar.np.signUpModule.model.SignUpModel;
import com.dcbazar.np.signUpModule.model.SignUpResponseModel;
import com.dcbazar.np.signUpModule.view.ISignUpView;


/**
 * Created by Amir on 31/03/2017.
 */

public class SignUpPresenter implements ISignUpPresenter {

    ISignUpView view;
    ISignUpInteractor interactor;
    Context context;
    SharedPreferenceManager sharedPreferenceManager;

    public SignUpPresenter(ISignUpView view, SharedPreferenceManager sharedPreferenceManager) {
        this.view = view;
        interactor = new SignUpInteractor(sharedPreferenceManager);
        this.context = (Context) view;
        this.sharedPreferenceManager = sharedPreferenceManager;
        interactor = new SignUpInteractor(sharedPreferenceManager);
    }

    @Override
    public void populatespinner() {

    }

    @Override
    public boolean validateFields(SignUpModel signUpModel) {
        if (signUpModel.getFirstName().isEmpty()) {
            view.showMessageDialog(context.getString(R.string.error_firstname));
            return false;
        } else if (signUpModel.getLastName().isEmpty()) {
            view.showMessageDialog(context.getString(R.string.error_lastname));
            return false;
        } else if (signUpModel.getEmail().isEmpty()) {
            view.showMessageDialog(context.getString(R.string.err_empty_email));
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(signUpModel.getEmail()).matches()) {
            view.showMessageDialog(context.getString(R.string.error_email_valid));
            return false;
        } else if (signUpModel.getPassword().length() == 0) {
            view.showMessageDialog(context.getString(R.string.err_empty_password));
            return false;
        } else if (signUpModel.getPassword().length() < 8) {
            view.showMessageDialog(context.getString(R.string.error_password_length));
            return false;
        }/*else if(!(signUpModel.getTerms().equalsIgnoreCase("true"))){
            view. showMessageDialog("Please agree to the terms");
            return false;
        }*/
        return true;
    }


    @Override
    public void sendRegisterDetails(SignUpModel signUpModel) {
        view.showProgressDialog("Signing up...");
        interactor.registerinApi(signUpModel, new BaseCallBack<SignUpResponseModel>() {
            @Override
            public void onSuccess(SignUpResponseModel responseData) {
                if (view != null) {
                    view.hideProgressDialog();
                    view.navigateToLogin(responseData.getMessage());
                }

            }

            @Override
            public void onAuthenticationFailure(String message) {
                if (view != null) {
                    view.hideProgressDialog();
                    view.showMessageDialog(message);
                }

            }

            @Override
            public void onError(String message) {
                if (view != null) {
                    view.hideProgressDialog();
                    view.showMessageDialog(message);
                }

            }

            @Override
            public void onFailure(String message) {
                if (view != null) {
                    view.hideProgressDialog();
                    view.showMessageDialog(message);
                    view.onAlreadyUsedEmail();

                }

            }
        });
    }

    @Override
    public void getFacebookId(String fbToken) {
        view.showProgressDialog("Loading . . . ");
        interactor.getFbId(fbToken, new BaseCallBack<LoginResponseModel>() {
            @Override
            public void onSuccess(LoginResponseModel responseData) {
                if (view != null) {
                    view.hideProgressDialog();
                    view.showSignUpMessageDialog(context.getString(R.string.already_registered));
                }


            }

            @Override
            public void onError(String message) {
                if (view != null) {
                    view.hideProgressDialog();
                    view.showMessageDialog(message);
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
    public void onDestroy() {
        //ChoiceApp.closeRealm();
        view = null;
    }
}
