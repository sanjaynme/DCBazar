package com.dcbazar.np.loginModule.login.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Amir on 05/07/2017.
 */

//
public class FbResponseModels {

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
        public String income;
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
        @SerializedName("income_range")
        @Expose
        public IncomeRange incomeRange;

    }

    public class IncomeRange {

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

    public class Occupation {

        @SerializedName("id")
        @Expose
        public Integer id;
        @SerializedName("occupation")
        @Expose
        public String occupation;

    }


}

