package com.dcbazar.np.loginModule.forgotPassword.model;

import com.ebpearls.choiceapp.base.BaseResponse;

/**
 * Created by Sanjay on 4/5/2017.
 */

public class ForgetPasswordRequestModel extends BaseResponse {

   public String email;


   @Override

    public String toString() {
       return "{" +
               "email='" + email + '\'' +
//                ", fb_token='" + fb_token + '\'' +
               '}';
   }
}
