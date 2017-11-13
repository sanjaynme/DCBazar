package com.dcbazar.np.updateProfileModule.view;

import com.dcbazar.np.base.BaseView;
import com.dcbazar.np.updateProfileModule.model.UpdateIncomeRangeResponse;
import com.dcbazar.np.updateProfileModule.model.UpdateOccupationListResponse;

/**
 * Created by Amir on 05/04/2017.
 */

public interface IUpdateView extends BaseView {
    void navigateToHome();
    void afterGettingUsers();

    void populateIncomeRange(UpdateIncomeRangeResponse responseData);

    void populateOccupationList(UpdateOccupationListResponse responseData);
}
