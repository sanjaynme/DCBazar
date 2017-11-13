package com.dcbazar.np.signUpModule.presenter;


import com.dcbazar.np.base.BasePresenter;
import com.dcbazar.np.signUpModule.model.SignUpModel;

/**
 * Created by Amir on 31/03/2017.
 */

public interface ISignUpPresenter extends BasePresenter {

    void populatespinner();

    boolean validateFields(SignUpModel signUpModel);

    void sendRegisterDetails(SignUpModel signUpModel);

    void getFacebookId(String fbToken);


}
