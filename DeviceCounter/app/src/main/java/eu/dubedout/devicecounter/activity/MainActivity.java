package eu.dubedout.devicecounter.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import eu.dubedout.devicecounter.R;
import eu.dubedout.devicecounter.helper.Const;
import eu.dubedout.devicecounter.presenter.MainActivityPresenter;
import eu.dubedout.devicecounter.presenter.viewable.MainActivityViewable;

// TODO: VincentD 15-10-20 get list of devices and registered names
// TODO: VincentD 15-10-20 register device
// TODO: VincentD 15-10-20 get domain name (Filter by domain to display devices
public class MainActivity extends AppCompatActivity implements MainActivityViewable{
    // Views
    @Bind(R.id.content_main_register_new_user_wrapper) TextInputLayout registerNewUserWrapper;
    @Bind(R.id.content_main_register_new_user) EditText registerNewUser;
    @Bind(R.id.content_main_warning_not_registered_email) TextView warningNotRegisteredDevice;
    private MainActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initializeToolbar();
        initializeViews();

        presenter = new MainActivityPresenter(this);
        presenter.onCreate(savedInstanceState);
    }

    private void initializeToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void initializeViews() {
        registerNewUser.setOnEditorActionListener(new OnNewUserKeyboardSend());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void launchDeviceRegistering() {
        Intent intent = new Intent(this, RegisterDeviceActivity.class);
        startActivityForResult(intent, Const.ForResult.REGISTER_DEVICE);
    }

    @Override
    public void showContent() {
        registerNewUserWrapper.setVisibility(View.VISIBLE);
        warningNotRegisteredDevice.setVisibility(View.GONE);
    }

    @Override
    public void loadDevicesList() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Const.ForResult.REGISTER_DEVICE) {
            if (resultCode == RESULT_OK) {
                presenter.onSuccessRegisteringDevice();
            } else {
                presenter.onFailedRegisteringDevice();
            }
        }
    }

    private class OnNewUserKeyboardSend implements TextView.OnEditorActionListener {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if (actionId == EditorInfo.IME_ACTION_SEND) {
                presenter.sendNewUser(registerNewUser.getText().toString());
                return true;
            }
            return false;
        }
    }
}
