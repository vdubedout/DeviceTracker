package eu.dubedout.devicecounter.activity;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import eu.dubedout.devicecounter.R;
import eu.dubedout.devicecounter.client.UserClientImpl;
import eu.dubedout.devicecounter.presenter.LoginActivityPresenter;
import eu.dubedout.devicecounter.presenter.viewable.LoginActivityViewable;

public class LoginActivity extends AppCompatActivity implements LoginActivityViewable {
    @Bind(R.id.activity_display_toolbar) Toolbar toolbar;
    @Bind(R.id.activity_login_username) EditText activityLoginUsername;
    @Bind(R.id.activity_login_username_wrapper) TextInputLayout activityLoginUsernameWraper;
    @Bind(R.id.activity_login_password) EditText activityLoginPassword;
    @Bind(R.id.activity_login_password_wrapper) TextInputLayout activityLoginPasswordWraper;
    @Bind(R.id.activity_login_button_send) Button activityLoginButtonSend;

    private LoginActivityPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // TODO: VincentD 15-11-22 remove up button, everything should be disabled when the user is not logged in

        presenter = new LoginActivityPresenter(this, new UserClientImpl());
        presenter.onCreate(savedInstanceState);
    }

    @OnClick(R.id.activity_login_button_send)
    void onLoginButtonClick() {
        presenter.onLoginButtonClick(
                activityLoginUsername.getText().toString(),
                activityLoginPassword.getText().toString());
    }

}
