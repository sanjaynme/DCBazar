package com.dcbazar.np.updateProfileModule.presenter;


import com.dcbazar.np.base.BasePresenter;
import com.dcbazar.np.updateProfileModule.model.UpdateModel;

/**
 * Created by Amir on 05/04/2017.
 */

public interface IUpdatePresenter extends BasePresenter {
    boolean validateFields(UpdateModel updateModel);

    void sendUpdateDetails(UpdateModel updateModel);

    void getUserDetails();

}
