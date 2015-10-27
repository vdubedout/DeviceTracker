package eu.dubedout.devicecounter.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import eu.dubedout.devicecounter.R;
import eu.dubedout.devicecounter.presenter.RegisterDevicePresenter;

public class RegisterDeviceActivity extends AppCompatActivity {

    private RegisterDevicePresenter presenter;
    private EditText deviceLabel;
    private EditText deviceModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_device);

        presenter = new RegisterDevicePresenter();
        presenter.onCreate(savedInstanceState);

        initializeViews();
    }

    private void initializeViews() {
        deviceLabel = (EditText) this.findViewById(R.id.activity_register_device_label);
        deviceModel = (EditText) this.findViewById(R.id.activity_register_device_device_model);

        Button sendButton = (Button) this.findViewById(R.id.activity_register_device_button_send);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.sendButtonClick(deviceLabel.getText(), deviceModel.getText());
            }
        });
    }


}
