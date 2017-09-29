package com.jiv.target_mvd.base;

import android.support.annotation.CallSuper;

import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableMaybeObserver;

import static com.jiv.target_mvd.base.Constants.REQUEST_FAILED;
import static com.jiv.target_mvd.base.Constants.REQUEST_NONE;
import static com.jiv.target_mvd.base.Constants.REQUEST_RUNNING;
import static com.jiv.target_mvd.base.Constants.REQUEST_SUCCEEDED;

public abstract class NetworkViewModel {
    protected @Constants.RequestState
    int requestState;
    protected Throwable lastError;

    public abstract boolean isRequestingInformation();

    public NetworkViewModel() {

        this.requestState = REQUEST_NONE;
    }

    public @Constants.RequestState
    int getRequestState() {

        if (isRequestingInformation()) {
            return REQUEST_RUNNING;
        }

        return requestState;
    }

    public Throwable getLastError() {

        return lastError;
    }

    protected class MaybeNetworkObserver<T> extends DisposableMaybeObserver<T> {

        @Override
        @CallSuper
        public void onSuccess(T value) {

            requestState = REQUEST_SUCCEEDED;
        }

        @Override
        @CallSuper
        public void onError(Throwable e) {

            lastError = e;
            requestState = REQUEST_FAILED;
        }

        @Override
        public void onComplete() {

        }
    }

    protected class CompleatableNetworkObserver<T> extends DisposableCompletableObserver {
        @Override
        @CallSuper
        public void onError(Throwable e) {

            lastError = e;
            requestState = REQUEST_FAILED;
        }

        @Override
        @CallSuper
        public void onComplete() {
            requestState = REQUEST_SUCCEEDED;
        }
    }
}
