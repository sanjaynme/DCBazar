package com.dcbazar.np.base;


import com.dcbazar.np.retrofitApi.RetrofitSingleton;
import com.dcbazar.np.retrofitApi.WebserviceApi;

/**
 * Created by Sanjay on 04/04/2017.
 */

public abstract class BaseInteractor {

    protected WebserviceApi apiService;
    public BaseInteractor() {
        apiService = RetrofitSingleton.getApiService();
    }

}
