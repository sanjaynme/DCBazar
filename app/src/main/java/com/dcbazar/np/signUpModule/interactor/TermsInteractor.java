package com.dcbazar.np.signUpModule.interactor;

import com.dcbazar.np.base.BaseCallBack;
import com.dcbazar.np.base.BaseInteractor;
import com.dcbazar.np.base.RetrofitCallBack;
import com.dcbazar.np.signUpModule.model.TermsPrivacyRequest;
import com.dcbazar.np.signUpModule.model.TermsPrivacyResponse;

import retrofit2.Call;

/**
 * Created by Amir on 05/04/2017.
 */

public class TermsInteractor extends BaseInteractor implements ITermsPrivacyInteractor{

    @Override
    public void getTermsPrivacy(int pageId, BaseCallBack<TermsPrivacyResponse> callBack) {
        TermsPrivacyRequest termsPrivacyRequest = new TermsPrivacyRequest();
        termsPrivacyRequest.setPageId(pageId+"");
        Call<TermsPrivacyResponse> call = apiService.getTermsAndPrivacy(termsPrivacyRequest);
        call.enqueue(new RetrofitCallBack<TermsPrivacyResponse>(callBack) {
            @Override
            public TermsPrivacyResponse modifyData(TermsPrivacyResponse response) {
                return response;
            }
        });
    }
}
