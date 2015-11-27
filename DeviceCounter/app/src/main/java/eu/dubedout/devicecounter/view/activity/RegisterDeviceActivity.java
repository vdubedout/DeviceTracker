package eu.dubedout.devicecounter.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import eu.dubedout.devicecounter.R;
import eu.dubedout.devicecounter.presenter.RegisterDevicePresenter;
import eu.dubedout.devicecounter.presenter.viewable.RegisterDeviceViewable;

public class RegisterDeviceActivity extends AppCompatActivity implements RegisterDeviceViewable {

    @Bind(R.id.activity_register_device_label) EditText deviceLabel;
    @Bind(R.id.activity_register_device_model) EditText deviceModel;
    @Bind(R.id.activity_register_device_button_send) Button sendButton;

    private RegisterDevicePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_device);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ButterKnife.bind(this);

        presenter = new RegisterDevicePresenter();
        presenter.onCreate(savedInstanceState, this);

        initializeViews();

    }

    private void initializeViews() {
        deviceLabel.setOnKeyListener(new OnTextChangeButtonActivationCheck());
        deviceModel.setOnKeyListener(new OnTextChangeButtonActivationCheck());

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.sendButtonClick(
                        deviceLabel.getText().toString(),
                        deviceModel.getText().toString());
            }
        });
    }

    @Override
    public void launchMainActivityForResult() {
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void activateButtonClick() {
        sendButton.setEnabled(true);
    }

    @Override
    public void deactivateButtonClick() {
        sendButton.setEnabled(false);
    }

    private class OnTextChangeButtonActivationCheck implements View.OnKeyListener {
        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            presenter.verifyButtonState(deviceLabel.getText().toString(), deviceModel.getText().toString());
            return false;
        }
    }
}
