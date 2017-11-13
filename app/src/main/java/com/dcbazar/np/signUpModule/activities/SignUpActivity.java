package com.dcbazar.np.signUpModule.activities;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.dcbazar.np.R;
import com.dcbazar.np.base.BaseActivity;
import com.dcbazar.np.helpers.AppLog;
import com.dcbazar.np.helpers.CommonMethods;
import com.dcbazar.np.helpers.ConnectionMngr;
import com.dcbazar.np.loginModule.LoginActivity;
import com.dcbazar.np.loginModule.login.model.FbRequestModel;
import com.dcbazar.np.signUpModule.model.SignUpModel;
import com.dcbazar.np.signUpModule.presenter.ISignUpPresenter;
import com.dcbazar.np.signUpModule.presenter.SignUpPresenter;
import com.dcbazar.np.signUpModule.view.ISignUpView;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class SignUpActivity extends BaseActivity implements ISignUpView {

    @BindView(R.id.iv_show_password)
    ImageView btnShowHidePassword;

    @BindView(R.id.et_firstname)
    EditText etFirstName;

    @BindView(R.id.et_lastname)
    EditText etLastName;

    @BindView(R.id.et_email)
    EditText etEmail;

    @BindView(R.id.et_password)
    EditText etPassword;

    @BindView(R.id.btn_signup)
    Button btnSignup;

    @BindView(R.id.sv_sign_up)
    ScrollView svSignUp;

    @BindView(R.id.tv_terms)
    TextView tvTerms;

    SignUpModel signUpModel;
    ISignUpPresenter presenter;
    FbRequestModel fbRequestModel;
    private static CallbackManager callBackManager;
    private List<String> needPermissions = Arrays.asList("email", "public_profile");
    private String fbId;

    public static void start(Context context) {
        Intent intent = new Intent(context, SignUpActivity.class);
        context.startActivity(intent);
    }

    public static void start(Context context, Bundle bundle) {
        Intent intent = new Intent(context, SignUpActivity.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CommonMethods.setupUI(findViewById(R.id.activity_register), this);
        signUpModel = new SignUpModel();
        presenter = new SignUpPresenter(this, preferenceManager);
        fbRequestModel = new FbRequestModel();

        //space validation for last name
        InputFilter filter = new InputFilter() {
            public CharSequence filter(CharSequence source, int start, int end,
                                       Spanned dest, int dstart, int dend) {
                for (int i = start; i < end; i++) {
                    if (Character.isWhitespace(source.charAt(i))) {
                        return "";
                    }
                }
                return null;
            }

        };
        etLastName.setFilters(new InputFilter[]{filter});


        setSpannableText();
//        btnShowHidePassword.setImageResource(R.drawable.ic_white_eye);
        getExtraDatas();
    }

    @Override
    public void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    private void getExtraDatas() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String firstName, lastName, email;

            firstName = bundle.getString("firstname");
            lastName = bundle.getString("lastname");
            email = bundle.getString("email");
            fbId = bundle.getString("fbId");

            etFirstName.setText(firstName);
            etLastName.setText(lastName);
            etEmail.setText(email);
            if (!email.isEmpty()) {
                etEmail.setEnabled(false);
                etEmail.setClickable(false);
            }
        }
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_register;
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

    @OnClick(R.id.iv_show_password)
    void showHidePassword() {
        if (etPassword.getTransformationMethod() == PasswordTransformationMethod.getInstance()) {
            etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
//            btnShowHidePassword.setImageResource(R.drawable.ic_white_custom_hide);
        } else if (etPassword.getTransformationMethod() == HideReturnsTransformationMethod.getInstance()) {
            etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
//            btnShowHidePassword.setImageResource(R.drawable.ic_white_eye);
        }
    }

    @OnClick(R.id.btn_signup)
    public void onSignUpClicked() {
        signUpModel.setFirstName(etFirstName.getText().toString().trim());
        signUpModel.setLastName(etLastName.getText().toString().trim());
        signUpModel.setEmail(etEmail.getText().toString().trim());
        signUpModel.setPassword(etPassword.getText().toString().trim());

        signUpModel.setFbToken(fbId);
        if (presenter.validateFields(signUpModel)) {
            presenter.sendRegisterDetails(signUpModel);
        }
    }

    @OnClick(R.id.layout_facebook)
    public void onFbButtonClicked() {
        if(ConnectionMngr.hasConnection(SignUpActivity.this)){
            doFacebookLogin();
        }

    }

    @OnClick(R.id.iv_register_cross)
    public void onBackArrowClicked() {
        onBackPressed();
    }


    @Override
    public void navigateToLogin(String message) {
        etFirstName.getText().clear();
        etLastName.getText().clear();
        etEmail.getText().clear();
        etPassword.getText().clear();
        showSignUpMessageDialog(message);
    }

    @Override
    public void onAlreadyUsedEmail() {
        etEmail.setEnabled(true);
        etFirstName.setEnabled(true);
        etLastName.setEnabled(true);
    }

    @Override
    public void showSignUpMessageDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.MyFullWidthDialog);
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.dialog_alert_msg, null);
        builder.setView(dialogView);

        TextView textViewMessage = (TextView) dialogView.findViewById(R.id.tv_dialog_message);
        textViewMessage.setText(message);

        showDialog(builder);

        TextView textViewOK = (TextView) dialogView.findViewById(R.id.tv_dialog_ok);
        textViewOK.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {

                if (!isDestroyed() && !isFinishing() && alertDialog != null && alertDialog.isShowing()) {
                    alertDialog.dismiss();
                }
                Bundle bundle = new Bundle();
                bundle.putString("Message", "message");
                bundle.putString("Email", etEmail.getText().toString().trim());
                finishAffinity();
                LoginActivity.start(SignUpActivity.this, bundle);
                transitionBackPressed();
            }
        });
    }


    private void showDialog(AlertDialog.Builder builder) {
        if (alertDialog != null && alertDialog.isShowing()) {
            alertDialog.dismiss();
        }
        alertDialog = builder.create();
        alertDialog.show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callBackManager.onActivityResult(requestCode, resultCode, data);
    }

    public void setSpannableText() {
//        SpannableString ss = new SpannableString(getResources().getString(R.string.terms_conditions));
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                TermsActivity.start(SignUpActivity.this);
                transitionActivityOpen();
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(true);
                ds.setColor(getResources().getColor(R.color.register_edittext_hint));

            }
        };
//        ss.setSpan(clickableSpan, 43, 60, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//        tvTerms.setMovementMethod(LinkMovementMethod.getInstance());
//        tvTerms.setText(ss);
    }

    public void doFacebookLogin() {
        callBackManager = CallbackManager.Factory.create();
        //set permissions
        LoginManager.getInstance().logInWithReadPermissions(this, needPermissions);

        LoginManager.getInstance().registerCallback(callBackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(final LoginResult loginResult) {
                        presenter.getFacebookId(loginResult.getAccessToken().getToken());
                        AppLog.d("token", loginResult.getAccessToken().getToken());
                    }

                    @Override
                    public void onCancel() {
                        AppLog.d("fb_login_cancel", "On cancel");
                    }

                    @Override
                    public void onError(FacebookException error) {
                        AppLog.d("fb_login_error", error.toString());
                    }
                });
    }
}
