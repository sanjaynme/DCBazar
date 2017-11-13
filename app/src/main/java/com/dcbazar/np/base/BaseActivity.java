package com.dcbazar.np.base;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.dcbazar.np.R;
import com.dcbazar.np.helpers.SharedPreferenceManager;
import com.dcbazar.np.homeModule.homeactivities.HomeActivity;
import com.dcbazar.np.landingPageModule.LandingActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by Sanjay on 28/03/2017.
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected static String TAG = BaseActivity.class.getSimpleName();
    protected AlertDialog alertDialog;
    protected SharedPreferenceManager preferenceManager;
    private Unbinder unbinder;
    private boolean hasLayout = true;
    private ProgressDialog pDialog;

    @LayoutRes
    protected abstract int getLayout();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (hasLayout) {
            setContentView(getLayout());
            unbinder = ButterKnife.bind(this);
        }
        preferenceManager = new SharedPreferenceManager(this);
        pDialog = new ProgressDialog(this);
    }

    protected void setHasLayout(boolean hasLayout) {
        this.hasLayout = hasLayout;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (!(this instanceof LandingActivity || this instanceof HomeActivity)) {
            transitionBackPressed();
        }
    }

    @Override
    public void onDestroy() {
        if (unbinder != null) {
            unbinder.unbind();
        }
        super.onDestroy();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    protected void transitionBackPressed() {
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    protected void transitionFadeOut() {
        overridePendingTransition(0, R.anim.fade_out);
    }

    protected void transitionActivityOpen() {
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    protected void showBaseToastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    protected void showBaseAlertDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setTitle("ChoiceApp");
        builder.setCancelable(false);

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                dismissAlertDialog();

            }
        });

        AlertDialog alert = builder.create();
        alert.show();


    }

    protected void showBaseFinishActivityDialog(String message) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setTitle("ChoiceApp");
        builder.setCancelable(false);

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                finish();
                transitionBackPressed();

            }
        });

        AlertDialog alert = builder.create();
        alert.show();

    }

    protected void showBaseProgressDialog(String message) {
        pDialog.setMessage(message);
        pDialog.setCancelable(true);
        pDialog.show();

       /* AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.MyFullWidthDialog);
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.dialog_loading, null);
        builder.setView(dialogView);

        TextView textViewMessage = (TextView) dialogView.findViewById(R.id.tv_dialog_message);
        textViewMessage.setText(message);

        showDialog(builder);
        alertDialog.setCancelable(false);*/
    }

    protected void dismissBaseProgressDialog() {
        dismissAlertDialog();
    }

    private void showDialog(AlertDialog.Builder builder) {
        if (alertDialog != null && alertDialog.isShowing()) {
            alertDialog.dismiss();
        }
        alertDialog = builder.create();
        alertDialog.show();
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    protected void dismissAlertDialog() {
        if (!isDestroyed() && !isFinishing() && pDialog != null && pDialog.isShowing()) {
            pDialog.dismiss();
        }
    }

    protected void showBaseAuthenticationFailureDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.MyFullWidthDialog);
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.dialog_alert_msg, null);
        builder.setView(dialogView);

        TextView textViewMessage = (TextView) dialogView.findViewById(R.id.tv_dialog_message);
        textViewMessage.setText(message);

        showDialog(builder);
        alertDialog.setCancelable(true);

        TextView textViewOK = (TextView) dialogView.findViewById(R.id.tv_dialog_ok);
        textViewOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                preferenceManager.clearData();
                dismissAlertDialog();
                LandingActivity.start(BaseActivity.this);
                finishAffinity();
                transitionBackPressed();
            }
        });
    }
}
