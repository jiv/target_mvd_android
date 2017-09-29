package com.jiv.target_mvd.services;

import com.jiv.target_mvd.application.TargetMVD;
import com.jiv.target_mvd.data.SharedPreferencesManager;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

public class ResponseInterceptor implements Interceptor {
    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());

        prefer(response);

        return response;
    }

    private void prefer(Response response) {

        String accessToken = response.header(SharedPreferencesManager.ACCESS_TOKEN);
        String client = response.header(SharedPreferencesManager.CLIENT);
        String uid = response.header(SharedPreferencesManager.UID);

        if (preferValid(accessToken, client, uid)) {
            SharedPreferencesManager manager = SharedPreferencesManager
                    .getInstance(TargetMVD.getAppContext());

            manager.storeHeaders(accessToken, client, uid);
        }
    }

    private boolean preferValid(String accessToken, String client, String uid) {
        return accessToken != null && !accessToken.isEmpty() &&
                client != null && !client.isEmpty() &&
                uid != null && !uid.isEmpty();
    }
}
