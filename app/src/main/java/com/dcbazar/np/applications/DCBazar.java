package com.dcbazar.np.applications;

import android.app.Application;

import com.dcbazar.np.R;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by Dell on 11/13/2017.
 */

public class DCBazar extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //Define your default font using CalligraphyConfig
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath(getString(R.string.font_proxima_nova_black))
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }
}
