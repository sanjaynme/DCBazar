package com.dcbazar.np.updateProfileModule;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.dcbazar.np.R;
import com.dcbazar.np.base.BaseActivity;
import com.dcbazar.np.helpers.CommonMethods;
import com.dcbazar.np.helpers.ConnectionMngr;
import com.dcbazar.np.helpers.Contracts;
import com.dcbazar.np.homeModule.homeactivities.HomeActivity;
import com.dcbazar.np.updateProfileModule.adapter.SpinnerAdapter;
import com.dcbazar.np.updateProfileModule.model.UpdateIncomeRangeResponse;
import com.dcbazar.np.updateProfileModule.model.UpdateModel;
import com.dcbazar.np.updateProfileModule.model.UpdateOccupationListResponse;
import com.dcbazar.np.updateProfileModule.presenter.IUpdatePresenter;
import com.dcbazar.np.updateProfileModule.presenter.UpdatePresenter;
import com.dcbazar.np.updateProfileModule.view.IUpdateView;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Amir on 05/04/2017.
 */

public class UpdateActivity extends BaseActivity implements IUpdateView {

    @BindView(R.id.ll_update_profile)
    LinearLayout llUpdateProfile;
    @BindView(R.id.ll_update)
    LinearLayout llUpdate;
    @BindView(R.id.et_first_name)
    EditText etFirstName;
    @BindView(R.id.et_last_name)
    EditText etLastName;
    @BindView(R.id.et_email)
    EditText etEmail;

    @BindView(R.id.spinner_age)
    Spinner spinnerAge;

    @BindView(R.id.spinner_gender)
    Spinner spinnerGender;

    @BindView(R.id.spinner_income)
    Spinner spinnerIncome;

    @BindView(R.id.spinner_occupation)
    Spinner spinnerOccupation;

    @BindView(R.id.tv_toolbar_title)
    TextView tvTitle;

    @BindView(R.id.et_postcode)
    EditText etPostcode;

    @BindView(R.id.btn_update)
    Button btnUpdate;

    @BindView(R.id.iv_income)
    ImageView ivIncome;

    @BindView(R.id.iv_occupation)
    ImageView ivOccupation;

    @BindView(R.id.iv_gender)
    ImageView ivGender;

    @BindView(R.id.iv_age)
    ImageView ivAge;

    UpdateModel updateModel;

    boolean buttonStatus;

    Bundle extras;

    IUpdatePresenter iUpdatePresenter;
    public ArrayList<String> incomeList, occupation, gender, age;
    public ArrayList<UpdateIncomeRangeResponse.IncomeRange> incomeRangeList;
    public ArrayList<UpdateOccupationListResponse.Occupation> occupationlist;


    public static void start(Context context) {
        Intent intent = new Intent(context, UpdateActivity.class);
        context.startActivity(intent);
    }

    public static void start(Context context, boolean extraData) {
        Intent intent = new Intent(context, UpdateActivity.class);
        intent.putExtra(Contracts.Extras.EXTRA_DATA, extraData);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        updateModel = new UpdateModel();
        iUpdatePresenter = new UpdatePresenter(this, preferenceManager);

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
        extras = getIntent().getExtras();
        setupToolbarFields();
        setupPages();
        if(ConnectionMngr.hasConnectionForNoDialog(UpdateActivity.this)){
            iUpdatePresenter.getUserDetails();
        }


    }

    @Override
    protected int getLayout() {
        return R.layout.activity_update_profile;
    }

    private void setupToolbarFields() {
        tvTitle.setText("My Profile");
    }


