package com.jiv.target_mvd.application;

import android.app.Application;
import android.content.Context;

import com.jiv.target_mvd.R;
import com.jiv.target_mvd.data.ResourcesManager;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class TargetMVD extends Application {
    private static Context sContext;

    private static final String DEFAULT_FONT_PATH = "fonts/OpenSans-Regular.ttf";

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();
        setDefaultFont();
        ResourcesManager.initialize(this);
    }

    public static Context getAppContext() {
        return sContext;
    }

    private void setDefaultFont() {
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath(DEFAULT_FONT_PATH)
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }
}
