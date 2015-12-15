package eu.dubedout.devicetracker.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import eu.dubedout.devicetracker.App;
import eu.dubedout.devicetracker.R;
import eu.dubedout.devicetracker.architecture.Const;
import eu.dubedout.devicetracker.business.PreferencesService;
import eu.dubedout.devicetracker.business.bo.Device;
import eu.dubedout.devicetracker.client.DeviceClient;
import eu.dubedout.devicetracker.client.UserClient;
import eu.dubedout.devicetracker.presenter.MainActivityPresenter;
import eu.dubedout.devicetracker.presenter.viewable.MainActivityViewable;
import eu.dubedout.devicetracker.view.adapter.DeviceAdapter;

// TODO: VincentD 15-10-20 get domain name (Filter by domain to display devices
public class MainActivity extends AppCompatActivity implements MainActivityViewable {
    // Views
    @Bind(R.id.activity_main_coordinator)
    CoordinatorLayout coordinatorLayout;
    @Bind(R.id.content_main_parent_layout)
    View mainContentParent;
    @Bind(R.id.content_main_register_new_device)
    Button buttonNewDevice;
    @Bind(R.id.content_main_register_new_user_wrapper)
    TextInputLayout registerNewUserWrapper;
    @Bind(R.id.content_main_register_new_user)
    EditText registerNewUser;
    @Bind(R.id.content_main_device_list)
    RecyclerView deviceRecyclerView;
    @Bind(R.id.content_main_loading_state)
    ProgressBar loadingProgress;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.content_main_error_text)
    TextView contentMainErrorText;
    @Bind(R.id.content_main_error_button)
    Button contentMainErrorButton;


    private MainActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        presenter = new MainActivityPresenter(this,
                App.getInstance(DeviceClient.class),
                App.getInstance(UserClient.class),
                App.getInstance(PreferencesService.class));

        initializeToolbar();
        initializeViews();

        presenter.onCreate(savedInstanceState);

    }

    private void initializeToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void initializeViews() {
        registerNewUser.setOnEditorActionListener(new OnNewUserKeyboardSend());
        deviceRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode) {
            case (Const.ForResult.REGISTER_DEVICE): {
                if (resultCode == RESULT_OK) {
                    presenter.onSuccessRegisteringDevice();
                } else {
                    presenter.onFailedRegisteringDevice();
                }
                break;
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            case R.id.action_login:
                launchLoginActivity();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void launchLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    public void launchDeviceRegistering() {
        Intent intent = new Intent(this, RegisterDeviceActivity.class);
        startActivityForResult(intent, Const.ForResult.REGISTER_DEVICE);
    }

    @Override
    public void showRegisterDeviceButton() {
        registerNewUserWrapper.setVisibility(View.GONE);
        buttonNewDevice.setVisibility(View.VISIBLE);
    }

    @Override
    public void showRegisterNewUserField() {
        registerNewUserWrapper.setVisibility(View.VISIBLE);
        buttonNewDevice.setVisibility(View.GONE);
    }

    @Override
    public void hideHeaderRegisteringFields() {
        registerNewUserWrapper.setVisibility(View.GONE);
        buttonNewDevice.setVisibility(View.GONE);
    }

    @Override
    public void showRegisteredDevicesList() {
        deviceRecyclerView.setVisibility(View.VISIBLE);
        loadingProgress.setVisibility(View.GONE);
    }

    @Override
    public void showLoadingData() {
        loadingProgress.setVisibility(View.VISIBLE);
        deviceRecyclerView.setVisibility(View.GONE);
        contentMainErrorButton.setVisibility(View.GONE);
        contentMainErrorText.setVisibility(View.GONE);
    }

    @Override
    public void showUserNotVerifiedHisEmail() {
        loadingProgress.setVisibility(View.GONE);

        contentMainErrorButton.setVisibility(View.VISIBLE);
        contentMainErrorText.setVisibility(View.VISIBLE);
    }

    @Override
    public void displaySentUserSuccess() {
        Snackbar.make(coordinatorLayout, R.string.new_user_sent_success, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void loadDevicesList(List<Device> deviceList) {
        deviceRecyclerView.setAdapter(new DeviceAdapter(deviceList));
    }

    @Override
    public void removeKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(registerNewUser.getWindowToken(), 0);
    }

    @Override
    public void clearEditText() {
        registerNewUser.setText("");
        registerNewUser.clearFocus();
    }


    @OnClick(R.id.content_main_error_button)
    void onRetryCheckingEmailVerificationClick() {
        presenter.onRetryEmailNotVerifiedButtonClick();
    }

    @OnClick(R.id.content_main_register_new_device)
    void onNewDeviceClick() {
        presenter.onNewDeviceClick();
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
