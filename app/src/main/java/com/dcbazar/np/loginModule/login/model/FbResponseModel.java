package com.dcbazar.np.loginModule.login.model;

import com.ebpearls.choiceapp.base.BaseResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Sanjay on 3/3/2017.
 */

public class FbResponseModel extends BaseResponse{

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
        public Integer id;
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
        @SerializedName("is_profile_complete")
        @Expose
        public Integer isProfileComplete;

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
        public String incomes;

        @SerializedName("income_range")
        @Expose
        public IncomeRanges incomerange;
        @SerializedName("occupation")
        @Expose
        public Occupation occupation;
        @SerializedName("postcode")
        @Expose
        public String postcode;
        @SerializedName("fb_firstname")
        @Expose
        public String fbFirstname;
        @SerializedName("fb_lastname")
        @Expose
        public String fbLastname;
        @SerializedName("fb_avatarlink")
        @Expose
        public String fbAvatarlink;
        @SerializedName("fb_email")
        @Expose
        public String fbEmail;
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
