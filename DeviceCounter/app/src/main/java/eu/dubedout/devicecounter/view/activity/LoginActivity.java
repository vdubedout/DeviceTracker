package eu.dubedout.devicecounter.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import eu.dubedout.devicecounter.R;
import eu.dubedout.devicecounter.architecture.Const;
import eu.dubedout.devicecounter.business.bo.User;
import eu.dubedout.devicecounter.client.UserClientImpl;
import eu.dubedout.devicecounter.presenter.LoginActivityPresenter;
import eu.dubedout.devicecounter.presenter.viewable.LoginActivityViewable;

public class LoginActivity extends AppCompatActivity implements LoginActivityViewable {
    @Bind(R.id.activity_login_coordinator) CoordinatorLayout coordinatorLayout;
    @Bind(R.id.activity_display_toolbar) Toolbar toolbar;
    @Bind(R.id.activity_login_email) EditText activityLoginEmail;
    @Bind(R.id.activity_login_email_wrapper) TextInputLayout activityLoginEmailWrapper;
    @Bind(R.id.activity_login_password) EditText activityLoginPassword;
    @Bind(R.id.activity_login_password_wrapper) TextInputLayout activityLoginPasswordWrapper;
    @Bind(R.id.activity_login_button_send) Button activityLoginButtonSend;
    @Bind(R.id.activity_login_button_swap_mode) Button activityLoginButtonNoAccount;

    private LoginActivityPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        presenter = new LoginActivityPresenter(this, new UserClientImpl());
        presenter.onCreate(savedInstanceState);

        initializeViews();
    }

    private void initializeViews() {
        activityLoginEmail.setOnKeyListener(new OnTextChangeButtonActivationCheck());
        activityLoginPassword.setOnKeyListener(new OnTextChangeButtonActivationCheck());

        activityLoginButtonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onSendButtonClick(
                        activityLoginEmail.getText().toString(),
                        activityLoginPassword.getText().toString());
            }
        });
    }

    @Override
    public void launchMainActivity(User user) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(Const.IntentExtra.USER, user);
        startActivity(intent);
        finish();
    }

    @Override
    public void activateLoginButtonClick() {
        activityLoginButtonSend.setEnabled(true);
    }

    @Override
    public void deactivateLoginButtonClick() {
        activityLoginButtonSend.setEnabled(false);
    }

    @Override
    public void displayErrorUserDoesNotExist() {
        showSnackBarError(getString(R.string.email_not_registered));
    }

    @Override
    public void displayRegisteringForm() {
        activityLoginButtonSend.setText(getString(R.string.action_create_account));
        activityLoginButtonNoAccount.setText(getString(R.string.already_an_account_login));
    }

    @Override
    public void displaySignInForm() {
        activityLoginButtonSend.setText(R.string.action_login);
        activityLoginButtonNoAccount.setText(R.string.no_account_create_it);
    }

    @Override
    public void displayErrorRegisteringEmailAlreadyExist() {
        showSnackBarError(getString(R.string.email_already_registered));
    }

    @Override
    public void displayGenericError(String message) {
        showSnackBarError(message);
    }

    private void showSnackBarError(String message) {
        Snackbar snackbar = Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_LONG);
        snackbar.getView().setBackgroundColor(getResources().getColor(R.color.snackbar_error));
        snackbar.show();
    }

    private class OnTextChangeButtonActivationCheck implements View.OnKeyListener {
        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            presenter.verifyButtonState(activityLoginEmail.getText().toString(),
                    activityLoginPassword.getText().toString());
            return false;
        }
    }
    
    @OnClick(R.id.activity_login_button_send)
    void onSendButtonClick() {
        presenter.onSendButtonClick(
                activityLoginEmail.getText().toString(),
                activityLoginPassword.getText().toString());
    }

    @OnClick(R.id.activity_login_button_swap_mode)
    void onSwapRegisteringAndSignIn() {
        presenter.onSwapRegisteringAndSignIn();
    }

}
