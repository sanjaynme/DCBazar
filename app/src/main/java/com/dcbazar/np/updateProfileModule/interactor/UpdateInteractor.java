package com.dcbazar.np.updateProfileModule.interactor;

import android.util.Log;

import com.dcbazar.np.base.BaseCallBack;
import com.dcbazar.np.base.BaseInteractor;
import com.dcbazar.np.base.RetrofitCallBack;
import com.dcbazar.np.helpers.Contracts;
import com.dcbazar.np.helpers.SharedPreferenceManager;
import com.dcbazar.np.updateProfileModule.model.UpdateIncomeRangeResponse;
import com.dcbazar.np.updateProfileModule.model.UpdateModel;
import com.dcbazar.np.updateProfileModule.model.UpdateOccupationListResponse;
import com.dcbazar.np.updateProfileModule.model.UpdateResponseModel;

import retrofit2.Call;

/**
 * Created by Amir on 05/04/2017.
 */

public class UpdateInteractor extends BaseInteractor implements IUpdateInteractor {


    private SharedPreferenceManager sharedPreferenceManager;

    public UpdateInteractor(SharedPreferenceManager sharedPreferenceManager) {
        this.sharedPreferenceManager = sharedPreferenceManager;
    }

    @Override
    public void updateProfile(UpdateModel updateModel, final BaseCallBack<UpdateResponseModel> baseCallBack) {
        Log.d("UpdateAuthentication", sharedPreferenceManager.getStringValues(Contracts.SharedPrefKeys.AUTHENTICATION_KEY));
        Call<UpdateResponseModel> call = apiService.updateProfile(sharedPreferenceManager.getStringValues(Contracts.SharedPrefKeys.AUTHENTICATION_KEY), updateModel);
        call.enqueue(new RetrofitCallBack<UpdateResponseModel>(baseCallBack) {
            @Override
            public UpdateResponseModel modifyData(UpdateResponseModel response) {
                storeUserDetails(response);
                return response;
            }
        });
    }

    @Override
    public void getAuthenticatedUser(final BaseCallBack<UpdateResponseModel> baseUpdateCallback) {

        Call<UpdateResponseModel> updateResponseModelCall = apiService.getAuthenticatedUser(sharedPreferenceManager.getStringValues(Contracts.SharedPrefKeys.AUTHENTICATION_KEY));
        updateResponseModelCall.enqueue(new RetrofitCallBack<UpdateResponseModel>(baseUpdateCallback) {
            @Override
            public UpdateResponseModel modifyData(UpdateResponseModel response) {
                storeUserDetails(response);
                return response;
            }
        });
    }

    private void storeUserDetails(UpdateResponseModel responseModel) {
        sharedPreferenceManager.setKeyValues(Contracts.SharedPrefKeys.IS_PROFILE_COMPLETE, responseModel.getResults().getUser().getIsProfileComplete());
        sharedPreferenceManager.setKeyValues(Contracts.SharedPrefKeys.PROFILE_FIRST_NAME, responseModel.getResults().getUser().getProfile().getFirstname());
        sharedPreferenceManager.setKeyValues(Contracts.SharedPrefKeys.PROFILE_LAST_NAME, responseModel.getResults().getUser().getProfile().getLastname());
        sharedPreferenceManager.setKeyValues(Contracts.SharedPrefKeys.PROFILE_EMAIL, responseModel.getResults().getUser().getEmail());
        if(sharedPreferenceManager.getIntValues(Contracts.SharedPrefKeys.IS_PROFILE_COMPLETE)==1){
            sharedPreferenceManager.setKeyValues(Contracts.SharedPrefKeys.PROFILE_AGE, responseModel.getResults().getUser().getProfile().getAge());
            sharedPreferenceManager.setKeyValues(Contracts.SharedPrefKeys.PROFILE_GENDER, responseModel.getResults().getUser().getProfile().getGender());
            if(responseModel.getResults().getUser().getProfile().getIncomeRange()!=null){
                sharedPreferenceManager.setKeyValues(Contracts.SharedPrefKeys.PROFILE_INCOME, responseModel.getResults().getUser().getProfile().getIncomeRange().min+"-"+responseModel.getResults().getUser().getProfile().getIncomeRange().max);
                sharedPreferenceManager.setKeyValues(Contracts.SharedPrefKeys.PROFILE_INCOME_ID, responseModel.getResults().getUser().getProfile().getIncomeRange().id);
            }
            sharedPreferenceManager.setKeyValues(Contracts.SharedPrefKeys.PROFILE_OCCUPATION_ID,responseModel.getResults().getUser().getProfile().getOccupations().id);
            sharedPreferenceManager.setKeyValues(Contracts.SharedPrefKeys.PROFILE_OCCUPATION, responseModel.getResults().getUser().getProfile().getOccupations().occupation);
            sharedPreferenceManager.setKeyValues(Contracts.SharedPrefKeys.PROFILE_POSTCODE, responseModel.getResults().getUser().getProfile().getPostcode());
        }

    }

}

