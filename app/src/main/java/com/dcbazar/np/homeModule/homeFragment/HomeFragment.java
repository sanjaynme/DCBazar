package com.dcbazar.np.homeModule.homeFragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dcbazar.np.R;
import com.dcbazar.np.base.BaseFragment;
import com.dcbazar.np.helpers.AppLog;
import com.dcbazar.np.helpers.CommonMethods;
import com.dcbazar.np.helpers.Contracts;
import com.dcbazar.np.helpers.SharedPreferenceManager;
import com.dcbazar.np.homeModule.homeFragment.adaptors.HomeAdapter;
import com.dcbazar.np.homeModule.homeFragment.presenter.IHomePresenter;
import com.dcbazar.np.homeModule.homeFragment.syncmodels.SurveyResponseModel;
import com.dcbazar.np.homeModule.homeFragment.view.IHomeView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Amir on 20/04/2017.
 */

public class HomeFragment extends BaseFragment implements IHomeView, HomeAdapter.OnClickListener {
    IHomePresenter iHomePresenter;
    SharedPreferenceManager sharedPreferenceManager;
    private BroadcastReceiver broadcastReceiver;
    int size = 0;

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    @BindView(R.id.tv_search)
    TextView tvSearch;

    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.iv_delete_search)
    ImageButton ivDeleteSearch;

    @BindView(R.id.ll_home_fragment)
    LinearLayout linearLayout;
    @BindView(R.id.rl_search)
    RelativeLayout rlSearch;
    HomeAdapter homeAdapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferenceManager = new SharedPreferenceManager(getActivity());
//        iHomePresenter = new HomePresenter(this, sharedPreferenceManager);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String message = intent.getStringExtra(Contracts.Extras.EXTRA_DATA);
                Gson gson = new Gson();
                List<SurveyResponseModel> surveyResponseModels = gson.fromJson(message,
                        new TypeToken<List<SurveyResponseModel>>() {
                        }.getType());
                setupRecyclerViewForSync(surveyResponseModels);
            }
        };
    }


    @Override
    public void onPause() {
        // Unregister since the activity is paused.
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(
                broadcastReceiver);
        super.onPause();
    }

    private void setupRecyclerViewForSync(List<SurveyResponseModel> listSurveyResponse) {
        if (getActivity() != null) {
            RecyclerView.LayoutManager layoutManager;
            layoutManager = new GridLayoutManager(getActivity(), 2);
            recyclerView.setLayoutManager(layoutManager);
            homeAdapter = new HomeAdapter(getActivity(), listSurveyResponse, this);
            if (listSurveyResponse != null) {
                size = listSurveyResponse.size();
            }
            recyclerView.setAdapter(homeAdapter);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        CommonMethods.setupUI(rlSearch, getActivity());
        CommonMethods.setupUI(linearLayout, getActivity());

        //sync data if internet availabale else load from Database
        iHomePresenter.loadSyncData();

     /*   etSearch.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!v.isActivated()) {
                    tvSearch.setVisibility(View.VISIBLE);
                    etSearch.setCursorVisible(false);
                    ivDeleteSearch.setVisibility(View.GONE);
                } else {
                    tvSearch.setVisibility(View.INVISIBLE);
                    etSearch.setFocusable(true);
                    etSearch.setCursorVisible(true);
                    ivDeleteSearch.setVisibility(View.VISIBLE);
                }
            }
        });*/
        etSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppLog.d("" + tvSearch.getX());
                AppLog.d("" + etSearch.getX());
                tvSearch.setVisibility(View.INVISIBLE);
                etSearch.setFocusable(true);
                etSearch.setCursorVisible(true);
                ivDeleteSearch.setVisibility(View.VISIBLE);
            }
        });

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    tvSearch.setVisibility(View.INVISIBLE);
                } else {
                    tvSearch.setVisibility(View.VISIBLE);
                    etSearch.setCursorVisible(false);
                    ivDeleteSearch.setVisibility(View.GONE);
                }
                if (size > 0) {
                    homeAdapter.getFilter().filter(s.toString().trim());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        ivDeleteSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etSearch.getText().toString().isEmpty()) {
                    tvSearch.setVisibility(View.VISIBLE);
                    etSearch.setCursorVisible(false);
                    ivDeleteSearch.setVisibility(View.GONE);
                }
                etSearch.getText().clear();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().registerReceiver(broadcastReceiver, new IntentFilter(Contracts.Actions.SYNC));

    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_home;
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
        showBaseAlertDialog(message, true);
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

    @Override
    public void setupRecyclerView(List<SurveyResponseModel> listSurveyResponse) {
        RecyclerView.LayoutManager layoutManager;
        recyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(layoutManager);
        int spanCount = 2;
        int spacing = 8;
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing));
        homeAdapter = new HomeAdapter(getActivity(), listSurveyResponse, this);
        if (listSurveyResponse != null) {
            size = listSurveyResponse.size();
        }
        recyclerView.setAdapter(homeAdapter);
    }


    @Override
    protected void transitionBackPressed() {
        super.transitionBackPressed();
    }


    @Override
    public void showPopUpFragment(SurveyResponseModel surveyResponseModel) {
        android.app.FragmentManager manager = getActivity().getFragmentManager();
//        PopUpFragment dialogPopUpFragment = PopUpFragment.newInstance(surveyResponseModel);
//        dialogPopUpFragment.show(manager, "PopupFragment");
    }

    @Override
    public void showSurveyData(SurveyResponseModel responseData) {
        //do nothing but its neccesary
    }

    @Override
    public void onItemClicked(View v, SurveyResponseModel surveyResponseModel, int id) {
        showPopUpFragment(surveyResponseModel);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
