package com.dcbazar.np.homeModule.homeactivities.interactor;


import com.dcbazar.np.base.BaseCallBack;
import com.dcbazar.np.base.BaseResponse;

/**
 * Created by Amir on 16/05/2017.
 */

public interface ISettingInteractor {
    void logOutUser(BaseCallBack<BaseResponse> baseCallBack);
}
