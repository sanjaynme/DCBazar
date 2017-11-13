package com.dcbazar.np.signUpModule.model;

import com.dcbazar.np.base.BaseResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TermsPrivacyResponse extends BaseResponse {

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

        @SerializedName("page")
        @Expose
        private Page page;

        public Page getPage() {
            return page;
        }

        public void setPage(Page page) {
            this.page = page;
        }

    }

    public class Page {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("content")
        @Expose
        private String content;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

    }


}