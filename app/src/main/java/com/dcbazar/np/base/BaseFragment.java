package com.dcbazar.np.base;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.dcbazar.np.R;
import com.dcbazar.np.helpers.SharedPreferenceManager;
import com.dcbazar.np.loginModule.LoginActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Sanjay on 29/03/2017.
 */


public abstract class BaseFragment extends Fragment {
    protected AlertDialog alertDialog;
    private Unbinder unbinder;
    protected SharedPreferenceManager preference;
    private ProgressDialog pDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(getLayout(), container, false);
        unbinder = ButterKnife.bind(this, view);
        pDialog = new ProgressDialog(getActivity());
        return view;
    }

    @Override
    public void onDestroy() {
        if (unbinder != null) {
            unbinder.unbind();
        }
        super.onDestroy();
    }

    @LayoutRes
    protected abstract int getLayout();

    protected void startSpecificActivity(Class<?> toActivity) {
        Intent intent = new Intent(getContext(), toActivity);
        startActivity(intent);
    }

    protected void startSpecificActivity(Class<?> toActivity, Bundle bundle) {
        Intent intent = new Intent(getContext(), toActivity);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    protected void transitionBackPressed() {
        getActivity().overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    protected void transitionFadeOut() {

        getActivity().overridePendingTransition(0, R.anim.fade_out);
    }

    protected void transitionActivityOpen() {
        getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }


    protected void showBaseAlertDialog(String message, boolean isCancelable) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(message);
        builder.setTitle("ChoiceApp");
        builder.setCancelable(isCancelable);

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
       /* AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.MyFullWidthDialog);
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View dialogView = inflater.inflate(R.layout.dialog_alert_msg, null);
        builder.setView(dialogView);
        TextView textViewMessage = (TextView) dialogView.findViewById(R.id.tv_dialog_message);
        textViewMessage.setText(message);
        alertDialog = builder.create();
        alertDialog.show();
        TextView textViewOK = (TextView) dialogView.findViewById(R.id.tv_dialog_ok);
        textViewOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismissAlertDialog();
            }
        });*/
    }

    protected void showBaseProgressDialog(String message) {
        pDialog.setMessage(message);
        pDialog.setCancelable(false);
        pDialog.show();

       /* AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.MyFullWidthDialog);
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View dialogView = inflater.inflate(R.layout.dialog_loading, null);
        builder.setView(dialogView);

        TextView textViewMessage = (TextView) dialogView.findViewById(R.id.tv_dialog_message);
        textViewMessage.setText(message);

        alertDialog = builder.create();
        alertDialog.show();
        alertDialog.setCancelable(false);*/
    }

    protected void dismissBaseProgressDialog() {
        dismissAlertDialog();
    }

    protected void showBaseToastMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    protected void dismissAlertDialog() {
        if (!getActivity().isDestroyed() && !getActivity().isFinishing() && pDialog != null && pDialog.isShowing()) {
            pDialog.dismiss();
        }
    }

    protected void showBaseAuthenticationFailureDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.MyFullWidthDialog);
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View dialogView = inflater.inflate(R.layout.dialog_alert_msg, null);
        builder.setView(dialogView);

        TextView textViewMessage = (TextView) dialogView.findViewById(R.id.tv_dialog_message);
        textViewMessage.setText(message);

        alertDialog = builder.create();
        alertDialog.show();
        alertDialog.setCancelable(false);

        TextView textViewOK = (TextView) dialogView.findViewById(R.id.tv_dialog_ok);
        textViewOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                preference.clearData();
                dismissAlertDialog();
                startSpecificActivity(LoginActivity.class);
                getActivity().finishAffinity();
            }
        });
    }
}
