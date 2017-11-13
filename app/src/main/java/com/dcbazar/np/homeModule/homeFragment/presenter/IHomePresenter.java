package com.dcbazar.np.homeModule.homeFragment.presenter;

import com.ebpearls.choiceapp.base.BasePresenter;
import com.ebpearls.choiceapp.loginModule.login.model.DeviceUpdateRequest;

/**
 * Created by Amir on 20/04/2017.
 */

public interface IHomePresenter extends BasePresenter {

    void loadSyncData();

    void addDeviceInfo(DeviceUpdateRequest deviceUpdateRequest);

    void getSurveyData(int surveyId, int userid);
}
