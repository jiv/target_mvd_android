package com.jiv.target_mvd.base;

import android.app.ProgressDialog;
import android.support.v4.app.Fragment;

public abstract class BaseFragment extends Fragment {

    protected ProgressDialog progressDialog;

    @Override
    public void onResume() {

        super.onResume();
        subscribeForNetworkRequests();
    }

    @Override
    public void onPause() {

        super.onPause();
        unsubscribeFromNetworkRequests();
    }

    protected void hideProgressDialog() {

        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    protected abstract void subscribeForNetworkRequests();
    protected abstract void unsubscribeFromNetworkRequests();
    protected abstract void reconnectWithNetworkRequests();

}
