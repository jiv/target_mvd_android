package com.jiv.target_mvd.networking;

import com.jiv.target_mvd.BuildConfig;
import com.jiv.target_mvd.networking.interceptors.AccessTokenInterceptor;
import com.jiv.target_mvd.networking.interceptors.HeadersInterceptor;
import com.jiv.target_mvd.networking.interceptors.ResponseInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFactory {
    private static final String URL_API = BuildConfig.API_URL;

    public static Retrofit build() {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new HeadersInterceptor())
                .addInterceptor(new AccessTokenInterceptor())
                .addInterceptor(new ResponseInterceptor())
                .build();

        return new Retrofit.Builder()
                .baseUrl(URL_API)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();
    }

    public static <T> T create(Class<T> klass) {
        return build().create(klass);
    }
}
