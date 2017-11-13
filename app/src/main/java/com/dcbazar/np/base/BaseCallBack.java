package com.dcbazar.np.base;

/**
 * Created by Sanjay on 03/04/2017.
 */

public interface BaseCallBack<T> {
    void onSuccess(T responseData);

    void onError(String message);

    void onFailure(String message);

    void onAuthenticationFailure(String message);

}
