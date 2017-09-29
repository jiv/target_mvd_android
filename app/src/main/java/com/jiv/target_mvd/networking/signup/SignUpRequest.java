package com.jiv.target_mvd.networking.signup;

import com.google.gson.annotations.SerializedName;

public class SignUpRequest {
    @SerializedName("user") private UserRequest mUser;

    public SignUpRequest(UserRequest user) {
        mUser = user;
    }
}
