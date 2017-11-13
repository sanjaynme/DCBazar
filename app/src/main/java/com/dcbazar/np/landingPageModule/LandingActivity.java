package com.dcbazar.np.landingPageModule;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.dcbazar.np.R;
import com.dcbazar.np.base.BaseActivity;
import com.dcbazar.np.helpers.SharedPreferenceManager;
import com.dcbazar.np.loginModule.LoginActivity;
import com.dcbazar.np.signUpModule.activities.SignUpActivity;

import butterknife.OnClick;

/**
 * Created by Sanjay on 3/29/2017.
 */

public class LandingActivity extends BaseActivity {
    private SharedPreferenceManager preference;

    public static void start(Context context) {
        Intent intent = new Intent(context, LandingActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_landing;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preference = new SharedPreferenceManager(this);

    }

    @OnClick(R.id.btn_login)
    void loginClicked() {
        LoginActivity.start(LandingActivity.this);
        transitionActivityOpen();
    }

    @OnClick(R.id.btn_create_account)
    void createAccountClicked() {
        SignUpActivity.start(LandingActivity.this);
        transitionActivityOpen();
    }
}