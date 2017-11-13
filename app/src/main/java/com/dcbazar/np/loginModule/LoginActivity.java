package com.dcbazar.np.loginModule;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ebpearls.choiceapp.R;
import com.ebpearls.choiceapp.base.BaseActivity;
import com.ebpearls.choiceapp.dbSync.MyService;
import com.ebpearls.choiceapp.dbSync.ServiceHelper;
import com.ebpearls.choiceapp.helpers.AppLog;
import com.ebpearls.choiceapp.helpers.CommonMethods;
import com.ebpearls.choiceapp.helpers.ConnectionMngr;
import com.ebpearls.choiceapp.helpers.Contracts;
import com.ebpearls.choiceapp.helpers.SharedPreferenceManager;
import com.ebpearls.choiceapp.homeModule.homeactivities.HomeActivity;
import com.ebpearls.choiceapp.loginModule.forgotPassword.ForgetPasswordActivity;
import com.ebpearls.choiceapp.loginModule.login.model.FbRequestModel;
import com.ebpearls.choiceapp.loginModule.login.presenter.ILoginPresenter;
import com.ebpearls.choiceapp.loginModule.login.presenter.LoginPresenter;
import com.ebpearls.choiceapp.loginModule.login.view.ILoginView;
import com.ebpearls.choiceapp.signUpModule.activities.SignUpActivity;
import com.ebpearls.choiceapp.updateProfileModule.UpdateActivity;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.realm.Realm;

public class LoginActivity extends BaseActivity implements ILoginView {
    @BindView(R.id.et_email)
    EditText edtEmail;

    @BindView(R.id.et_password)
    EditText edtPassword;

    @BindView(R.id.tv_forgot_password)
    TextView tvForgotPassword;

    @BindView(R.id.btn_show_hide_password)
    ImageView btnShowHidePassword;

    @BindView(R.id.ll_container)
    LinearLayout llcontainer;
    private MyService mMyService;
    private Intent mServiceIntent;

    private String notificationToken,deviceID;

    private SharedPreferenceManager preference;
    private List<String> needpermissions = Arrays.asList("email", "public_profile");
    private Boolean isClicked = false;
    private FbRequestModel fbRequestModel;
    private Bundle bundle;
    private CallbackManager callBackManager;
    private ILoginPresenter loginPresenter;

    private List<String> facebookPermissions = Arrays.asList("email", "public_profile");

    public static void start(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    public static void start(Context context, Bundle bundle) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        facebookInitialize();

        mMyService = new MyService(this);
        mServiceIntent = new Intent(this, mMyService.getClass());

//        ServiceHelper.startService(LoginActivity.this);

        CommonMethods.setupUI(llcontainer, this);
        preference = new SharedPreferenceManager(this);

        loginPresenter = new LoginPresenter(this, preference);
        fbRequestModel = new FbRequestModel();

        CommonMethods.setupUI(findViewById(R.id.activity_sign_in), this);
        btnShowHidePassword.setImageResource(R.drawable.ic_white_eye);
        Bundle extras = getIntent().getExtras();
        String message, email;
        if (extras != null) {
            message = extras.getString("Message");
            email = extras.getString("Email");
            edtEmail.setText(email);
        }
    }


    private void facebookInitialize() {
        FacebookSdk.sdkInitialize(getApplicationContext());
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_signin;
    }

    @OnClick(R.id.btn_normal_login)
    void normalLogin() {
        deviceID = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        preference.setKeyValues(Contracts.SharedPrefKeys.DEVICE_ID,deviceID);
        notificationToken = preference.getStringValues(Contracts.SharedPrefKeys.NOTIFICATION_TOKEN);
        if (notificationToken != null && !notificationToken.isEmpty()) {

        } else {
            tokenRequest();
        }
        Log.d("token", notificationToken);
        Log.d("deviceID", deviceID);
        loginPresenter.doNormalLogin(edtEmail.getText().toString().trim(), edtPassword.getText().toString());
    }

