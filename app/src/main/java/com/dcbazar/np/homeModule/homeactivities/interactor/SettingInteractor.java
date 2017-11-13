package com.dcbazar.np.homeModule.homeactivities.interactor;

import com.dcbazar.np.base.BaseCallBack;
import com.dcbazar.np.base.BaseInteractor;
import com.dcbazar.np.base.BaseResponse;
import com.dcbazar.np.base.RetrofitCallBack;
import com.dcbazar.np.helpers.Contracts;
import com.dcbazar.np.helpers.SharedPreferenceManager;

import retrofit2.Call;

/**
 * Created by Amir on 16/05/2017.
 */

public class SettingInteractor extends BaseInteractor implements ISettingInteractor {
    private SharedPreferenceManager sharedPreferenceManager;

    public SettingInteractor(SharedPreferenceManager sharedPreferenceManager) {
        this.sharedPreferenceManager = sharedPreferenceManager;
    }

    @Override
    public void logOutUser(final BaseCallBack<BaseResponse> baseCallBack) {
        Call<BaseResponse> callLogOut = apiService.logOutUser(sharedPreferenceManager.getStringValues(Contracts.SharedPrefKeys.AUTHENTICATION_KEY));

        callLogOut.enqueue(new RetrofitCallBack<BaseResponse>(baseCallBack) {
            @Override
            public BaseResponse modifyData(BaseResponse response) {
//                LoginManager.getInstance().logOut();
                sharedPreferenceManager.setKeyValues(Contracts.SharedPrefKeys.IS_LOGGED_IN, false);
                sharedPreferenceManager.setKeyValues(Contracts.SharedPrefKeys.AUTHENTICATION_KEY, "");
                sharedPreferenceManager.setKeyValues(Contracts.SharedPrefKeys.NOTIFICATION_TOKEN,"");
                //ServiceHelper.stopService(getApplicationContext());
                return response;
            }
        });

    }

}
