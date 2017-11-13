package com.dcbazar.np.homeModule.homeFragment.presenter;

import com.ebpearls.choiceapp.base.BaseDatabaseCallback;
import com.ebpearls.choiceapp.helpers.SharedPreferenceManager;
import com.ebpearls.choiceapp.homeModule.homeFragment.interactor.DialogInteractor;
import com.ebpearls.choiceapp.homeModule.homeFragment.interactor.IDialogInteractor;
import com.ebpearls.choiceapp.homeModule.homeFragment.syncmodels.SurveyResponseModel;
import com.ebpearls.choiceapp.homeModule.homeFragment.view.IDialogFragmentView;

/**
 * Created by Amir on 14/07/2017.
 */

public class DialogPresenter implements IDialogPresenter{

    private IDialogInteractor dialogInteractor;
    SharedPreferenceManager sharedPreferenceManager;
    IDialogFragmentView iDialogFragmentView;


    public DialogPresenter(SharedPreferenceManager sharedPreferenceManager, IDialogFragmentView iDialogFragmentView){
        this.sharedPreferenceManager = sharedPreferenceManager;
        dialogInteractor = new DialogInteractor(sharedPreferenceManager);
        this.iDialogFragmentView = iDialogFragmentView;
    }
    @Override
    public void insertSyncDataIntoHistory(SurveyResponseModel surveyResponseModel) {
        dialogInteractor.insertSyncDataIntoHistory(surveyResponseModel, new BaseDatabaseCallback<SurveyResponseModel>() {
            @Override
            public void onSuccess(SurveyResponseModel surveyResponseModel) {
                if ( iDialogFragmentView!= null) {
                    iDialogFragmentView.navigateToDynamic(surveyResponseModel);
                    //homeView.showPopUp(surveyResponseModel);
                }
            }

            @Override
            public void onFailure(String message) {

            }
        });
    }

}