    private void setupSpinner() {
        gender = new ArrayList<>(Arrays.asList("Male", "Female","Other"));
        gender.add("Gender");
        SpinnerAdapter genderadapter = new SpinnerAdapter(this, R.layout.item_spinner, gender, 1);
        genderadapter.setDropDownViewResource(R.layout.custom_dropdown);
        spinnerGender.setAdapter(genderadapter);
        if (preferenceManager.getStringValues(Contracts.SharedPrefKeys.PROFILE_GENDER).equals("")) {
            spinnerGender.setSelection(genderadapter.getCount());
        } else {
            spinnerGender.setSelection(gender.indexOf(preferenceManager.getStringValues(Contracts.SharedPrefKeys.PROFILE_GENDER)));
        }

        age = new ArrayList<>();

        for (int i = 5; i <= 100; i++) {
            age.add(i + "");
        }
        age.add("Age");
        SpinnerAdapter ageAdapter = new SpinnerAdapter(this, R.layout.item_spinner, age, 4);
        ageAdapter.setDropDownViewResource(R.layout.custom_dropdown);
        spinnerAge.setAdapter(ageAdapter);

        if (preferenceManager.getIntValues(Contracts.SharedPrefKeys.PROFILE_AGE) == 0) {
            spinnerAge.setSelection(ageAdapter.getCount());
        } else {
            String ages = preferenceManager.getIntValues(Contracts.SharedPrefKeys.PROFILE_AGE) + "";
            spinnerAge.setSelection(age.indexOf(ages));
        }

        incomeList = new ArrayList<>();
        incomeList.add(preferenceManager.getStringValues(Contracts.SharedPrefKeys.PROFILE_INCOME));
        incomeList.add("Income");
        SpinnerAdapter incomeadapter = new SpinnerAdapter(this, R.layout.item_spinner, incomeList, 2);
        incomeadapter.setDropDownViewResource(R.layout.custom_dropdown); // The drop down view
        spinnerIncome.setAdapter(incomeadapter);
        if (preferenceManager.getStringValues(Contracts.SharedPrefKeys.PROFILE_INCOME).equals("")) {
            spinnerIncome.setSelection(incomeadapter.getCount());
        } else {
            spinnerIncome.setSelection(incomeList.indexOf(preferenceManager.getStringValues(Contracts.SharedPrefKeys.PROFILE_INCOME)));
        }

        occupationlist = new ArrayList<>();
        occupation = new ArrayList<>();
        occupation.add(preferenceManager.getStringValues(Contracts.SharedPrefKeys.PROFILE_OCCUPATION));
        occupation.add("Occupation");
        SpinnerAdapter occupationadapter = new SpinnerAdapter(this, R.layout.item_spinner, occupation, 3);
        occupationadapter.setDropDownViewResource(R.layout.custom_dropdown); // The drop down view
        spinnerOccupation.setAdapter(occupationadapter);
        if (preferenceManager.getStringValues(Contracts.SharedPrefKeys.PROFILE_OCCUPATION).equals("")) {
            spinnerOccupation.setSelection(occupationadapter.getCount());
        } else {
            spinnerOccupation.setSelection(occupation.indexOf(preferenceManager.getStringValues(Contracts.SharedPrefKeys.PROFILE_OCCUPATION)));
        }


    }

    @OnClick(R.id.iv_back_arrow)
    public void onBackArrowClicked() {

        super.onBackPressed();

    }


