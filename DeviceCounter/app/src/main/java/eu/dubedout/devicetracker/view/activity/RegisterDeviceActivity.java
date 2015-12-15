package eu.dubedout.devicetracker.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import eu.dubedout.devicetracker.App;
import eu.dubedout.devicetracker.R;
import eu.dubedout.devicetracker.business.PreferencesService;
import eu.dubedout.devicetracker.client.DeviceClient;
import eu.dubedout.devicetracker.presenter.RegisterDevicePresenter;
import eu.dubedout.devicetracker.presenter.viewable.RegisterDeviceViewable;

public class RegisterDeviceActivity extends AppCompatActivity implements RegisterDeviceViewable {

    @Bind(R.id.activity_register_device_label)
    EditText deviceLabel;
    @Bind(R.id.activity_register_device_model)
    EditText deviceModel;
    @Bind(R.id.activity_register_device_button_send)
    Button sendButton;

    private RegisterDevicePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_device);
        ButterKnife.bind(this);
        presenter = new RegisterDevicePresenter(
                App.getInstance(DeviceClient.class),
                App.getInstance(PreferencesService.class));

        initializeViews();

        presenter.onCreate(savedInstanceState, this);
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