    @OnClick(R.id.layout_login_facebook)
    public void onFacebookLoginClicked() {
        if(ConnectionMngr.hasConnection(LoginActivity.this)){
            facebookLogin();
        }

    }

    @OnClick(R.id.tv_forgot_password)
    void forgetPasswordButton() {
        loginPresenter.forgetPasswordButtonClicked();
    }

    @OnClick(R.id.btn_show_hide_password)
    void showHidePassword() {
        if (edtPassword.getTransformationMethod() == PasswordTransformationMethod.getInstance()) {
            edtPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            btnShowHidePassword.setImageResource(R.drawable.ic_white_custom_hide);
        } else if (edtPassword.getTransformationMethod() == HideReturnsTransformationMethod.getInstance()) {
            edtPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            btnShowHidePassword.setImageResource(R.drawable.ic_white_eye);
        }
    }

    @Override
    public void showProgressDialog(String message) {
        showBaseProgressDialog(message);
    }

    @Override
    public void hideProgressDialog() {
        dismissBaseProgressDialog();
    }


    @Override
    public void showProgress(String message) {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showToastMessage(String message) {
        showBaseToastMessage(message);
    }

    @Override
    public void showMessageDialog(String message) {
        showBaseAlertDialog(message);
    }

    @Override
    public void showAuthenticationFailureDialog(String message) {

    }


    @Override
    public void showError(String error) {
        showBaseAlertDialog(error);
    }

    private void facebookLogin() {
        callBackManager = CallbackManager.Factory.create();
        //set permissions
        LoginManager.getInstance().logInWithReadPermissions(this, facebookPermissions);
        LoginManager.getInstance().registerCallback(callBackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(final LoginResult loginResult) {
                        loginPresenter.onFacebookResponse(loginResult.getAccessToken().getToken());
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

    @Override
    public void openForgetPasswordActivity() {
        ForgetPasswordActivity.start(LoginActivity.this);
        transitionActivityOpen();
    }


    @Override
    public void navigateToUpdateProfile() {
        finishAffinity();
        UpdateActivity.start(LoginActivity.this, Contracts.FROMLOGIN);
        transitionActivityOpen();
    }

    @Override
    protected void onStop() {
        stopService(mServiceIntent);
        Log.d(TAG, "onStop: ");
        super.onStop();
    }

    @Override
    public void onDestroy() {
        loginPresenter.onDestroy();
        stopService(mServiceIntent);
        Log.d(TAG, "onDestroy:");
        Realm realm = Realm.getDefaultInstance();
        realm.close();
        super.onDestroy();
    }

    @Override
    public void navigateToRegister(String firstName, String lastName, String email, String fbId) {
        Bundle bundle = new Bundle();
        bundle.putString("firstname", firstName);
        bundle.putString("lastname", lastName);
        bundle.putString("email", email);
        bundle.putString("fbId", fbId);
        finishAffinity();
        SignUpActivity.start(LoginActivity.this, bundle);
        transitionActivityOpen();
    }

    @Override
    public void navigateToHome() {
        ServiceHelper.startService(getApplicationContext());
        edtEmail.getText().clear();
        edtPassword.getText().clear();
        finishAffinity();
        HomeActivity.start(LoginActivity.this);
        transitionActivityOpen();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callBackManager.onActivityResult(requestCode, resultCode, data);
    }

    @OnClick(R.id.iv_login_cross)
    public void onBackArrowClicked() {
        onBackPressed();
        edtEmail.getText().clear();
        edtPassword.getText().clear();
    }

    private void tokenRequest() {
        FirebaseMessaging.getInstance().subscribeToTopic("news");
        notificationToken = FirebaseInstanceId.getInstance().getToken();
        Log.d("notificationtoken", "" + notificationToken);
        preference.setKeyValues(Contracts.SharedPrefKeys.NOTIFICATION_TOKEN,notificationToken);
        if (notificationToken != null && !notificationToken.isEmpty()) {
            //registerFirebaseTokenToServer(notificationToken);
        } else {
            tokenRequest();
        }
    }


}
