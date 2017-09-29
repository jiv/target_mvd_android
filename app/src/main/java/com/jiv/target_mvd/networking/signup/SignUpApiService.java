package com.jiv.target_mvd.networking.signup;

import com.jiv.target_mvd.data.SharedPreferencesManager;
import com.jiv.target_mvd.networking.signup.exceptions.SignUpTechFailureException;
import com.jiv.target_mvd.networking.signup.exceptions.UsernameAlreadyExistsException;
import com.jiv.target_mvd.services.RestAdapterFactory;

import java.net.HttpRetryException;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class SignUpApiService {

    private ISignUpApi mSignUpApi;
    private SharedPreferencesManager mSharedPreferencesManager;
    private boolean isRequestingSignUp;

    public SignUpApiService(SharedPreferencesManager sharedPreferencesManager) {

        this.mSignUpApi = RestAdapterFactory.create(ISignUpApi.class);
        mSharedPreferencesManager = sharedPreferencesManager;
    }

    public boolean isRequestingSignUp() {
        return isRequestingSignUp;
    }

    public Completable register(SignUpRequest request) {
        return mSignUpApi.signUp(request)
                .doOnSubscribe(disposable -> isRequestingSignUp = true)
                .doOnTerminate(() -> isRequestingSignUp = false)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .ignoreElements();
    }

    private Observable<SignUpResponse> handleSignUpError(Throwable throwable) {

        if (throwable instanceof HttpRetryException) {

            int status = ((HttpRetryException) throwable).responseCode();

            if (status == 401) {
                throw new UsernameAlreadyExistsException();
            } else {
                throw new SignUpTechFailureException();
            }

        } else {
            throw new SignUpTechFailureException();
        }
    }

    private void processSignUpResponse(SignUpResponse signUpResponse) {
        mSharedPreferencesManager.storeUserId(signUpResponse.getId());
    }
}
