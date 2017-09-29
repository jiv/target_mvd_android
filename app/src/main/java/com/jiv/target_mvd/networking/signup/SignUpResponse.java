package com.jiv.target_mvd.networking.signup;

import com.google.gson.annotations.SerializedName;

public class SignUpResponse {
    @SerializedName("id")           private String mId;
    @SerializedName("email")        private String mEmail;
    @SerializedName("provider")     private String mProvider;
    @SerializedName("uid")          private String mUid;
    @SerializedName("first_name")   private String mFirstName;
    @SerializedName("last_name")    private String mLastName;
    @SerializedName("gender")       private String mGender;
    @SerializedName("username")     private String mUsername;

    public String getId() {
        return mId;
    }

    public String getEmail() {
        return mEmail;
    }

    public String getProvider() {
        return mProvider;
    }

    public String getUid() {
        return mUid;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public String getGender() {
        return mGender;
    }

    public String getUsername() {
        return mUsername;
    }
}
