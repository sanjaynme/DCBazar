package com.dcbazar.np.loginModule.login.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Amir on 04/05/2017.
 */

public class FbRequestModel {

    @SerializedName("fb_token")
    @Expose
    public String fbToken;


}
