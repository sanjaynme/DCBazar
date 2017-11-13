package com.dcbazar.np.loginModule.login.model;

import com.dcbazar.np.base.BaseResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Sanjay on 3/2/2017.
 */

public class LoginResponseModel extends BaseResponse {

    @SerializedName("results")
    @Expose
    public Results results;

    public class Results {

        @SerializedName("token")
        @Expose
        public String token;
        @SerializedName("user")
        @Expose
        public User user;

        @SerializedName("fb_detail")
        @Expose
        public FbDetail fbDetail;
    }
    public class FbDetail {

        @SerializedName("first_name")
        @Expose
        public String firstName;
        @SerializedName("last_name")
        @Expose
        public String lastName;
        @SerializedName("email")
        @Expose
        public String email;
        @SerializedName("fb_id")
        @Expose
        public String fbId;

    }


    public class User {

        @SerializedName("id")
        @Expose
        public int id;
        @SerializedName("name")
        @Expose
        public String name;
        @SerializedName("email")
        @Expose
        public String email;
        @SerializedName("active")
        @Expose
        public Integer active;
        @SerializedName("last_logged_in_on")
        @Expose
        public String lastLoggedInOn;
        @SerializedName("created_at")
        @Expose
        public String createdAt;
        @SerializedName("is_profile_complete")
        @Expose
        public int isProfileComplete;
        @SerializedName("updated_at")
        @Expose
        public String updatedAt;
        @SerializedName("first_login")
        @Expose
        public Integer firstLogin;
        @SerializedName("profile")
        @Expose
        public Profile profile;
    }

    public class Profile {

        @SerializedName("firstname")
        @Expose
        public String firstname;
        @SerializedName("lastname")
        @Expose
        public String lastname;
        @SerializedName("age")
        @Expose
        public Integer age;
        @SerializedName("gender")
        @Expose
        public String gender;
        @SerializedName("income")
        @Expose
        public String income;
        @SerializedName("occupation")
        @Expose
        public Occupation occupation;
        @SerializedName("postcode")
        @Expose
        public String postcode;
        @SerializedName("income_range")
        @Expose
        public IncomeRanges incomeRange=null;
    }

    public class IncomeRanges {

        @SerializedName("id")
        @Expose
        public Integer id;
        @SerializedName("min")
        @Expose
        public Integer min;
        @SerializedName("max")
        @Expose
        public Integer max;
    }

    public class Occupation{
        @SerializedName("id")
        @Expose
        public Integer id;
        @SerializedName("occupation")
        @Expose
        public String occupation;
    }

}