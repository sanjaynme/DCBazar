package com.dcbazar.np.updateProfileModule.presenter;

import android.content.Context;

import com.dcbazar.np.R;
import com.dcbazar.np.base.BaseCallBack;
import com.dcbazar.np.helpers.SharedPreferenceManager;
import com.dcbazar.np.updateProfileModule.interactor.IUpdateInteractor;
import com.dcbazar.np.updateProfileModule.interactor.UpdateInteractor;
import com.dcbazar.np.updateProfileModule.model.UpdateModel;
import com.dcbazar.np.updateProfileModule.model.UpdateResponseModel;
import com.dcbazar.np.updateProfileModule.view.IUpdateView;


/**
 * Created by Amir on 05/04/2017.
 */

public class UpdatePresenter implements IUpdatePresenter {

    IUpdateView updateView;
    Context context;
    IUpdateInteractor iUpdateInteractor;
    SharedPreferenceManager sharedPreferenceManager;

    public UpdatePresenter(IUpdateView updateView, SharedPreferenceManager sharedPreferenceManager) {
        this.updateView = updateView;
        this.context = (Context) updateView;
        this.sharedPreferenceManager = sharedPreferenceManager;
        iUpdateInteractor = new UpdateInteractor(sharedPreferenceManager);

    }

    @Override
    public boolean validateFields(UpdateModel updateModel) {
        if (updateModel.getFirstName().isEmpty()) {
            updateView.showMessageDialog(this.context.getResources().getString(R.string.error_firstname));
            return false;
        } else if (updateModel.getLastName().isEmpty()) {
            updateView.showMessageDialog(this.context.getResources().getString(R.string.error_lastname));
            return false;
        } else if (updateModel.getAge().equalsIgnoreCase("age")) {
            updateView.showMessageDialog(this.context.getResources().getString(R.string.error_age));
            return false;
        } else if (updateModel.getGender().equalsIgnoreCase("gender")) {
            updateView.showMessageDialog(this.context.getResources().getString(R.string.error_gender));
            return false;
        } else if (updateModel.getOccupation().equalsIgnoreCase("occupation")) {
            updateView.showMessageDialog(this.context.getResources().getString(R.string.error_occupation));
            return false;
        } else if (updateModel.getPostcode().isEmpty()) {
            updateView.showMessageDialog(this.context.getResources().getString(R.string.error_postcode));
            return false;
        } else if (updateModel.getPostcode().length() < 4) {
            updateView.showMessageDialog(this.context.getResources().getString(R.string.error_postcode_length));
            return false;
        }
        return true;
    }

    @Override
    public void sendUpdateDetails(final UpdateModel updateModel) {
        updateView.showProgressDialog("Updating Profile...");
        iUpdateInteractor.updateProfile(updateModel, new BaseCallBack<UpdateResponseModel>() {
            @Override
            public void onSuccess(UpdateResponseModel responseData) {

                if (updateView != null) {
                    updateView.hideProgressDialog();
                    updateView.navigateToHome();
                }

            }

            @Override
            public void onError(String message) {
                if (updateView != null) {
                    updateView.hideProgressDialog();
                    updateView.showMessageDialog(message);
                }

            }

            @Override
            public void onFailure(String message) {
                if (updateView != null) {
                    updateView.hideProgressDialog();
                    updateView.showMessageDialog(message);
                }

            }

            @Override
            public void onAuthenticationFailure(String message) {
                if (updateView != null) {
                    updateView.hideProgressDialog();
                    updateView.showMessageDialog(message);
                }

            }
        });
    }

    @Override
    public void getUserDetails() {
        updateView.showProgressDialog("Fetching Profile...");
        iUpdateInteractor.getAuthenticatedUser(new BaseCallBack<UpdateResponseModel>() {
            @Override
            public void onSuccess(UpdateResponseModel responseData) {
                if (updateView != null) {
                    updateView.afterGettingUsers();
                }

            }

            @Override
            public void onError(String message) {
                if (updateView != null) {
                    updateView.hideProgressDialog();
                    updateView.showMessageDialog(message);
                }

            }

            @Override
            public void onFailure(String message) {
                if (updateView != null) {
                    updateView.hideProgressDialog();
                    updateView.showMessageDialog(message);
                }

            }

            @Override
            public void onAuthenticationFailure(String message) {
                if (updateView != null) {
                    updateView.hideProgressDialog();
                    updateView.showAuthenticationFailureDialog(message);
                }

            }
        });
    }


    @Override
    public void onDestroy() {
//        //ChoiceApp.closeRealm();
        updateView = null;
    }
}
