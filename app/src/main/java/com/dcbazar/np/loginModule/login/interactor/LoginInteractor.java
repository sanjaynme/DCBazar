package com.dcbazar.np.loginModule.login.interactor;


import android.util.Log;

import com.ebpearls.choiceapp.base.BaseCallBack;
import com.ebpearls.choiceapp.helpers.AppLog;
import com.ebpearls.choiceapp.helpers.Contracts;
import com.ebpearls.choiceapp.helpers.SharedPreferenceManager;
import com.ebpearls.choiceapp.helpers.UrlHelper;
import com.ebpearls.choiceapp.loginModule.login.model.DeviceUpdateRequest;
import com.ebpearls.choiceapp.loginModule.login.model.DeviceUpdateResponse;
import com.ebpearls.choiceapp.loginModule.login.model.FbRequestModel;
import com.ebpearls.choiceapp.loginModule.login.model.LoginRequestModel;
import com.ebpearls.choiceapp.loginModule.login.model.LoginResponseModel;
import com.ebpearls.choiceapp.retrofitApi.RetrofitSingleton;
import com.ebpearls.choiceapp.retrofitApi.WebserviceApi;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Sanjay on 3/30/2017.
 */

public class LoginInteractor implements ILoginInteractor {

    private SharedPreferenceManager preference;
    private WebserviceApi apiService;
    SharedPreferenceManager sharedPreferenceManager;

    public LoginInteractor(SharedPreferenceManager preference) {
        this.preference = preference;
        this.apiService = RetrofitSingleton.getApiService();
        this.sharedPreferenceManager = preference;
    }

    @Override
    public void loginUser(String email, String password, final BaseCallBack<LoginResponseModel> loginResponseCallBack) {
        LoginRequestModel model = new LoginRequestModel();
        model.email = email;
        model.password = password;

        final Call<LoginResponseModel> login = apiService.loginUser(model);
        login.enqueue(new Callback<LoginResponseModel>() {
            @Override
            public void onResponse(Call<LoginResponseModel> call, Response<LoginResponseModel> response) {
                if (response.isSuccessful()) {
                    switch (response.body().getStatusCode()) {
                        case UrlHelper.ResponseCodes.SUCCESS:
                            storeUserDetails(response.body());
                            sendDeviceInfo(loginResponseCallBack,response.body());
                            break;

                        case UrlHelper.ResponseCodes.INVALID_EMAIL_PASSWORD:
                            loginResponseCallBack.onError(response.body().getMessage());
                            break;

                        case UrlHelper.ResponseCodes.CANNOT_CREATE_TOKEN:
                            loginResponseCallBack.onError(response.body().getMessage());
                            break;

                        case UrlHelper.ResponseCodes.INVALID_CREDENTIALS:
                            loginResponseCallBack.onError(response.body().getMessage());
                            break;

                        case UrlHelper.ResponseCodes.INVALID_USERS:
                            loginResponseCallBack.onError(response.body().getMessage());
                            break;

                        case UrlHelper.ResponseCodes.OTHER_FAILURES:
                            loginResponseCallBack.onError(response.body().getMessage());
                            break;

                        case UrlHelper.ResponseCodes.FAILURE:
                            loginResponseCallBack.onSuccess(response.body());
                            break;
                    }
                }

            }

            @Override
            public void onFailure(Call<LoginResponseModel> call, Throwable t) {
                if (t != null && loginResponseCallBack != null) {
                    if (t instanceof UnknownHostException) {
                        loginResponseCallBack.onError(Contracts.Errors.NO_INTERNET_AVAILABLE);
                    } else if (t instanceof SocketTimeoutException) {
                        loginResponseCallBack.onError(Contracts.Errors.INTERNET_REQUEST_TIMEOUT);
                    } else {
                        loginResponseCallBack.onError(Contracts.Errors.ERROR);
                    }
                }


            }
        });

    }