    @OnClick(R.id.btn_update)
    public void onUpdateClicked() {
        if (buttonStatus) {
            if(ConnectionMngr.hasConnection(UpdateActivity.this)){
                if (iUpdatePresenter.validateFields(setModel())) {
                    iUpdatePresenter.sendUpdateDetails(setModel());
                }
            }

        } else {
            etFirstName.setEnabled(true);
            etLastName.setEnabled(true);
            etEmail.setTextColor(getResources().getColor(R.color.faint_white));
            etPostcode.setEnabled(true);
            spinnerAge.setEnabled(true);
            spinnerGender.setEnabled(true);
            spinnerIncome.setEnabled(true);
            spinnerOccupation.setEnabled(true);
            ivGender.setVisibility(View.VISIBLE);
            ivAge.setVisibility(View.VISIBLE);
            ivIncome.setVisibility(View.VISIBLE);
            ivOccupation.setVisibility(View.VISIBLE);
            buttonStatus = true;
            btnUpdate.setText("Update");
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

    }

    @Override
    public void showProgressDialog(String progressMessage) {
        showBaseProgressDialog(progressMessage);
    }

    @Override
    public void hideProgressDialog() {
        dismissBaseProgressDialog();
    }

    private UpdateModel setModel() {
        updateModel.setFirstName(etFirstName.getText().toString().trim());
        updateModel.setLastName(etLastName.getText().toString().trim());
        updateModel.setEmail(etEmail.getText().toString().trim());
        updateModel.setAge(spinnerAge.getSelectedItem().toString());
        updateModel.setGender(spinnerGender.getSelectedItem().toString());
        updateModel.setOccupation(spinnerOccupation.getSelectedItem().toString());
        updateModel.setIncome(spinnerIncome.getSelectedItem().toString());
        updateModel.setPostcode(etPostcode.getText().toString().trim());
        String id;
        int pos;

        if (spinnerIncome.getSelectedItemPosition() != incomeRangeList.size()) {
            pos = spinnerIncome.getSelectedItemPosition();
            id = incomeRangeList.get(pos).id + "";
            updateModel.setIncome(id);
        }
        if (spinnerOccupation.getSelectedItemPosition() != occupationlist.size()) {
            pos = spinnerOccupation.getSelectedItemPosition();
            id = occupationlist.get(pos).id + "";
            updateModel.setOccupation(id);
        }

        return updateModel;
    }

    @Override
    public void navigateToHome() {
        if (extras != null) {
            if (extras.getBoolean(Contracts.Extras.EXTRA_DATA)) {
                onBackPressed();
            } else {
                finish();
                HomeActivity.start(UpdateActivity.this);
            }
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void afterGettingUsers() {
        setupToolbarFields();
        setupPages();
    }

    private void setupPages() {

        extras = getIntent().getExtras();
        setupSpinner();

        if (extras != null) {
            if (extras.getBoolean(Contracts.Extras.EXTRA_DATA)) {  //from My profile
                populateProfiles();
                buttonStatus = false;
                btnUpdate.setText("EDIT");
            } else {   //after edit button is clicked from my Profile
                etFirstName.setText(preferenceManager.getStringValues(Contracts.SharedPrefKeys.PROFILE_FIRST_NAME));
                etLastName.setText(preferenceManager.getStringValues(Contracts.SharedPrefKeys.PROFILE_LAST_NAME));
                etEmail.setText(preferenceManager.getStringValues(Contracts.SharedPrefKeys.PROFILE_EMAIL));
                etPostcode.setText(preferenceManager.getStringValues(Contracts.SharedPrefKeys.PROFILE_POSTCODE));
                buttonStatus = true;

                btnUpdate.setText("UPDATE");
            }
        } else {  //called initially after registration and login
            etFirstName.setText(preferenceManager.getStringValues(Contracts.SharedPrefKeys.PROFILE_FIRST_NAME));
            etLastName.setText(preferenceManager.getStringValues(Contracts.SharedPrefKeys.PROFILE_LAST_NAME));
            etEmail.setText(preferenceManager.getStringValues(Contracts.SharedPrefKeys.PROFILE_EMAIL));
            etPostcode.setText(preferenceManager.getStringValues(Contracts.SharedPrefKeys.PROFILE_POSTCODE));
            buttonStatus = true;
            btnUpdate.setText("UPDATE");
        }

        CommonMethods.setupUI(llUpdate, this);
    }

    @Override
    public void populateIncomeRange(UpdateIncomeRangeResponse responseData) {
        incomeRangeList = new ArrayList<>();
        incomeList = new ArrayList<>();
        for (int i = 0; i < responseData.results.incomeRanges.size(); i++) {
            UpdateIncomeRangeResponse.IncomeRange incomeRange = new UpdateIncomeRangeResponse.IncomeRange();
            incomeRange = new UpdateIncomeRangeResponse.IncomeRange();
            incomeRange.id = responseData.results.incomeRanges.get(i).id;
            incomeRange.max = responseData.results.incomeRanges.get(i).max;
            incomeRange.min = responseData.results.incomeRanges.get(i).min;
            incomeList.add(responseData.results.incomeRanges.get(i).min + "-" + responseData.results.incomeRanges.get(i).max);
            incomeRangeList.add(incomeRange);
//            incomeMin = responseData.results.incomeRanges.get(i).min;
        }
        incomeList.add("Income");
        SpinnerAdapter incomeadapter = new SpinnerAdapter(this, R.layout.item_spinner, incomeList, 2);
        incomeadapter.setDropDownViewResource(R.layout.custom_dropdown); // The drop down view
        spinnerIncome.setAdapter(incomeadapter);
        if (preferenceManager.getStringValues(Contracts.SharedPrefKeys.PROFILE_INCOME).equals("")) {
            spinnerIncome.setSelection(incomeadapter.getCount());
        } else {
            spinnerIncome.setSelection(incomeList.indexOf(preferenceManager.getStringValues(Contracts.SharedPrefKeys.PROFILE_INCOME)));
        }
    }

    @Override
    public void populateOccupationList(UpdateOccupationListResponse responseData) {
        occupationlist = new ArrayList<>();
        occupation = new ArrayList<>();
        for (int i = 0; i < responseData.results.occupations.size(); i++) {
            UpdateOccupationListResponse.Occupation occupationListResponse = new UpdateOccupationListResponse.Occupation();
            occupation.add(responseData.results.occupations.get(i).occupation);
            occupationListResponse.id = responseData.results.occupations.get(i).id;
            occupationListResponse.occupation = responseData.results.occupations.get(i).occupation;
            occupationlist.add(occupationListResponse);
        }
        occupation.add("Occupation");

        SpinnerAdapter occupationadapter = new SpinnerAdapter(this, R.layout.item_spinner, occupation, 3);
        occupationadapter.setDropDownViewResource(R.layout.custom_dropdown); // The drop down view
        spinnerOccupation.setAdapter(occupationadapter);
        if (preferenceManager.getStringValues(Contracts.SharedPrefKeys.PROFILE_OCCUPATION).equals("")) {
            spinnerOccupation.setSelection(occupationadapter.getCount());
        } else {

            spinnerOccupation.setSelection(occupation.indexOf(preferenceManager.getStringValues(Contracts.SharedPrefKeys.PROFILE_OCCUPATION)));
        }
    }

    public void populateProfiles() {
        ivGender.setVisibility(View.INVISIBLE);
        ivAge.setVisibility(View.INVISIBLE);
        ivIncome.setVisibility(View.INVISIBLE);
        ivOccupation.setVisibility(View.INVISIBLE);
        etFirstName.setText(preferenceManager.getStringValues(Contracts.SharedPrefKeys.PROFILE_FIRST_NAME));
        etFirstName.setEnabled(false);
        etLastName.setText(preferenceManager.getStringValues(Contracts.SharedPrefKeys.PROFILE_LAST_NAME));
        etLastName.setEnabled(false);
        etEmail.setText(preferenceManager.getStringValues(Contracts.SharedPrefKeys.PROFILE_EMAIL));
        etEmail.setEnabled(false);

        spinnerAge.setSelection(age.indexOf(preferenceManager.getIntValues(Contracts.SharedPrefKeys.PROFILE_AGE) + ""));
        spinnerAge.setEnabled(false);

        spinnerGender.setSelection(gender.indexOf(preferenceManager.getStringValues(Contracts.SharedPrefKeys.PROFILE_GENDER)));
        spinnerGender.setEnabled(false);

        spinnerIncome.setEnabled(false);

        spinnerOccupation.setEnabled(false);

        etPostcode.setText(preferenceManager.getStringValues(Contracts.SharedPrefKeys.PROFILE_POSTCODE));
        etPostcode.setEnabled(false);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
