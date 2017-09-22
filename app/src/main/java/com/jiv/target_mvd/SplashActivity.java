package com.jiv.target_mvd;

import android.content.Intent;
import android.os.Bundle;

import com.jiv.target_mvd.base.BaseActivity;

public class SplashActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        startActivity(new Intent(this, LandingActivity.class));
        finish();
    }
}
