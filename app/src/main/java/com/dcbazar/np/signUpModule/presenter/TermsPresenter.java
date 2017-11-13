package com.dcbazar.np.signUpModule.presenter;

import com.dcbazar.np.base.BaseCallBack;
import com.dcbazar.np.signUpModule.interactor.TermsInteractor;
import com.dcbazar.np.signUpModule.model.TermsPrivacyResponse;
import com.dcbazar.np.signUpModule.view.ITermsPrivacyView;

/**
 * Created by Amir on 05/04/2017.
 */

public class TermsPresenter {
    int pageId;
    TermsInteractor termsInteractor;
    ITermsPrivacyView termsPrivacyView;

    public TermsPresenter(ITermsPrivacyView termsPrivacyView) {
        termsInteractor = new TermsInteractor();
        this.termsPrivacyView = termsPrivacyView;
    }

    public void getTermsandPrivacy(int pageId) {
        termsPrivacyView.showProgressDialog("Loading page... ");
        termsInteractor.getTermsPrivacy(pageId, new BaseCallBack<TermsPrivacyResponse>() {
            @Override
            public void onSuccess(TermsPrivacyResponse responseData) {
                termsPrivacyView.hideProgressDialog();
                termsPrivacyView.showPrivacyTerms(responseData.getResults().getPage().getContent());
            }

            @Override
            public void onError(String message) {
                termsPrivacyView.hideProgressDialog();

            }

            @Override
            public void onFailure(String message) {
                termsPrivacyView.hideProgressDialog();

            }

            @Override
            public void onAuthenticationFailure(String message) {
                termsPrivacyView.hideProgressDialog();

            }
        });

    }
}
