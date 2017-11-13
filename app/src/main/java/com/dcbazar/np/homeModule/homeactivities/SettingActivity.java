package com.dcbazar.np.homeModule.homeactivities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.dcbazar.np.R;
import com.dcbazar.np.base.BaseActivity;
import com.dcbazar.np.helpers.ConnectionMngr;
import com.dcbazar.np.helpers.SharedPreferenceManager;
import com.dcbazar.np.homeModule.homeactivities.presenter.ISettingPresenter;
import com.dcbazar.np.homeModule.homeactivities.presenter.SettingPresenter;
import com.dcbazar.np.homeModule.homeactivities.view.ISettingView;
import com.dcbazar.np.landingPageModule.LandingActivity;
import com.dcbazar.np.loginModule.changePassword.ChangePasswordActivity;
import com.dcbazar.np.signUpModule.activities.PrivacyActivity;
import com.dcbazar.np.signUpModule.activities.TermsActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Amir on 16/05/2017.
 */

public class SettingActivity extends BaseActivity implements ISettingView {

    @BindView(R.id.iv_back_arrow)
    ImageView ivBackArrow;

    @BindView(R.id.tv_toolbar_title)
    TextView tvTitle;

    @BindView(R.id.iv_menu)
    ImageView ivMenu;

    @BindView(R.id.iv_notification)
    ImageView ivNotification;

    ISettingPresenter settingPresenter;

    SharedPreferenceManager sharedPreferenceManager;


    public static void start(Context context) {
        Intent intent = new Intent(context, SettingActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferenceManager = new SharedPreferenceManager(this);
        settingPresenter = new SettingPresenter(sharedPreferenceManager, this);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            setStatusColor();
        }
        setupToolbarAndBackground();
        //helpShiftIntegration();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setStatusColor() {
        Window window = getWindow();
//        Drawable background = getResources().getDrawable(R.drawable.toolbar_gradient);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        window.setStatusBarColor(getResources().getColor(android.R.color.transparent));
//        window.setNavigationBarColor(getResources().getColor(android.R.color.transparent));
//        window.setBackgroundDrawable(background);
    }

    private void setupToolbarAndBackground() {
        tvTitle.setText("Settings");
        ivBackArrow.setVisibility(View.VISIBLE);
        ivNotification.setVisibility(View.GONE);
        ivMenu.setVisibility(View.GONE);
    }


    @Override
    protected int getLayout() {
        return R.layout.activity_settings;
    }

    /*@OnClick(R.id.rr_setting_profile)
    public void onProfileSetting() {
        //startSpecificActivity(UpdateActivity.class, CommonDef.FROMMENU);
        UpdateActivity.start(this, Contracts.FROMMENU);

    }*/

    @OnClick(R.id.rr_setting_contact)
    public void onContact() {
        //Support.showFAQs(this);
    }

    @OnClick(R.id.rr_setting_privacy)
    public void onPrivacy() {
        if(ConnectionMngr.hasConnection(SettingActivity.this)){
            PrivacyActivity.start(this);
            transitionActivityOpen();
        }

    }

    @OnClick(R.id.rr_setting_service)
    public void onService() {
        if(ConnectionMngr.hasConnection(SettingActivity.this)){
            TermsActivity.start(this);
            transitionActivityOpen();
        }
    }

    @OnClick(R.id.rr_setting_change_password)
    public void onChangePassword() {
        //startSpecificActivity(ChangePassword.class);
        ChangePasswordActivity.start(this);
        transitionActivityOpen();
    }

    @OnClick(R.id.rr_setting_logout)
    public void onLogout() {
        showWarningDialog("Do you really want to log out?");
    }


    protected void showWarningDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                settingPresenter.onLogout();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dismissAlertDialog();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
       /* AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.MyFullWidthDialog);
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.dialog_yes_no, null);
        builder.setView(dialogView);

        TextView textViewMessage = (TextView) dialogView.findViewById(R.id.tv_dialog_message);
        textViewMessage.setText(message);

        showDialog(builder);

        TextView textViewYes = (TextView) dialogView.findViewById(R.id.tv_dialog_yes);
        TextView textViewNo = (TextView) dialogView.findViewById(R.id.tv_dialog_no);
        textViewYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                settingPresenter.onLogout();
            }
        });
        textViewNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismissAlertDialog();
            }
        });*/
    }

    private void showDialog(AlertDialog.Builder builder) {
        if (alertDialog != null && alertDialog.isShowing()) {
            alertDialog.dismiss();
        }
        alertDialog = builder.create();
        alertDialog.show();
    }


    @Override
    public void showProgress(String message) {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showToastMessage(String message) {

    }

    @Override
    public void showMessageDialog(String message) {
        showBaseAlertDialog(message);
    }

    @Override
    public void showAuthenticationFailureDialog(String message) {

    }

    @Override
    public void showProgressDialog(String progressMessage) {
        showBaseProgressDialog(progressMessage);
    }

    @Override
    public void hideProgressDialog() {
        dismissBaseProgressDialog();
    }

    @Override
    public void navigateToLogin() {
//        ChoiceApp.deleteRealm();
        finishAffinity();
        LandingActivity.start(this);
        transitionActivityOpen();
    }

    @Override
    public void onDestroy() {
        settingPresenter.onDestroy();
        super.onDestroy();
    }

    @OnClick(R.id.iv_back_arrow)
    public void onBackArrowClicked() {
        onBackPressed();
    }

    protected void helpShiftIntegration() {
        // helpshift integration
      /*  Core.init(Support.getInstance());
        try {
            Core.install(getApplication(),
                    "a7bf76a44832ae9fe4333768085134c6",
                    "social-titali.helpshift.com",
                    "social-titali_platform_20170425095731063-eb42f92c7eee7b4");
        } catch (InstallException e) {
            e.printStackTrace();

        }*/
    }

}
