
package com.dcbazar.np.retrofitApi;

import com.dcbazar.np.base.BaseResponse;
import com.dcbazar.np.helpers.UrlHelper;
import com.dcbazar.np.homeModule.homeFragment.interactor.SyncRequestModel;
import com.dcbazar.np.homeModule.homeFragment.syncmodels.SyncResponseModel;
import com.dcbazar.np.loginModule.changePassword.ChangePasswordResponse;
import com.dcbazar.np.loginModule.forgotPassword.model.ForgotPasswordResponseModel;
import com.dcbazar.np.loginModule.login.model.DeviceUpdateRequest;
import com.dcbazar.np.loginModule.login.model.DeviceUpdateResponse;
import com.dcbazar.np.loginModule.login.model.FbRequestModel;
import com.dcbazar.np.loginModule.login.model.LoginRequestModel;
import com.dcbazar.np.loginModule.login.model.LoginResponseModel;
import com.dcbazar.np.signUpModule.model.SignUpModel;
import com.dcbazar.np.signUpModule.model.SignUpResponseModel;
import com.dcbazar.np.signUpModule.model.TermsPrivacyRequest;
import com.dcbazar.np.signUpModule.model.TermsPrivacyResponse;
import com.dcbazar.np.updateProfileModule.model.UpdateModel;
import com.dcbazar.np.updateProfileModule.model.UpdateResponseModel;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by Sanjay on 8/22/2016.
 */
public interface WebserviceApi {

    @POST(UrlHelper.LOGIN)
    Call<LoginResponseModel> loginUser(@Body LoginRequestModel body);

    @GET(UrlHelper.LOGOUT)
    Call<BaseResponse> logOutUser(@Header("Authorization") String authenticationKey);

    @GET(UrlHelper.GET_AUTHENTICATED_USER)
    Call<UpdateResponseModel> getAuthenticatedUser(@Header("Authorization") String authenticationKey);

    @POST(UrlHelper.FACEBOOK_LOGIN)
    Call<LoginResponseModel> fbLoginUser(@Body FbRequestModel requestBody);

    @POST(UrlHelper.REGISTER)
    Call<SignUpResponseModel> registerUser(@Body SignUpModel signUpModel);

    @POST(UrlHelper.FORGOT_PASSWORD)
    Call<ForgotPasswordResponseModel> forgetPassword(@Body RequestBody body);

    @POST(UrlHelper.UPDATE_PROFILE)
    Call<UpdateResponseModel> updateProfile(@Header("Authorization") String authenticationKey, @Body UpdateModel updateModel);

    @POST(UrlHelper.CHANGE_PASSWORD)
    Call<ChangePasswordResponse> changePassword(@Header("Authorization") String authenticationKey, @Body RequestBody changePasswordResponse);

    @POST(UrlHelper.SYNC_SERVER)
    Call<SyncResponseModel> syncServer(@Header("Authorization") String authenticationKey, @Body SyncRequestModel syncRequestModel);


    @POST(UrlHelper.GET_TERMS_PRIVACY)
    Call<TermsPrivacyResponse> getTermsAndPrivacy(@Body TermsPrivacyRequest termsPrivacyRequest);



    @POST(UrlHelper.POST_ADDUPDATEDEVICE_INFO)
    Call<DeviceUpdateResponse> addUpdateDeviceInfo(@Header("Authorization") String authenticationKey, @Body DeviceUpdateRequest deviceUpdateRequest);


}
