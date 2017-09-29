package com.jiv.target_mvd.networking.signup;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ISignUpApi {

    @POST("users")
    Observable<SignUpResponse> signUp(@Body SignUpRequest request);
}
