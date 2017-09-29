package com.jiv.target_mvd.networking.managers;

import android.content.Context;

import com.jiv.target_mvd.data.AuthenticationManager;
import com.jiv.target_mvd.data.SharedPreferencesManager;
import com.jiv.target_mvd.networking.signup.SignUpApiService;
import com.jiv.target_mvd.networking.signup.SignUpRequest;
import com.jiv.target_mvd.networking.signup.UserRequest;
import com.jiv.target_mvd.networking.signup.exceptions.EmptyFieldsException;
import com.jiv.target_mvd.networking.signup.exceptions.InvalidEmailException;
import com.jiv.target_mvd.networking.signup.exceptions.PasswordNotMatchException;

import io.reactivex.Completable;

import static com.jiv.target_mvd.Utils.isValidEmail;

public class AuthenticationRequestManager {

    private static AuthenticationRequestManager instance;

    private SharedPreferencesManager privateSharedPreferencesManager;

    private SignUpApiService mSignUpApiService;
//    private LoginAPIService loginAPIService;
//    private UserDataRequestManager userDataRequestManager;

    private AuthenticationRequestManager(Context context) {
        privateSharedPreferencesManager = SharedPreferencesManager.getInstance(context);

        this.mSignUpApiService = new SignUpApiService(privateSharedPreferencesManager);
//        this.loginAPIService = new LoginAPIService(authenticationManager);

//        this.userDataRequestManager = UserDataRequestManager.getInstance(context);
    }

    public static AuthenticationRequestManager getInstance(Context context) {

        synchronized (AuthenticationRequestManager.class) {
            if (instance == null) {
                instance = new AuthenticationRequestManager(context);
            }

            return instance;
        }
    }

    public boolean isRequestingInformation() {

        return mSignUpApiService.isRequestingSignUp();
    }

    public Completable register() {

        return mSignUpApiService.register(createBodyForSignUp());
    }

//    public Observable<Object> login() {
//
//        return loginAPIService.login(createLoginRequest())
//                .flatMap(this::makeGetUserDataRequest);
//    }
//
//    private Observable<Object> makeLoginRequest(RegistrationResponse registrationResponse) {
//
//        return login();
//    }
//
//    private Observable<Object> makeGetUserDataRequest(LoginResponse loginResponse) {
//
//        return userDataRequestManager.getUserData();
//    }
//
//    private LoginRequest createLoginRequest() {
//
//        String nickname = privateSharedPreferencesManager.getUserNickname();
//        String password = authenticationManager.getPassword();
//
//        if (nickname == null || nickname.isEmpty() ||
//                password == null || password.isEmpty()) {
//            throw new LoginInternalException();
//        }
//
//        return new LoginRequest(nickname, password);
//    }

    private SignUpRequest createBodyForSignUp() {
        AuthenticationManager manager = AuthenticationManager.getInstance();

        String username = manager.getUsername();
        String email = manager.getEmail();
        String password = manager.getPassword();
        String password_confirmation = manager.getPasswordConfirmation();
        String gender = manager.getGender();

        if (username.isEmpty() || email.isEmpty() || password.isEmpty()
                || password_confirmation.isEmpty() || gender.isEmpty()) {
            throw new EmptyFieldsException();
        }else if (!isValidEmail(email)) {
            throw new InvalidEmailException();
        }else if (!password.equals(password_confirmation)) {
            throw new PasswordNotMatchException();
        }else {
            UserRequest userRequest = new UserRequest(username, email, password,
                    password_confirmation, gender);

            return new SignUpRequest(userRequest);
        }
    }
}
