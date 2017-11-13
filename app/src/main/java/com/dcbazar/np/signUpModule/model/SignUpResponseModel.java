package com.dcbazar.np.signUpModule.model;

import com.dcbazar.np.base.BaseResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Amir on 07/04/2017.
 */

public class SignUpResponseModel extends BaseResponse {

    @SerializedName("results")
    @Expose
    public Results results;

    public class Results{

    }
}
