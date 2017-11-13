package com.dcbazar.np.loginModule.changePassword;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Sanjay on 5/5/2017.
 */

public class ChangePasswordResponse {
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("statusCode")
    @Expose
    public String statusCode;

}
