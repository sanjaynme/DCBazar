package com.dcbazar.np.helpers;

/**
 * Created by sanjay on 7/17/2015.
 */
public class UrlHelper {

    public static String BASE_URL = "http://ci.draftserver.com/choiceapp/public/api/";
    public static String appFolder = "/sdcard/Mvp/";

    public final static String password_forgot = "password/forgot";
    public final static String change_password = "account/password";

    public static final String LOGIN = "auth/login";
    public static final String LOGOUT = "auth/logout";
    public static final String REGISTER = "auth/register";
    public static final String FACEBOOK_LOGIN = "auth/postLoginWithFacebook";
    public static final String UPDATE_PROFILE = "auth/updateProfile";
    public static final String POST_ADDUPDATEDEVICE_INFO = "auth/addUpdateDeviceInfo";
    public static final String CHANGE_PASSWORD = "auth/postChangePassword";
    public static final String GET_AUTHENTICATED_USER = "auth/getAuthenticatedUser";

    public static final String FORGOT_PASSWORD = "password/forgot";

    public static final String SYNC_SERVER = "survey/sync_survey";
    public static final String SYNC_SERVER_ANSWER = "survey/sync_survey_answer";
    public static final String GET_SURVEY_HISTORY = "survey/get_survey_history";
    public static final String SAVE_SURVEY_START_LOG = "survey/save_survey_start_log";
    public static final String GET_SURVEY_CLASSES = "survey/get_survey_classes";
    public static final String REPORT_LISTING = "survey/get_survey_report_list";
    public static final String GET_REPORT_DETAILS = "survey/get_survey_report";

    public static final String GET_INCOME_RANGE_LIST = "income-range/list";
    public static final String GET_OCCUPATION_LIST = "occupation/list";
    public static final String GET_TERMS_PRIVACY = "page/getPageDetails";

    public static final String SEND_FEEDBACK = "feedback/create";

    public static final String NOTIFICATION_LISTING = "notification/list";
    public static final String NOTIFICATION_DELETE = "notification/delete";




    public static class ResponseCodes {
        public static final String OTHER_FAILURES = "0000";
        public static final String SUCCESS = "200";
        public static final String FAILURE = "403";
        public static final String USER_NOT_FOUND = "404";
        public static final String INVALID_EMAIL_PASSWORD = "302";
        public static final String INVALID_CREDENTIALS = "401";
        public static final String CANNOT_CREATE_TOKEN = "500";
        public static final String INVALID_USERS = "404";

    }


}

