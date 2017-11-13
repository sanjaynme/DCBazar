package com.dcbazar.np.base;

/**
 * Created by Sanjay on 18/05/2017.
 */

import com.dcbazar.np.helpers.Contracts;
import com.dcbazar.np.helpers.UrlHelper;
import com.dcbazar.np.retrofitApi.ErrorUtils;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public abstract class RetrofitCallBack<DATA_TYPE extends BaseResponse> implements Callback<DATA_TYPE> {
    private BaseCallBack<DATA_TYPE> baseResponseCallBack;
    private Retrofit retrofit;

    public RetrofitCallBack(BaseCallBack<DATA_TYPE> baseResponseCallBack) {
        this.baseResponseCallBack = baseResponseCallBack;
    }

    public RetrofitCallBack(BaseCallBack<DATA_TYPE> baseResponseCallBack, Retrofit retrofit) {
        this.baseResponseCallBack = baseResponseCallBack;
        this.retrofit = retrofit;
    }

    @Override
    public void onResponse(Call<DATA_TYPE> call, Response<DATA_TYPE> response) {
        if (baseResponseCallBack != null) {
            if (response.isSuccessful()) {
                switch (response.body().getStatusCode()) {
                    case UrlHelper.ResponseCodes.SUCCESS:
                        if (modifyData(response.body()) != null) {
                            baseResponseCallBack.onSuccess(response.body());
                        }
                        break;

                    case UrlHelper.ResponseCodes.INVALID_EMAIL_PASSWORD:
                        baseResponseCallBack.onError(response.body().getMessage());
                        break;

                    case UrlHelper.ResponseCodes.CANNOT_CREATE_TOKEN:
                        baseResponseCallBack.onError(response.body().getMessage());
                        break;

                    case UrlHelper.ResponseCodes.INVALID_CREDENTIALS:
                        baseResponseCallBack.onError(response.body().getMessage());
                        break;

                    case UrlHelper.ResponseCodes.INVALID_USERS:
                        baseResponseCallBack.onError(response.body().getMessage());
                        break;

                    case UrlHelper.ResponseCodes.OTHER_FAILURES:
                        baseResponseCallBack.onError(response.body().getMessage());
                        break;

                    case UrlHelper.ResponseCodes.FAILURE:
                        baseResponseCallBack.onSuccess(response.body());
                        break;
                }
            } else {
                if (retrofit != null) {
                        BaseResponse baseResponse = ErrorUtils.parseError(response, retrofit);
                        baseResponseCallBack.onError(baseResponse.getMessage());
                    } else {
                        baseResponseCallBack.onError(Contracts.Errors.ERROR);
                }
            }
        }
    }

    @Override
    public void onFailure(Call<DATA_TYPE> call, Throwable t) {

        if (t != null && baseResponseCallBack != null) {
            if (t instanceof UnknownHostException) {
                baseResponseCallBack.onError(Contracts.Errors.NO_INTERNET_AVAILABLE);
            } else if (t instanceof SocketTimeoutException) {
                baseResponseCallBack.onError(Contracts.Errors.INTERNET_REQUEST_TIMEOUT);
            } else {
                baseResponseCallBack.onError(Contracts.Errors.ERROR);
            }
        }
    }

    /**
     * Modify the data from api as required
     *
     * @return data to be returned to presenter
     */
    public abstract DATA_TYPE modifyData(DATA_TYPE response);

}
