package com.jiv.target_mvd.ui.signup;

import android.support.annotation.NonNull;

import com.jiv.target_mvd.base.Constants;
import com.jiv.target_mvd.base.Lifecycle;
import com.jiv.target_mvd.base.NetworkViewModel;
import com.jiv.target_mvd.networking.managers.AuthenticationRequestManager;
import com.jiv.target_mvd.networking.signup.exceptions.EmptyFieldsException;
import com.jiv.target_mvd.networking.signup.exceptions.InvalidEmailException;
import com.jiv.target_mvd.networking.signup.exceptions.PasswordNotMatchException;
import com.jiv.target_mvd.networking.signup.exceptions.UsernameAlreadyExistsException;

import static com.jiv.target_mvd.base.Constants.REQUEST_FAILED;
import static com.jiv.target_mvd.base.Constants.REQUEST_SUCCEEDED;


public class SignUpViewModel extends NetworkViewModel implements SignUpContract.ViewModel{

    private SignUpContract.View mViewCallback;

    private AuthenticationRequestManager mAuthenticationRequestManager;

    public SignUpViewModel(AuthenticationRequestManager authenticationRequestManager) {
        mAuthenticationRequestManager = authenticationRequestManager;
    }

    @Override
    public void onViewResumed() {
        @Constants.RequestState int requestState = getRequestState();
        if (requestState == REQUEST_SUCCEEDED) {
            onSignUpCompleted();
        } else if (requestState == REQUEST_FAILED) {
            onSignUpError(getLastError());
        }
    }

    @Override
    public void onViewAttached(@NonNull Lifecycle.View viewCallback) {
        mViewCallback = (SignUpContract.View) viewCallback;
    }

    @Override
    public void onViewDetached() {
        mViewCallback = null;
    }

    @Override
    public void register() {
        mAuthenticationRequestManager.register()
                .subscribe(new RegistrationObserver());
    }

    @Override
    public boolean isRequestingInformation() {
        return mAuthenticationRequestManager.isRequestingInformation();
    }

    private void onSignUpCompleted() {

        if (mViewCallback != null) {
            mViewCallback.hideProgressDialog();
            mViewCallback.showMessage("SignUp Completed");
        }
    }

    private void onSignUpError(Throwable e) {

        if (mViewCallback != null) {
            mViewCallback.hideProgressDialog();

            if (e instanceof EmptyFieldsException) {
                mViewCallback.showMessage("There are empty fields");
            } else if (e instanceof InvalidEmailException) {
                mViewCallback.showMessage("The email is not valid");
            } else if (e instanceof PasswordNotMatchException) {
                mViewCallback.showMessage("Passwords don't match");
            } else if (e instanceof UsernameAlreadyExistsException) {
                mViewCallback.showMessage("Username has already been taken");
            } else {
                mViewCallback.showMessage("Registration Failure");
            }
        }
    }

    private class RegistrationObserver extends CompleatableNetworkObserver<Object> {

        @Override
        public void onComplete() {
            super.onComplete();
            onSignUpCompleted();
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            onSignUpError(e);
        }
    }
}
