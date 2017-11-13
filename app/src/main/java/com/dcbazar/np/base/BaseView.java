package com.dcbazar.np.base;

/**
 * Created by Sanjay on 31/03/2017.
 */

public interface BaseView {

    //Show progress dialog or progress bar
    void showProgress(String message);

    //Hide progress dialog or progress bar
    void hideProgress();

    //Show toast displaying message
    void showToastMessage(String message);

    //Show alert dialog displaying message
    void showMessageDialog(String message);

    // show authentication failure dialog
    void showAuthenticationFailureDialog(String message);

    //Show progress dialog
    void showProgressDialog(String progressMessage);

    //Hide progress dialog
    void hideProgressDialog();


}
