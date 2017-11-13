package com.dcbazar.np.homeModule.homeFragment.presenter;

import com.ebpearls.choiceapp.base.BaseCallBack;
import com.ebpearls.choiceapp.base.BaseDatabaseListCallback;
import com.ebpearls.choiceapp.helpers.SharedPreferenceManager;
import com.ebpearls.choiceapp.homeModule.homeFragment.interactor.HomeInteractor;
import com.ebpearls.choiceapp.homeModule.homeFragment.interactor.IHomeInteractor;
import com.ebpearls.choiceapp.homeModule.homeFragment.syncmodels.SurveyResponseModel;
import com.ebpearls.choiceapp.homeModule.homeFragment.view.IHomeView;
import com.ebpearls.choiceapp.loginModule.login.model.DeviceUpdateRequest;

import java.util.List;

/**
 * Created by Amir on 20/04/2017.
 */

public class HomePresenter implements IHomePresenter {

    private final SharedPreferenceManager preference;
    private IHomeView homeView;
    private IHomeInteractor iHomeInteractor;


    public HomePresenter(IHomeView homeView, SharedPreferenceManager sharedPreferenceManager) {
        this.homeView = homeView;
        iHomeInteractor = new HomeInteractor(sharedPreferenceManager);
        this.preference = sharedPreferenceManager;
    }

    @Override
    public void loadSyncData() {
        homeView.showProgressDialog("Loading surveys...");
        iHomeInteractor.getData(new BaseDatabaseListCallback<List<SurveyResponseModel>>() {
            @Override
            public void onListEmpty(String message) {
                if (homeView != null) {
                    homeView.hideProgressDialog();
                    homeView.showMessageDialog(message);
                }
            }

            @Override
            public void onSuccess(List<SurveyResponseModel> responseData) {
                if (homeView != null) {
                    homeView.hideProgressDialog();
                    if (responseData.size() > 0) {
                        homeView.setupRecyclerView(responseData);
                    } else {
                        homeView.showMessageDialog("No Survey Lists Available.");
                    }
                }
            }

            @Override
            public void onError(String message) {
                if (homeView != null) {
                    homeView.hideProgressDialog();
                }
            }

            @Override
            public void onFailure(String message) {
                if (homeView != null) {
                    homeView.hideProgressDialog();
                    homeView.showMessageDialog(message);
                }
            }

            @Override
            public void onAuthenticationFailure(String message) {
                if (homeView != null) {
                    homeView.hideProgressDialog();
                    homeView.showMessageDialog(message);
                }
            }
        });
    }

    @Override
    public void addDeviceInfo(DeviceUpdateRequest deviceUpdateRequest) {
        iHomeInteractor.sendDeviceInfo(deviceUpdateRequest, new BaseCallBack<String>() {
            @Override
            public void onSuccess(String responseData) {
                //  homeView.setupAfterSendingToken();
            }

            @Override
            public void onError(String message) {
                homeView.showToastMessage("Unable to send Token");

            }

            @Override
            public void onFailure(String message) {
                homeView.showToastMessage("Unable to send Token");
            }

            @Override
            public void onAuthenticationFailure(String message) {
                homeView.showToastMessage("Unable to send Token");
            }
        });
    }

    @Override
    public void getSurveyData(int surveyId, int userid) {
        iHomeInteractor.getSurveyDataFromDb(surveyId,userid, new BaseDatabaseListCallback<SurveyResponseModel>() {
            @Override
            public void onListEmpty(String message) {

            }

            @Override
            public void onSuccess(SurveyResponseModel responseData) {
                if(homeView!=null){
                    if(responseData!=null){
                        homeView.showSurveyData(responseData);
                    }
                }
            }

            @Override
            public void onError(String message) {

            }

            @Override
            public void onFailure(String message) {

            }

            @Override
            public void onAuthenticationFailure(String message) {

            }
        });

    }

    @Override
    public void onDestroy() {
        homeView = null;
    }
}
