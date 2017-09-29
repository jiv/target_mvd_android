package com.jiv.target_mvd.ui.signup;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.jiv.target_mvd.R;
import com.jiv.target_mvd.base.BaseFragment;
import com.jiv.target_mvd.base.Lifecycle;
import com.jiv.target_mvd.data.AuthenticationManager;
import com.jiv.target_mvd.networking.managers.AuthenticationRequestManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUpFragment extends BaseFragment implements SignUpContract.View {

    private SignUpContract.ViewModel mSignUpViewModel;

    private ProgressDialog progressDialog;

    @BindView(R.id.et_username)                 EditText mUsernameEditText;
    @BindView(R.id.et_email)                    EditText mEmailEditText;
    @BindView(R.id.et_password)                 EditText mPasswordEditText;
    @BindView(R.id.et_password_confirmation)    EditText mPasswordConfirmationEditText;
    @BindView(R.id.et_gender)                   EditText mGenderEditText;

    public static SignUpFragment newInstance() {
        return new SignUpFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AuthenticationRequestManager authenticationRequestManager =
                AuthenticationRequestManager.getInstance(getActivity().getApplicationContext());

        mSignUpViewModel = new SignUpViewModel(authenticationRequestManager);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);

        ButterKnife.bind(this, view);

        return view;
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
        AuthenticationManager manager = AuthenticationManager.getInstance();

        manager.setUsername(mUsernameEditText.getText().toString());
        manager.setEmail(mEmailEditText.getText().toString());
        manager.setPassword(mPasswordEditText.getText().toString());
        manager.setPasswordConfirmation(mPasswordConfirmationEditText.getText().toString());
        manager.setGender(mGenderEditText.getText().toString());

        mSignUpViewModel.register();

        progressDialog = ProgressDialog.show(getActivity(), "Registering", "...", true);
    }
}
