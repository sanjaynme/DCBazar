package com.dcbazar.np.homeModule.homeFragment;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.ebpearls.choiceapp.R;
import com.ebpearls.choiceapp.customviews.CapitalizedTextView;
import com.ebpearls.choiceapp.dynamicFormListing.DynamicPagesActivity;
import com.ebpearls.choiceapp.helpers.AppLog;
import com.ebpearls.choiceapp.helpers.Contracts;
import com.ebpearls.choiceapp.helpers.SharedPreferenceManager;
import com.ebpearls.choiceapp.homeModule.homeFragment.presenter.DialogPresenter;
import com.ebpearls.choiceapp.homeModule.homeFragment.presenter.IDialogPresenter;
import com.ebpearls.choiceapp.homeModule.homeFragment.syncmodels.SurveyResponseModel;
import com.ebpearls.choiceapp.homeModule.homeFragment.view.IDialogFragmentView;
import com.google.gson.Gson;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Amir on 14/07/2017.
 */

public class PopUpFragment extends DialogFragment implements IDialogFragmentView {

    ProgressBar progressBar;

    private CapitalizedTextView tvSurveyName, tvWelcome, tvDescription;

    private Dialog dialog;

    private SurveyResponseModel surveyResponseModel;

    private ImageView ivCross, ivProfileImage;

    private Button btnStart;

    private SharedPreferenceManager sharedPreferenceManager;

    IDialogPresenter dialogPresenter;

    private ProgressDialog pDialog;


