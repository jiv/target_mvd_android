package com.jiv.target_mvd.networking.signup;

import com.google.gson.annotations.SerializedName;

public class UserRequest {

    @SerializedName("email")                    private String mEmail;
    @SerializedName("password")                 private String mPassword;
    @SerializedName("password_confirmation")    private String mPasswordConfirmation;
    @SerializedName("username")                 private String mUsername;
    @SerializedName("gender")                   private String mGender;

    public UserRequest(String username,
                       String email,
                       String password,
                       String password_confirmation,
                       String gender) {
        mUsername = username;
        mEmail = email;
        mPassword = password;
        mPasswordConfirmation = password_confirmation;
        mGender = gender;
    }
}
