package com.dcbazar.np.homeModule.homeactivities.presenter;

import com.dcbazar.np.base.BaseCallBack;
import com.dcbazar.np.base.BaseResponse;
import com.dcbazar.np.helpers.SharedPreferenceManager;
import com.dcbazar.np.homeModule.homeactivities.interactor.ISettingInteractor;
import com.dcbazar.np.homeModule.homeactivities.interactor.SettingInteractor;
import com.dcbazar.np.homeModule.homeactivities.view.ISettingView;

/**
 * Created by Amir on 16/05/2017.
 */

public class SettingPresenter implements ISettingPresenter {

    private SharedPreferenceManager sharedPreferenceManager;
    private ISettingInteractor iSettingInteractor;
    private ISettingView settingView;

    public SettingPresenter(SharedPreferenceManager sharedPreferenceManager, ISettingView settingView){
        this.sharedPreferenceManager = sharedPreferenceManager;
        iSettingInteractor = new SettingInteractor(sharedPreferenceManager);
        this.settingView = settingView;
    }


    @Override
    public void onLogout() {
        settingView.showProgressDialog("Logging out...");
        iSettingInteractor.logOutUser(new BaseCallBack<BaseResponse>() {
            @Override
            public void onSuccess(BaseResponse responseData) {
                if(settingView!=null){
                    settingView.hideProgressDialog();
                    settingView.navigateToLogin();
                }

            }

            @Override
            public void onError(String message) {
                if(settingView!=null){
                    settingView.hideProgressDialog();
                    settingView.showMessageDialog(message);
                }
            }

            @Override
            public void onFailure(String message) {
                if(settingView!=null){
                    settingView.hideProgressDialog();
                    settingView.showMessageDialog(message);
                }

            }

            @Override
            public void onAuthenticationFailure(String message) {
                if(settingView!=null){
                    settingView.hideProgressDialog();
                    settingView.showAuthenticationFailureDialog(message);
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        settingView=null;
    }
}
