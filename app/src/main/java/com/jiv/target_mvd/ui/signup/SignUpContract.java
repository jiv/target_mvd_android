package com.jiv.target_mvd.ui.signup;

import com.jiv.target_mvd.base.Lifecycle;

public interface SignUpContract {

    interface View extends Lifecycle.View {

        void hideProgressDialog();

        void showMessage(String message);
    }

    interface ViewModel extends Lifecycle.ViewModel {

        void register();
    }
}
