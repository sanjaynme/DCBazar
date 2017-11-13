package com.dcbazar.np.loginModule.login.presenter;

import com.ebpearls.choiceapp.base.BaseCallBack;
import com.ebpearls.choiceapp.helpers.Contracts;
import com.ebpearls.choiceapp.helpers.SharedPreferenceManager;
import com.ebpearls.choiceapp.loginModule.login.interactor.ILoginInteractor;
import com.ebpearls.choiceapp.loginModule.login.interactor.LoginInteractor;
import com.ebpearls.choiceapp.loginModule.login.model.LoginResponseModel;
import com.ebpearls.choiceapp.loginModule.login.view.ILoginView;

import retrofit2.Response;

/**
 * Created by Sanjay on 3/29/2017.
 */

public class LoginPresenter implements ILoginPresenter {
    private ValidationAndLogin user;
    private ILoginView iLoginView;
    private SharedPreferenceManager preference;
    private ILoginInteractor iLoginInteractor;


    public LoginPresenter(ILoginView iLoginView, SharedPreferenceManager preference) {
        this.iLoginView = iLoginView;
        this.preference = preference;
        user = new ValidationAndLogin(iLoginView);
        iLoginInteractor = new LoginInteractor(this.preference);
    }

    public void errorFacebookLogin(String errorMessage) {
        System.out.println("Failure Fb error Messgae" + errorMessage);
    }

    @Override
    public void forgetPasswordButtonClicked() {
        iLoginView.openForgetPasswordActivity();
    }

    @Override
    public void onFacebookResponse(String fbToken) {
        iLoginView.showProgressDialog("Loading...");
        iLoginInteractor.loginWithFacebook(fbToken, new BaseCallBack<LoginResponseModel>() {
            @Override
            public void onSuccess(LoginResponseModel responseData) {
                if (responseData.getStatusCode().equalsIgnoreCase(Contracts.HttpStatusCodes.FB_USER_NOT_FOUND)) {
                    iLoginView.hideProgressDialog();
                    String firstName = responseData.results.fbDetail.firstName;
                    String lastName = responseData.results.fbDetail.lastName;
                    String email = responseData.results.fbDetail.email;
                    String fbId = responseData.results.fbDetail.fbId;
                    iLoginView.navigateToRegister(firstName, lastName, email, fbId);

                } else if (responseData.getStatusCode().equalsIgnoreCase(Contracts.HttpStatusCodes.STATUS_OK)) {
                    iLoginView.hideProgressDialog();
                    if (responseData.results.user.isProfileComplete == Contracts.ProfileLoginStatus.PROFILECOMPLETE) {
                        iLoginView.navigateToHome();
                    } else {
                        iLoginView.navigateToUpdateProfile();
                    }

                } else {
                    iLoginView.hideProgressDialog();
                }
            }

            @Override
            public void onAuthenticationFailure(String message) {
                if (iLoginView != null) {
                    iLoginView.hideProgressDialog();
                    iLoginView.showMessageDialog(message);
                }


            }

            @Override
            public void onError(String message) {
                if (iLoginView != null) {
                    iLoginView.hideProgressDialog();
                    iLoginView.showMessageDialog(message);

                }

            }

            @Override
            public void onFailure(String message) {
                if (iLoginView != null) {
                    iLoginView.hideProgressDialog();
                    iLoginView.showMessageDialog(message);
                }

            }
        });
    }


    @Override
    public void onLoginResponse(String login_type, Response<LoginResponseModel> response, String name, String email, String token) {
        preference.setKeyValues(Contracts.SharedPrefKeys.IS_LOGGED_IN, true);

     /*   preferenceManager.setKeyValues(Contracts.SharedPrefKeys.USER_ID, response.body().userInfo.userId);
        preferenceManager.setKeyValues(Contracts.SharedPrefKeys.EMAIL, response.body().userInfo.email);
        preferenceManager.setKeyValues(Contracts.SharedPrefKeys.PHONE, response.body().userInfo.phone);
        preferenceManager.setKeyValues(Contracts.SharedPrefKeys.HASH_CODE, response.body().userInfo.hashCode);
        preferenceManager.setKeyValues(Contracts.SharedPrefKeys.IMAGE_URL, response.body().userInfo.image);
        preferenceManager.setKeyValues(Contracts.SharedPrefKeys.NAME, response.body().userInfo.name);
*/
    }


    @Override
    public void doNormalLogin(String email, String password) {
        user.checkUserValidity(email, password, this);
    }

    @Override
    public void onValidationSuccess(String email, String password) {
        iLoginView.showProgressDialog("Logging in ...");
        iLoginInteractor.loginUser(email, password, new BaseCallBack<LoginResponseModel>() {
            @Override
            public void onSuccess(LoginResponseModel responseModel) {
                if (iLoginView != null) {
                    iLoginView.hideProgressDialog();
                    if (responseModel.results.user.isProfileComplete == Contracts.ProfileLoginStatus.PROFILECOMPLETE) {
                        iLoginView.navigateToHome();
                    } else {
                        iLoginView.navigateToUpdateProfile();
                    }
                }
            }

            @Override
            public void onAuthenticationFailure(String message) {
                if (iLoginView != null) {
                    iLoginView.hideProgressDialog();
                }
            }

            @Override
            public void onError(String message) {
                if (iLoginView != null) {
                    iLoginView.hideProgressDialog();
                    iLoginView.showMessageDialog(message);
                }
            }

            @Override
            public void onFailure(String message) {
                if (iLoginView != null) {
                    iLoginView.hideProgressDialog();
                }
            }
        });
    }


    @Override
    public void onValidationError(String error) {
        iLoginView.showError(error);
    }

    @Override
    public void onDestroy() {
        iLoginView = null;
    }


    /*private void tokenRequest() {
        FirebaseMessaging.getInstance().subscribeToTopic("news");
        notificationToken = FirebaseInstanceId.getInstance().getToken();
        Log.d("notificationtoken", "" + notificationToken);
        if (notificationToken != null && !notificationToken.isEmpty()) {
            //registerFirebaseTokenToServer(notificationToken);
        } else {
            tokenRequest();
        }
    }*/
}
