package com.dcbazar.np.signUpModule.interactor;


import com.dcbazar.np.base.BaseCallBack;
import com.dcbazar.np.signUpModule.model.TermsPrivacyResponse;

/**
 * Created by Amir on 30/06/2017.
 */

public interface ITermsPrivacyInteractor {
    void getTermsPrivacy(int pageId, BaseCallBack<TermsPrivacyResponse> baseCallBack);
}
