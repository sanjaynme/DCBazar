package com.dcbazar.np.loginModule.changePassword;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ebpearls.choiceapp.R;
import com.ebpearls.choiceapp.base.BaseActivity;
import com.ebpearls.choiceapp.helpers.CommonMethods;
import com.ebpearls.choiceapp.helpers.ConnectionMngr;
import com.ebpearls.choiceapp.helpers.Contracts;
import com.ebpearls.choiceapp.helpers.SharedPreferenceManager;
import com.ebpearls.choiceapp.loginModule.LoginActivity;
import com.ebpearls.choiceapp.retrofitApi.RetrofitSingleton;
import com.ebpearls.choiceapp.retrofitApi.WebserviceApi;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChangePasswordActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "Change Password";


    String oldPassword;
    String newPassword;
    Map<String, String> params;
    private Context context;

    private EditText etOldPassword;
    private EditText etNewpassword;
    private Button btn_reset;


    @BindView(R.id.tv_toolbar_title)
    TextView tvTitle;

    private WebserviceApi apiService;
    SharedPreferenceManager sharedPreferenceManager;
    private boolean isOldImageClicked;
    private boolean isNewImageClicked;
    private boolean isNewComfirmPasswordImageClicked;
    private ImageView btnConfirmPassword, btnNewPassword, btnOldPassword;

    public static void start(Context context) {
        Intent intent = new Intent(context, ChangePasswordActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        etOldPassword = (EditText) findViewById(R.id.et_old_password);
        etNewpassword = (EditText) findViewById(R.id.et_new_password);

        CommonMethods.setupUI(findViewById(R.id.rlchange_password), this);
        btnNewPassword = (ImageView) findViewById(R.id.btn_new_show_hide_password);
        btnOldPassword = (ImageView) findViewById(R.id.btn_old_show_hide_password);

        btnOldPassword.setImageResource(R.drawable.ic_white_eye);
        btnNewPassword.setImageResource(R.drawable.ic_white_eye);

        btn_reset = (Button) findViewById(R.id.btn_submitchange_password);
        btn_reset.setOnClickListener(this);

        params = new HashMap<>();
        apiService = RetrofitSingleton.getApiService();
        sharedPreferenceManager = new SharedPreferenceManager(this);
        tvTitle.setText("Change Password");

    }

    @OnClick(R.id.btn_old_show_hide_password)
    void showHideOldPassword() {
        isOldImageClicked = !isOldImageClicked;
        if (isOldImageClicked) {
            etOldPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            btnOldPassword.setImageResource(R.drawable.ic_white_custom_hide);

        } else {
            etOldPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            btnOldPassword.setImageResource(R.drawable.ic_white_eye);
        }
    }

    @OnClick(R.id.btn_new_show_hide_password)
    void showHideNewPassword() {
        isNewImageClicked = !isNewImageClicked;
        if (isNewImageClicked) {
            etNewpassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            btnNewPassword.setImageResource(R.drawable.ic_white_custom_hide);

        } else {
            etNewpassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            btnNewPassword.setImageResource(R.drawable.ic_white_eye);
        }
    }


    @Override
    protected int getLayout() {
        return R.layout.activity_change_password;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_submitchange_password:
                CommonMethods.hideSoftKeyboard(this);
                getInputFields();
                if (isValid()) {
                    if (ConnectionMngr.hasConnection(ChangePasswordActivity.this)) {
                        showBaseProgressDialog("Changing Password...");

                        params.put("current_password", oldPassword);
                        params.put("new_password", newPassword);
                        params.put("new_password_confirmation", newPassword);

                        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), (new JSONObject(params)).toString());

                        Log.d("ChangeAuthentication", sharedPreferenceManager.getStringValues(Contracts.SharedPrefKeys.AUTHENTICATION_KEY));

                        Call<ChangePasswordResponse> change_password = apiService.changePassword(sharedPreferenceManager.getStringValues(Contracts.SharedPrefKeys.AUTHENTICATION_KEY), body);
                        change_password.enqueue(new Callback<ChangePasswordResponse>() {
                            @Override
                            public void onResponse(Call<ChangePasswordResponse> call, Response<ChangePasswordResponse> response) {
                                if (response.isSuccessful()) {
                                    if (response.body().statusCode.equalsIgnoreCase(Contracts.HttpStatusCodes.STATUS_OK)) {
                                        showChangePasswordAlertDialog(response.body().message);
                                        etOldPassword.getText().clear();
                                        etNewpassword.getText().clear();
                                    } else {
                                        showBaseAlertDialog(response.body().message);
                                    }
                                } else {
                                    showBaseAlertDialog(response.body().message);
                                }
                            }

                            @Override
                            public void onFailure(Call<ChangePasswordResponse> call, Throwable t) {
                                showBaseAlertDialog(Contracts.Errors.NO_INTERNET_AVAILABLE);
                            }
                        });

                    }


                }
                break;
        }
    }

    private void showChangePasswordAlertDialog(String message) {
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
                LoginActivity.start(ChangePasswordActivity.this);
                transitionActivityOpen();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();

        /*AlertDialog alert = builder.create();
        alert.show();     AlertDialog.Builder builder = new AlertDialog.Builder(ChangePasswordActivity.this, R.style.MyFullWidthDialog);
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
                finish();
                LoginActivity.start(ChangePasswordActivity.this);
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


    private void getInputFields() {
        oldPassword = etOldPassword.getText().toString().trim();
        newPassword = etNewpassword.getText().toString().trim();
    }

    private boolean isValid() {
        if (oldPassword.isEmpty()) {
            displayMessage("Please enter a valid current password.");
            return false;
        } else if (newPassword.isEmpty()) {
            displayMessage("Please enter a valid new password.");
            return false;
        }
        return true;
    }

    @OnClick(R.id.iv_back_arrow)
    public void onBackArrowClicked() {
        onBackPressed();
    }

    public void displayMessage(String msg) {
        showBaseAlertDialog(msg);
    }


}
