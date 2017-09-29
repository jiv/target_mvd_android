package com.jiv.target_mvd.data;

public class AuthenticationManager {

    private static AuthenticationManager instance;

    private String mUsername;
    private String mEmail;
    private String mPassword;
    private String mPasswordConfirmation;
    private String mGender;
    private boolean userLogged;

    private AuthenticationManager() {

    }

    public static AuthenticationManager getInstance() {

        synchronized (AuthenticationManager.class) {
            if (instance == null) {
                instance = new AuthenticationManager();
            }

            return instance;
        }
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String mUsername) {
        this.mUsername = mUsername;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        this.mPassword = password;
    }

    public String getPasswordConfirmation() {
        return mPasswordConfirmation;
    }

    public void setPasswordConfirmation(String mPasswordConfirmation) {
        this.mPasswordConfirmation = mPasswordConfirmation;
    }

    public String getGender() {
        return mGender;
    }

    public void setGender(String mGender) {
        this.mGender = mGender;
    }

    public boolean isUserLogged() {

        return userLogged;
    }

    public void logUserIn() {

        userLogged = true;
    }
}