    @Override
    public void loginWithFacebook(String token, final BaseCallBack<LoginResponseModel> loginResponseCallBack) {
        preference.setKeyValues(Contracts.SharedPrefKeys.FACEBOOK_TOKEN, token);
        FbRequestModel fbRequestModel = new FbRequestModel();
        fbRequestModel.fbToken = token;
        Call<LoginResponseModel> fbResponseModelCall = apiService.fbLoginUser(fbRequestModel);
        fbResponseModelCall.enqueue(new Callback<LoginResponseModel>() {
            @Override
            public void onResponse(Call<LoginResponseModel> call, Response<LoginResponseModel> response) {
                if (response.isSuccessful()) {
                    switch (response.body().getStatusCode()) {
                        case UrlHelper.ResponseCodes.SUCCESS:
                            storeUserDetails(response.body());
                            sendDeviceInfo(loginResponseCallBack,response.body());
                            break;

                        case UrlHelper.ResponseCodes.INVALID_EMAIL_PASSWORD:
                            loginResponseCallBack.onError(response.body().getMessage());
                            break;

                        case UrlHelper.ResponseCodes.CANNOT_CREATE_TOKEN:
                            loginResponseCallBack.onError(response.body().getMessage());
                            break;

                        case UrlHelper.ResponseCodes.INVALID_CREDENTIALS:
                            loginResponseCallBack.onError(response.body().getMessage());
                            break;

                        case UrlHelper.ResponseCodes.INVALID_USERS:
                            loginResponseCallBack.onError(response.body().getMessage());
                            break;

                        case UrlHelper.ResponseCodes.OTHER_FAILURES:
                            loginResponseCallBack.onError(response.body().getMessage());
                            break;

                        case UrlHelper.ResponseCodes.FAILURE:
                            loginResponseCallBack.onSuccess(response.body());
                            break;
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginResponseModel> call, Throwable t) {
                if (t != null && loginResponseCallBack != null) {
                    if (t instanceof UnknownHostException) {
                        loginResponseCallBack.onError(Contracts.Errors.NO_INTERNET_AVAILABLE);
                    } else if (t instanceof SocketTimeoutException) {
                        loginResponseCallBack.onError(Contracts.Errors.INTERNET_REQUEST_TIMEOUT);
                    } else {
                        loginResponseCallBack.onError(Contracts.Errors.ERROR);
                    }
                }

            }
        });

    }


    public void storeUserDetails(LoginResponseModel responseModel) {

        preference.setKeyValues(Contracts.SharedPrefKeys.IS_LOGGED_IN, true);
        preference.setKeyValues(Contracts.SharedPrefKeys.AUTHENTICATION_KEY, "Bearer " + responseModel.results.token);
        Log.d("Authenticationkey", preference.getStringValues(Contracts.SharedPrefKeys.AUTHENTICATION_KEY));
        preference.setKeyValues(Contracts.SharedPrefKeys.IS_PROFILE_COMPLETE, responseModel.results.user.isProfileComplete);
        preference.setKeyValues(Contracts.SharedPrefKeys.USER_ID, responseModel.results.user.id);
        Log.d("User Id", String.valueOf(preference.getIntValues(Contracts.SharedPrefKeys.USER_ID)));

        preference.setKeyValues(Contracts.SharedPrefKeys.PROFILE_FIRST_NAME, responseModel.results.user.profile.firstname);
        preference.setKeyValues(Contracts.SharedPrefKeys.PROFILE_LAST_NAME, responseModel.results.user.profile.lastname);
        preference.setKeyValues(Contracts.SharedPrefKeys.PROFILE_EMAIL, responseModel.results.user.email);
        if (responseModel.results.user.profile.age != null) {
            if (responseModel.results.user.profile.age != 0) {
                preference.setKeyValues(Contracts.SharedPrefKeys.PROFILE_AGE, responseModel.results.user.profile.age);
            } else {
                preference.setKeyValues(Contracts.SharedPrefKeys.PROFILE_AGE, 0);
            }
        } else {
            preference.setKeyValues(Contracts.SharedPrefKeys.PROFILE_AGE, 0);
        }
        preference.setKeyValues(Contracts.SharedPrefKeys.PROFILE_GENDER, responseModel.results.user.profile.gender);
        if (responseModel.results.user.profile.incomeRange != null) {
            preference.setKeyValues(Contracts.SharedPrefKeys.PROFILE_INCOME_ID, responseModel.results.user.profile.incomeRange.id);
            preference.setKeyValues(Contracts.SharedPrefKeys.PROFILE_INCOME, responseModel.results.user.profile.incomeRange.min + "-" + responseModel.results.user.profile.incomeRange.max);
        } else {
            preference.setKeyValues(Contracts.SharedPrefKeys.PROFILE_INCOME, "");
            preference.setKeyValues(Contracts.SharedPrefKeys.PROFILE_INCOME_ID, "");
        }
        if (responseModel.results.user.profile.occupation != null) {
            preference.setKeyValues(Contracts.SharedPrefKeys.PROFILE_OCCUPATION_ID, responseModel.results.user.profile.occupation.id);
            preference.setKeyValues(Contracts.SharedPrefKeys.PROFILE_OCCUPATION, responseModel.results.user.profile.occupation.occupation);
        } else {
            preference.setKeyValues(Contracts.SharedPrefKeys.PROFILE_OCCUPATION, "");
            preference.setKeyValues(Contracts.SharedPrefKeys.PROFILE_OCCUPATION_ID, "");
        }
        preference.setKeyValues(Contracts.SharedPrefKeys.PROFILE_POSTCODE, responseModel.results.user.profile.postcode);
        Log.d("Profile UpdatedValue", String.valueOf(preference.getIntValues(Contracts.SharedPrefKeys.IS_PROFILE_COMPLETE)));
    }

     private void sendDeviceInfo(final BaseCallBack<LoginResponseModel> loginResponseCallBack, final LoginResponseModel loginResponseModel) {
         DeviceUpdateRequest deviceUpdateRequest = new DeviceUpdateRequest();
         deviceUpdateRequest.setDeviceId(sharedPreferenceManager.getStringValues(Contracts.SharedPrefKeys.DEVICE_ID));
         deviceUpdateRequest.setDeviceToken(sharedPreferenceManager.getStringValues(Contracts.SharedPrefKeys.NOTIFICATION_TOKEN));
         deviceUpdateRequest.setDeviceType("0");
        Call<DeviceUpdateResponse> updatedevice = apiService.addUpdateDeviceInfo(sharedPreferenceManager.getStringValues(Contracts.SharedPrefKeys.AUTHENTICATION_KEY), deviceUpdateRequest);
        updatedevice.enqueue(new Callback<DeviceUpdateResponse>() {
            @Override
            public void onResponse(Call<DeviceUpdateResponse> call, Response<DeviceUpdateResponse> response) {
                if (response.isSuccessful()) {
                    switch (response.body().getStatusCode()) {
                        case UrlHelper.ResponseCodes.SUCCESS:
                            loginResponseCallBack.onSuccess(loginResponseModel);
                            break;
                        default:
                            loginResponseCallBack.onFailure(loginResponseModel.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<DeviceUpdateResponse> call, Throwable t) {
                loginResponseCallBack.onFailure(t.getMessage());
                AppLog.d("message", t.getMessage());
            }
        });
    }


   /* private void storeUserDetailFromFb(FbResponseModel responseModel) {

        preference.setKeyValues(Contracts.SharedPrefKeys.IS_LOGGED_IN, true);
        preference.setKeyValues(Contracts.SharedPrefKeys.AUTHENTICATION_KEY, "Bearer " + responseModel.results.token);
        Log.d("Authenticationkey", preference.getStringValues(Contracts.SharedPrefKeys.AUTHENTICATION_KEY));
        preference.setKeyValues(Contracts.SharedPrefKeys.IS_PROFILE_COMPLETE, responseModel.results.user.isProfileComplete);
        preference.setKeyValues(Contracts.SharedPrefKeys.USER_ID, responseModel.results.user.id);
        Log.d("User Id", String.valueOf(preference.getIntValues(Contracts.SharedPrefKeys.USER_ID)));

        preference.setKeyValues(Contracts.SharedPrefKeys.PROFILE_FIRST_NAME, responseModel.results.user.profile.firstname);
        preference.setKeyValues(Contracts.SharedPrefKeys.PROFILE_LAST_NAME, responseModel.results.user.profile.lastname);
        preference.setKeyValues(Contracts.SharedPrefKeys.PROFILE_EMAIL, responseModel.results.user.email);
        if (responseModel.results.user.profile.age != null) {
            if (responseModel.results.user.profile.age != 0) {
                preference.setKeyValues(Contracts.SharedPrefKeys.PROFILE_AGE, responseModel.results.user.profile.age);
            } else {
                preference.setKeyValues(Contracts.SharedPrefKeys.PROFILE_AGE, 0);
            }
        } else {
            preference.setKeyValues(Contracts.SharedPrefKeys.PROFILE_AGE, 0);
        }
        preference.setKeyValues(Contracts.SharedPrefKeys.PROFILE_GENDER, responseModel.results.user.profile.gender);
        if (responseModel.results.user.profile.incomerange != null) {
            preference.setKeyValues(Contracts.SharedPrefKeys.PROFILE_INCOME_ID, responseModel.results.user.profile.incomerange.id);
            preference.setKeyValues(Contracts.SharedPrefKeys.PROFILE_INCOME, responseModel.results.user.profile.incomerange.min + "-" + responseModel.results.user.profile.incomerange.max);
        } else {
            preference.setKeyValues(Contracts.SharedPrefKeys.PROFILE_INCOME, "");
            preference.setKeyValues(Contracts.SharedPrefKeys.PROFILE_INCOME_ID, "");
        }
        if (responseModel.results.user.profile.occupation != null) {
            preference.setKeyValues(Contracts.SharedPrefKeys.PROFILE_OCCUPATION_ID, responseModel.results.user.profile.occupation.id);
            preference.setKeyValues(Contracts.SharedPrefKeys.PROFILE_OCCUPATION, responseModel.results.user.profile.occupation.occupation);
        } else {
            preference.setKeyValues(Contracts.SharedPrefKeys.PROFILE_OCCUPATION, "");
            preference.setKeyValues(Contracts.SharedPrefKeys.PROFILE_OCCUPATION_ID, "");
        }
        preference.setKeyValues(Contracts.SharedPrefKeys.PROFILE_POSTCODE, responseModel.results.user.profile.postcode);
        Log.d("Profile UpdatedValue", String.valueOf(preference.getIntValues(Contracts.SharedPrefKeys.IS_PROFILE_COMPLETE)));
    }
*/

}
