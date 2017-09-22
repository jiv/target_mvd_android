package com.jiv.target_mvd.ui.signup;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jiv.target_mvd.R;
import com.jiv.target_mvd.base.BaseFragment;

public class SignUpFragment extends BaseFragment {

    public static SignUpFragment newInstance() {
        return new SignUpFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }

    @Override
    protected void subscribeForNetworkRequests() {

    }

    @Override
    protected void unsubscribeFromNetworkRequests() {

    }

    @Override
    protected void reconnectWithNetworkRequests() {

    }
}
