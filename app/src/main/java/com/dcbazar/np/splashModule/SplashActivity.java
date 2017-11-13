package com.dcbazar.np.splashModule;

import android.os.Bundle;

import com.dcbazar.np.base.BaseActivity;
import com.dcbazar.np.helpers.Contracts;
import com.dcbazar.np.homeModule.homeactivities.HomeActivity;
import com.dcbazar.np.landingPageModule.LandingActivity;
import com.dcbazar.np.updateProfileModule.UpdateActivity;


public class SplashActivity extends BaseActivity {
    @Override
    protected int getLayout() {
        return 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setHasLayout(false);
        super.onCreate(savedInstanceState);
        if (preferenceManager.getBoolValues(Contracts.SharedPrefKeys.IS_LOGGED_IN)) {
            if (preferenceManager.getIntValues(Contracts.SharedPrefKeys.IS_PROFILE_COMPLETE)
                    == Contracts.ProfileLoginStatus.PROFILECOMPLETE) {
                HomeActivity.start(SplashActivity.this);
                finish();
                transitionFadeOut();
            } else {
                UpdateActivity.start(SplashActivity.this);
                finish();
                transitionFadeOut();
            }
        } else {
            LandingActivity.start(SplashActivity.this);
            finish();
            transitionFadeOut();
        }
    }

}
