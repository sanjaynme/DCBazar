package com.dcbazar.np.base;

/**
 * Created by Sanjay on 31/05/2017.
 */

public interface BaseDatabaseCallback<T> {
    void onSuccess(T object);

    void onFailure(String message);
}
