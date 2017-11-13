package com.dcbazar.np.base;

/**
 * Created by Nischal Manandhar on 01/05/2017.
 */

public interface BaseResponseListCallBack<DATA_TYPE> extends BaseCallBack<DATA_TYPE> {
    void onListEmpty(String message);
}