    public static PopUpFragment newInstance(SurveyResponseModel surveyResponseModel) {
        PopUpFragment dialogRsvpFragment = new PopUpFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Contracts.Extras.EXTRA_DATA, new Gson().toJson(surveyResponseModel));
        dialogRsvpFragment.setArguments(bundle);
        return dialogRsvpFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Gson gson = new Gson();
        sharedPreferenceManager = new SharedPreferenceManager(getActivity());
        dialogPresenter = new DialogPresenter(sharedPreferenceManager, this);
        String string = getArguments().getString(Contracts.Extras.EXTRA_DATA);
        surveyResponseModel = new SurveyResponseModel();
        surveyResponseModel = gson.fromJson(string, SurveyResponseModel.class);
    }

    public void setStatusBarColorIfPossible(int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getActivity().getWindow().setStatusBarColor(color);
        }
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvSurveyName = (CapitalizedTextView) view.findViewById(R.id.tv_surveyName);
        tvDescription = (CapitalizedTextView) view.findViewById(R.id.tv_description);
        tvWelcome = (CapitalizedTextView) view.findViewById(R.id.tv_introduction);
        ivCross = (ImageView) view.findViewById(R.id.iv_pop_backarrow);
        progressBar = (ProgressBar) view.findViewById(R.id.profile_progressbar);
        btnStart = (Button) view.findViewById(R.id.btnnext);

        ivProfileImage = (ImageView) view.findViewById(R.id.iv_profile_image);
        tvSurveyName.setText(surveyResponseModel.getName());
        tvWelcome.setText("Welcome To");
        tvDescription.setText(surveyResponseModel.getDescription());

        ivCross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog != null) {
                    getDialog().dismiss();
                }
            }
        });

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialogPresenter.insertSyncDataIntoHistory(surveyResponseModel);
/*
                Date deviceDate = Calendar.getInstance().getTime();
                Date surveyStartDate;
                Date surveyEndDate;
                Date projectStartDate = surveyResponseModel.getStartDate();
                Date projectEndDate = surveyResponseModel.getEndDate();

                if (surveyResponseModel.getRecurringTypeId() == 0) {
                    surveyStartDate = surveyResponseModel.getStartDate();
                    surveyEndDate = surveyResponseModel.getEndDate();
                } else {
                    surveyStartDate = CommonMethods.convertSurveyStartEndStringToDate(CommonMethods.getStartDate(surveyResponseModel.getRecurringTypeId(), deviceDate));
                    surveyEndDate = CommonMethods.convertSurveyStartEndStringToDate(CommonMethods.getEndDate(surveyResponseModel.getRecurringTypeId(), deviceDate));
                }
                if (surveyResponseModel.getLastModifiedDate() != null) {
                    Date lastModifiedDate = CommonMethods.convertLocalTimeStringToDate(surveyResponseModel.getLastModifiedDate());
                    int surveyTimes = surveyResponseModel.getTimes();
                    int count = surveyResponseModel.getSurveyCount();

                    int compareLastModDateWithSurveyStartDate = lastModifiedDate.compareTo(surveyStartDate);
                    int compareLastModDateWithSurveyEndDate = lastModifiedDate.compareTo(surveyEndDate);

                    //Repeating survey
                    if (surveyResponseModel.getIsRecurring().equals(Contracts.IsRecurring.YES)) {
                        //Repeating and limited
                        if (surveyResponseModel.getIsLimited() == Contracts.IsLimited.LIMITED) {
                            if (surveyTimes == count) {
                                showMessageDialog("Your survey repeatition time has expired. Please try after " + changeDateFormat(surveyEndDate.toString()));
                            } else {  //if surveyTimes is greater than count
                                //if lastModified date is in between surveyStart and surveyEnd Date
                                if ((compareLastModDateWithSurveyStartDate >= 0)
                                        && ((compareLastModDateWithSurveyEndDate) <= 0)) {
                                    if (deviceDate.compareTo(projectEndDate) <= 0) {
                                        dialogPresenter.insertSyncDataIntoHistory(surveyResponseModel);
                                    } else {
                                        showMessageDialog("Your survey has expired.");
                                    }
                                } else {
                                    //if lastModified Date is before surveyStartDate
                                    //set count to zero and start fresh survey
                                    UserRepository userRepository = new UserRepository(sharedPreferenceManager);
                                    if (surveyResponseModel.getRecurringTypeId() != 0) {
                                        userRepository.updateSurveyCount(surveyResponseModel.getId());
                                    }
                                    dialogPresenter.insertSyncDataIntoHistory(surveyResponseModel);
                                }
                            }
                        } else { //repeating and unlimited survey
                            if ((compareLastModDateWithSurveyStartDate >= 0)
                                    && ((compareLastModDateWithSurveyEndDate) <= 0)) {
                                if (deviceDate.compareTo(projectEndDate) <= 0) {
                                    dialogPresenter.insertSyncDataIntoHistory(surveyResponseModel);
                                } else {
                                    showMessageDialog("Your survey has expired.");
                                }
                            } else {
                                dialogPresenter.insertSyncDataIntoHistory(surveyResponseModel);
                            }
                        }
                    }
                    //check for survey non repeating
                    else {
                        if ((deviceDate.compareTo(projectStartDate) >= 0)
                                && (deviceDate.compareTo(projectEndDate) <= 0)) {
                            dialogPresenter.insertSyncDataIntoHistory(surveyResponseModel);
                        } else {
                            showMessageDialog("Your survey has expired.");
                        }
                    }
                }*/
            }
        });

        loadProfileImage();

    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // the content
        final RelativeLayout root = new RelativeLayout(getActivity());
        root.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        // creating the fullscreen dialog
        dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(root);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.YELLOW));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        return dialog;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.popup_survey, container);
        return view;
    }

    private void loadProfileImage() {
        Glide.with(getActivity())
                .load(surveyResponseModel.getProfileImage())
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        progressBar.setVisibility(View.GONE);
                        AppLog.d("", "Glide onException: " + e.getMessage());
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        progressBar.setVisibility(View.GONE);
                        return false;
                    }
                }).override(500, 300)
//                .bitmapTransform(new RoundedCornersTransformation(getActivity(), 5, 0))
                .centerCrop()
                .error(R.drawable.default_image)
                .into(ivProfileImage);
    }

    private String changeDateFormat(String givenDate) {
        DateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
        Date date = null;
        try {
            date = formatter.parse(givenDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(date);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        String formatedDate = cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DATE);
        return formatedDate;
    }

    @Override
    public void navigateToDynamic(SurveyResponseModel surveyResponseModel) {
        Gson gson = new Gson();
        DynamicPagesActivity.start(getActivity(), gson.toJson(surveyResponseModel), "1");
        transitionActivityOpen();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (getDialog() != null) {
                    getDialog().dismiss();
                }
            }
        }, 500);
    }

    protected void transitionActivityOpen() {
        getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    @Override
    public void showMessageDialog(String message) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(message);
        builder.setTitle("ChoiceApp");
        builder.setCancelable(false);

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                dialog.dismiss();

            }
        });

        AlertDialog alert = builder.create();
        alert.show();


    }

}



