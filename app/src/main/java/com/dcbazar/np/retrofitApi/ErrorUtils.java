package com.dcbazar.np.retrofitApi;


import com.dcbazar.np.base.BaseResponse;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ErrorUtils {

    public static BaseResponse parseError(Response<?> response, Retrofit retrofit) {
        Converter<ResponseBody, BaseResponse> converter =
                retrofit.responseBodyConverter(BaseResponse.class, new Annotation[0]);
        BaseResponse error;

        try {
            error = converter.convert(response.errorBody());
        } catch (IOException e) {
            return new BaseResponse();
        }
        return error;
    }
}
