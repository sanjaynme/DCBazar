package com.dcbazar.np.homeModule.homeFragment.view;

import com.dcbazar.np.base.BaseView;
import com.dcbazar.np.homeModule.homeFragment.syncmodels.SurveyResponseModel;

import java.util.List;

/**
 * Created by Amir on 20/04/2017.
 */

public interface IHomeView extends BaseView {

    void setupRecyclerView(List<SurveyResponseModel> homeResponseModel);

    /*void showPopUp(SurveyResponseModel surveyResponseModel);*/

//    void getSurveyListing();

   /* void navigateToDynamic(SurveyResponseModel surveyResponseModel);*/

    /*void setupAfterSendingToken();*/

    void showPopUpFragment(SurveyResponseModel surveyResponseModel);

    void showSurveyData(SurveyResponseModel responseData);
}
