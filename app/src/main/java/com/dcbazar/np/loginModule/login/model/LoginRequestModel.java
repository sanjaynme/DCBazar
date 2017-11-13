package com.dcbazar.np.loginModule.login.model;

/**
 * Created by Sanjay on 3/2/2017.
 */

public class LoginRequestModel {

    public String email;
    public String password;
//    public String fb_token;

    @Override
    public String toString() {
        return "{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
//                ", fb_token='" + fb_token + '\'' +
                '}';
    }
}
