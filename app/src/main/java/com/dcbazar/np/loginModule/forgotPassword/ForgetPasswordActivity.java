package com.dcbazar.np.loginModule.forgotPassword;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ebpearls.choiceapp.R;
import com.ebpearls.choiceapp.base.BaseActivity;
import com.ebpearls.choiceapp.helpers.CommonMethods;
import com.ebpearls.choiceapp.loginModule.LoginActivity;
import com.ebpearls.choiceapp.loginModule.forgotPassword.presenter.ForgetPasswordPresenter;
import com.ebpearls.choiceapp.loginModule.forgotPassword.presenter.IForgetPasswordPresenter;
import com.ebpearls.choiceapp.loginModule.forgotPassword.view.IForgotPasswordView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Sanjay on 4/5/2017.
 */

public class ForgetPasswordActivity extends BaseActivity implements IForgotPasswordView {


    private IForgetPasswordPresenter forgotPasswordPresenter;

    @BindView(R.id.tv_toolbar_title)
    TextView tvTitle;

    @BindView(R.id.et_forgot_email)
    EditText edtForgotEmail;

    @BindView(R.id.btn_submit_email)
    Button btnSubmitEmail;

    @BindView(R.id.rl_forget_password)
    RelativeLayout rlForget_password;

    @Override

    protected int getLayout() {
        return R.layout.activity_forgot_password;
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, ForgetPasswordActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tvTitle.setText("Forgot Password");
        forgotPasswordPresenter = new ForgetPasswordPresenter(this);
        CommonMethods.setupUI(rlForget_password, this);
    }

    @OnClick(R.id.btn_submit_email)
    void submitEmailButtonClicked() {
        forgotPasswordPresenter.submitEmail(edtForgotEmail.getText().toString().trim());
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

    }

    @Override
    public void showMessageDialog(String message) {
        showBaseAlertDialog(message);
    }

    @Override
    public void showAuthenticationFailureDialog(String message) {

    }

    @Override
    public void setupViews() {

    }

    @Override
    public void showError(String message) {
        showBaseAlertDialog(message);
    }

    @Override
    public void successReset(String message) {

        navigateToLogin(message);
    }

    @Override
    public void navigateToLogin(String message) {
        edtForgotEmail.getText().clear();
        showForgetMessageDialog(message);
//        startSpecificActivity(LoginActivity.class);
    }

    @Override
    public void showForgetMessageDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setTitle("ChoiceApp");
        builder.setCancelable(false);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                dismissAlertDialog();
                finish();
                LoginActivity.start(ForgetPasswordActivity.this);
                transitionActivityOpen();

            }
        });

        AlertDialog alert = builder.create();
        alert.show();

        /*AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.MyFullWidthDialog);
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
                finishAffinity();
                LoginActivity.start(ForgetPasswordActivity.this);
                transitionActivityOpen();
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

    @OnClick(R.id.iv_back_arrow)
    public void onBackArrowClicked() {
        onBackPressed();
    }


}
