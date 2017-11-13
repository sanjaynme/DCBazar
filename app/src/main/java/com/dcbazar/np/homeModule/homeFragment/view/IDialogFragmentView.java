package com.dcbazar.np.homeModule.homeFragment.view;

import com.ebpearls.choiceapp.homeModule.homeFragment.syncmodels.SurveyResponseModel;

/**
 * Created by Amir on 14/07/2017.
 */

public interface IDialogFragmentView {
    void navigateToDynamic(SurveyResponseModel surveyResponseModel);

    void showMessageDialog(String message);

}
