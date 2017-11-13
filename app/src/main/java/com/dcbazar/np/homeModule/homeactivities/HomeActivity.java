package com.dcbazar.np.homeModule.homeactivities;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dcbazar.np.R;
import com.dcbazar.np.base.BaseActivity;
import com.dcbazar.np.base.BaseView;
import com.dcbazar.np.helpers.CommonMethods;
import com.dcbazar.np.helpers.ConnectionMngr;
import com.dcbazar.np.helpers.Contracts;
import com.dcbazar.np.helpers.SharedPreferenceManager;
import com.dcbazar.np.homeModule.homeFragment.HomeFragment;
import com.dcbazar.np.retrofitApi.RetrofitSingleton;
import com.dcbazar.np.retrofitApi.WebserviceApi;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Sanjay on 3/29/2017.
 */

public class HomeActivity extends BaseActivity implements BaseView {
    @BindView(R.id.tv_toolbar_title)
    TextView tvTitle;

    @BindView(R.id.layout_drawer)
    DrawerLayout drawerLayout;

    @BindView(R.id.tv_drawer_title)
    TextView tvDrawerTitle;

    @BindView(R.id.ll_container)
    LinearLayout linearLayout;

    @BindView(R.id.iv_menu)
    ImageView ivMenu;

    @BindView(R.id.iv_notification)
    ImageView ivNotification;

    @BindView(R.id.iv_back_arrow)
    ImageView ivBackArrow;

    @BindView(R.id.rl_drawer_reports)
    RelativeLayout rlDrawerReports;


    public WebserviceApi apiService;
    public String authenticationKey;
    SharedPreferenceManager sharedPreferenceManager;
    HomeFragment homeFragment;

    public static void start(Context context) {
        Intent intent = new Intent(context, HomeActivity.class);
        context.startActivity(intent);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CommonMethods.setupUI(linearLayout, this);
        apiService = RetrofitSingleton.getApiService();
        sharedPreferenceManager = new SharedPreferenceManager(this);
        authenticationKey = sharedPreferenceManager.getStringValues(Contracts.SharedPrefKeys.AUTHENTICATION_KEY);
        Log.d(TAG, "HomeActiviyAuth: " + authenticationKey);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            setStatusColor();
        }
        setupToolbarAndBackground();
        loadHomeFragment();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setStatusColor() {
        Window window = getWindow();
//        Drawable background = getResources().getDrawable(R.drawable.toolbar_gradient);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(android.R.color.transparent));
//        window.setBackgroundDrawable(background);
    }

    private void setupToolbarAndBackground() {
        tvTitle.setText("ChoiceApp");
        tvDrawerTitle.setText("ChoiceApp");
        ivMenu.setVisibility(View.VISIBLE);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_home;
    }

    private void loadHomeFragment() {
        homeFragment = new HomeFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fl_container, homeFragment, "HOME FRAGMENT")
                .commit();
    }

    @OnClick(R.id.iv_menu)
    public void onMenuClicked() {
        drawerLayout.openDrawer(GravityCompat.END);
    }


    @OnClick(R.id.rl_drawer_home)
    public void onHomeClicked() {
        drawerLayout.closeDrawer(GravityCompat.END);
    }

    @OnClick(R.id.rl_drawer_profile)
    public void onMyProfileClicked() {
//        UpdateActivity.start(HomeActivity.this, Contracts.FROMMENU);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                drawerLayout.closeDrawer(GravityCompat.END);
            }
        }, 500);
    }

    @OnClick(R.id.rl_drawer_history)
    public void onHistoryClicked() {
//        HistoryListActivity.start(this);
        transitionActivityOpen();
    }

    @OnClick(R.id.rl_drawer_reports)
    public void onReportsClicked() {
        if(ConnectionMngr.hasConnection(HomeActivity.this)){
//            ReportListActivity.start(this);
            transitionActivityOpen();
        }

    }

    @OnClick(R.id.rl_drawer_notification)
    public void onNotificationClicked() {
        if(ConnectionMngr.hasConnection(HomeActivity.this)){
//            NotificationListingActivity.start(this);
            transitionActivityOpen();
        }
    }

    @OnClick(R.id.rl_drawer_settings)
    public void onSettingsClicked() {
        SettingActivity.start(this);
        transitionActivityOpen();
    }

    @OnClick(R.id.iv_notification)
    public void onNotificationIconClicked() {
        if(ConnectionMngr.hasConnection(HomeActivity.this)){
//            NotificationListingActivity.start(this);
            transitionActivityOpen();
        }
    }

    @OnClick(R.id.iv_drawer_back_arrow)
    public void onDrawerBackArrow() {
        drawerLayout.closeDrawer(GravityCompat.END);
    }

    @OnClick(R.id.iv_back_arrow)
    public void onBackArrowClicked() {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.END)) {
            drawerLayout.closeDrawer(GravityCompat.END);
        } else {
            super.onBackPressed();
        }
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
        showBaseAuthenticationFailureDialog(message);
    }

    @Override
    public void showProgressDialog(String progressMessage) {
        showBaseProgressDialog(progressMessage);
    }

    @Override
    public void hideProgressDialog() {
        dismissBaseProgressDialog();
    }

}
