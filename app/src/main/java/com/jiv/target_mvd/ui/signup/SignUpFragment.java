package com.jiv.target_mvd.ui.signup;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.jiv.target_mvd.R;
import com.jiv.target_mvd.base.BaseFragment;
import com.jiv.target_mvd.base.Lifecycle;
import com.jiv.target_mvd.data.AuthenticationManager;
import com.jiv.target_mvd.data.ResourcesManager;
import com.jiv.target_mvd.networking.managers.AuthenticationRequestManager;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

import static com.jakewharton.rxbinding.widget.RxTextView.textChanges;
import static com.jiv.target_mvd.Utils.isValidEmail;

public class SignUpFragment extends BaseFragment implements SignUpContract.View {

    private SignUpContract.ViewModel mSignUpViewModel;

    private ProgressDialog progressDialog;
    private ResourcesManager mResourcesManager;

    private Subscription mUsernameSubscription;
    private Subscription mEmailSubscription;
    private Subscription mpasswordsSubscription;

    @BindView(R.id.et_username)
    EditText mUsernameEditText;
    @BindView(R.id.tv_username_error)
    TextView mUsernameErrorText;
    @BindView(R.id.et_email)
    EditText mEmailEditText;
    @BindView(R.id.tv_email_error)
    TextView mEmailErrorText;
    @BindView(R.id.et_password)
    EditText mPasswordEditText;
    @BindView(R.id.et_password_confirmation)
    EditText mPasswordConfirmationEditText;
    @BindView(R.id.tv_password_confirmation_error)
    TextView mPasswordConfirmationErrorText;
    @BindView(R.id.et_gender)
    EditText mGenderEditText;

    public static SignUpFragment newInstance() {
        return new SignUpFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AuthenticationRequestManager authenticationRequestManager =
                AuthenticationRequestManager.getInstance(getActivity().getApplicationContext());

        mSignUpViewModel = new SignUpViewModel(authenticationRequestManager);
        mResourcesManager = ResourcesManager.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);

        ButterKnife.bind(this, view);

        addFormValidations();

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    protected Lifecycle.ViewModel getViewModel() {
        return mSignUpViewModel;
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    @OnClick(R.id.button_sign_up)
    public void signUpButtonTap(View view) {
        String username = mUsernameEditText.getText().toString();
        String email = mEmailEditText.getText().toString();
        String password = mPasswordEditText.getText().toString();
        String password_confirmation = mPasswordConfirmationEditText.getText().toString();
        String gender = mGenderEditText.getText().toString();


        AuthenticationManager manager = AuthenticationManager.getInstance();

        manager.setUsername(mUsernameEditText.getText().toString());
        manager.setEmail(mEmailEditText.getText().toString());
        manager.setPassword(mPasswordEditText.getText().toString());
        manager.setPasswordConfirmation(mPasswordConfirmationEditText.getText().toString());
        manager.setGender(mGenderEditText.getText().toString());

        mSignUpViewModel.register();

        progressDialog = ProgressDialog.show(getActivity(), "Registering", "...", true);
    }

    private void addFormValidations() {
        mUsernameSubscription = textChanges(mUsernameEditText)
                .map(username -> username.toString().trim().length() > 0).skip(1)
                .subscribe(valid -> showError(valid,
                        mUsernameErrorText, mResourcesManager.getString(R.string.username_error)));

        mEmailSubscription = textChanges(mEmailEditText)
                .debounce(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .map(username -> isValidEmail(username.toString().trim()))
                .skip(1)
                .subscribe(valid -> showError(valid,
                        mEmailErrorText, mResourcesManager.getString(R.string.email_error)));

        Observable<CharSequence> passwordObservable =
                RxTextView.textChanges(mPasswordEditText).skip(1);
        Observable<CharSequence> passwordConfirmationObservable =
                RxTextView.textChanges(mPasswordConfirmationEditText).skip(1);

        mpasswordsSubscription = Observable.combineLatest(passwordObservable, passwordConfirmationObservable,
                (password, confirmPassword) ->
                        password.toString().equals(confirmPassword.toString())).skip(1)
        .subscribe(valid -> showError(valid,
                mPasswordConfirmationErrorText, mResourcesManager.getString(R.string.passwords_dont_match_error)));
    }

    private void showError(boolean valid, TextView errorTextView, String text) {
        if (!valid) {
            errorTextView.setVisibility(View.VISIBLE);
            errorTextView.setText(text);
        } else {
            errorTextView.setVisibility(View.GONE);
        }
    }
}
