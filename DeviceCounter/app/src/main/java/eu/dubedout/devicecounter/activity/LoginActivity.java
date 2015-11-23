package eu.dubedout.devicecounter.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import butterknife.Bind;
import butterknife.ButterKnife;
import eu.dubedout.devicecounter.R;
import eu.dubedout.devicecounter.presenter.viewable.LoginActivityViewable;

public class LoginActivity extends AppCompatActivity implements LoginActivityViewable {
    @Bind(R.id.activity_display_toolbar) Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // TODO: VincentD 15-11-22 remove up button, everything should be disabled when the user is not logged in
    }

}
