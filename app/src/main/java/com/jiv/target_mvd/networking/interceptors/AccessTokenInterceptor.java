package com.jiv.target_mvd.networking.interceptors;

import com.jiv.target_mvd.application.TargetMVD;
import com.jiv.target_mvd.data.SharedPreferencesManager;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AccessTokenInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();

        SharedPreferencesManager manager = SharedPreferencesManager.getInstance(TargetMVD.getAppContext());

        if (addHeaderValid(manager)) {
            builder
                    .addHeader(SharedPreferencesManager.ACCESS_TOKEN,
                            manager.getAccessToken())
                    .addHeader(SharedPreferencesManager.CLIENT,
                            manager.getClient())
                    .addHeader(SharedPreferencesManager.UID,
                            manager.getUid());
        }

        return chain.proceed(builder.build());
    }

    private boolean addHeaderValid(SharedPreferencesManager manager) {
        return !manager.getAccessToken().equalsIgnoreCase("") &&
                !manager.getClient().equalsIgnoreCase("") &&
                !manager.getUid().equalsIgnoreCase("");
    }
}
