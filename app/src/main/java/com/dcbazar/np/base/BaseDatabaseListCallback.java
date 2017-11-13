package com.dcbazar.np.base;

/**
 * Created by Sanjay on 31/05/2017.
 */

public interface BaseDatabaseListCallback<T> extends BaseCallBack<T> {
    void onListEmpty(String message);
}
