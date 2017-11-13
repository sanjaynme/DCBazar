package com.dcbazar.np.signUpModule.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.dcbazar.np.R;
import com.dcbazar.np.base.BaseActivity;
import com.dcbazar.np.signUpModule.presenter.TermsPresenter;
import com.dcbazar.np.signUpModule.view.ITermsPrivacyView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Amir on 30/06/2017.
 */

public class PrivacyActivity extends BaseActivity implements ITermsPrivacyView {

    @BindView(R.id.tv_toolbar_title)
    TextView tvToolbar;
    @BindView(R.id.iv_back_arrow)
    ImageView ivBackArrow;
    @BindView(R.id.iv_menu)
    ImageView ivMenu;

    @BindView(R.id.web_view)
    WebView webView;

    TermsPresenter termsPresenter;

    @BindView(R.id.iv_notification)
    ImageView ivNotification;

    int pageId;

    public static void start(Context context) {
        Intent intent = new Intent(context, PrivacyActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupToolbar();
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            setStatusColor();
        }
        termsPresenter = new TermsPresenter(this);
        webView.setBackgroundColor(Color.WHITE);
        termsPresenter.getTermsandPrivacy(2);

    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setStatusColor() {
        Window window = getWindow();
//        Drawable background = ContextCompat.getDrawable(this, R.drawable.toolbar_gradient);
//        window.setStatusBarColor(ContextCompat.getColor(this, android.R.color.transparent));
//        window.setBackgroundDrawable(background);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_privacy;
    }


    @OnClick(R.id.iv_back_arrow)
    public void onBackArrowClicked() {
        onBackPressed();
    }


    private void setupToolbar() {
        ivBackArrow.setVisibility(View.VISIBLE);
//        tvToolbar.setText(getResources().getString(R.string.privacy_policy));
        ivBackArrow.setVisibility(View.VISIBLE);
        ivNotification.setVisibility(View.GONE);
        ivMenu.setVisibility(View.GONE);
    }


    @Override
    public void showPrivacyTerms(String data) {
        webView.loadData(data, "text/html", null);
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

    @OnClick(R.id.iv_back_arrow)
    public void onBackArrowPressed(){
        onBackPressed();
    }

}


