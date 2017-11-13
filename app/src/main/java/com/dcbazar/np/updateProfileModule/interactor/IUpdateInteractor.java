package com.dcbazar.np.updateProfileModule.interactor;


import com.dcbazar.np.base.BaseCallBack;
import com.dcbazar.np.updateProfileModule.model.UpdateModel;
import com.dcbazar.np.updateProfileModule.model.UpdateResponseModel;

/**
 * Created by Amir on 05/04/2017.
 */

public interface IUpdateInteractor {

    void updateProfile(UpdateModel updateModel, BaseCallBack<UpdateResponseModel> baseCallBack);

    void getAuthenticatedUser(BaseCallBack<UpdateResponseModel> updateBaseCallback);

}
