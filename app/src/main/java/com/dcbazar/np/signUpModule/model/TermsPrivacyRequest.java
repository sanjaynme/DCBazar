package com.dcbazar.np.signUpModule.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Amir on 30/06/2017.
 */

public class TermsPrivacyRequest {


    @SerializedName("page_id")
    @Expose
    private String pageId;

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }
}
