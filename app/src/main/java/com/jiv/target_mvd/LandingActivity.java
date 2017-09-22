package com.jiv.target_mvd;

import android.os.Bundle;

import com.jiv.target_mvd.base.BaseActivity;
import com.jiv.target_mvd.ui.signup.SignUpFragment;

public class LandingActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, SignUpFragment.newInstance()).commit();
    }
}
