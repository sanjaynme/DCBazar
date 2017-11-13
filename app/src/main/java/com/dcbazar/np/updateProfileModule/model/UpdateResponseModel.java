package com.dcbazar.np.updateProfileModule.model;

import com.dcbazar.np.base.BaseResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateResponseModel extends BaseResponse {

    @SerializedName("results")
    @Expose
    private Results results;

    public Results getResults() {
        return results;
    }

    public void setResults(Results results) {
        this.results = results;
    }

    public class Results {

        @SerializedName("user")
        @Expose
        private User user;

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

    }

    public class User {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("active")
        @Expose
        private Integer active;
        @SerializedName("last_logged_in_on")
        @Expose
        private String lastLoggedInOn;
        @SerializedName("is_profile_complete")
        @Expose
        private Integer isProfileComplete;
        @SerializedName("profile")
        @Expose
        private Profile profile;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public Integer getActive() {
            return active;
        }

        public void setActive(Integer active) {
            this.active = active;
        }

        public String getLastLoggedInOn() {
            return lastLoggedInOn;
        }

        public void setLastLoggedInOn(String lastLoggedInOn) {
            this.lastLoggedInOn = lastLoggedInOn;
        }

        public Integer getIsProfileComplete() {
            return isProfileComplete;
        }

        public void setIsProfileComplete(Integer isProfileComplete) {
            this.isProfileComplete = isProfileComplete;
        }

        public Profile getProfile() {
            return profile;
        }

        public void setProfile(Profile profile) {
            this.profile = profile;
        }

    }

    public class Profile {

        @SerializedName("firstname")
        @Expose
        private String firstname;
        @SerializedName("lastname")
        @Expose
        private String lastname;
        @SerializedName("age")
        @Expose
        private Integer age;
        @SerializedName("gender")
        @Expose
        private String gender;
        @SerializedName("income")
        @Expose
        private String income;
        @SerializedName("postcode")
        @Expose
        private String postcode;
        @SerializedName("fb_firstname")
        @Expose
        private String fbFirstname;
        @SerializedName("fb_lastname")
        @Expose
        private String fbLastname;
        @SerializedName("fb_avatarlink")
        @Expose
        private String fbAvatarlink;
        @SerializedName("fb_email")
        @Expose
        private String fbEmail;

        public Occupation getOccupations() {
            return occupations;
        }

        public void setOccupations(Occupation occupations) {
            this.occupations = occupations;
        }

        @SerializedName("occupation")
        private Occupation occupations;

        public IncomeRanges getIncomeRange() {
            return incomeRange;
        }

        public void setIncomeRange(IncomeRanges incomeRange) {
            this.incomeRange = incomeRange;
        }

        @SerializedName("income_range")
        @Expose
        private IncomeRanges incomeRange;

        public String getFirstname() {
            return firstname;
        }

        public void setFirstname(String firstname) {
            this.firstname = firstname;
        }

        public String getLastname() {
            return lastname;
        }

        public void setLastname(String lastname) {
            this.lastname = lastname;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getIncome() {
            return income;
        }

        public void setIncome(String income) {
            this.income = income;
        }

        public String getPostcode() {
            return postcode;
        }

        public void setPostcode(String postcode) {
            this.postcode = postcode;
        }

        public String getFbFirstname() {
            return fbFirstname;
        }

        public void setFbFirstname(String fbFirstname) {
            this.fbFirstname = fbFirstname;
        }

        public String getFbLastname() {
            return fbLastname;
        }

        public void setFbLastname(String fbLastname) {
            this.fbLastname = fbLastname;
        }

        public String getFbAvatarlink() {
            return fbAvatarlink;
        }

        public void setFbAvatarlink(String fbAvatarlink) {
            this.fbAvatarlink = fbAvatarlink;
        }

        public String getFbEmail() {
            return fbEmail;
        }

        public void setFbEmail(String fbEmail) {
            this.fbEmail = fbEmail;
        }

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

    public class Occupation {
        @SerializedName("id")
        @Expose
        public Integer id;
        @SerializedName("occupation")
        @Expose
        public String occupation;
    }
}