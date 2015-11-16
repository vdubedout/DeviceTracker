package eu.dubedout.devicecounter.presenter;

import android.os.Bundle;

import eu.dubedout.devicecounter.App;
import eu.dubedout.devicecounter.activity.RegisterDeviceViewable;
import eu.dubedout.devicecounter.bo.Device;
import eu.dubedout.devicecounter.client.DeviceClient;
import eu.dubedout.devicecounter.helper.PreferencesHelper;
import eu.dubedout.devicecounter.helper.ResponseHandler;
import eu.dubedout.devicecounter.helper.StringHelper;

public class RegisterDevicePresenter {
    private RegisterDeviceViewable viewable;

    public void onCreate(Bundle savedInstanceState, RegisterDeviceViewable viewable) {
        // TODO: VincentD 15-10-27 save instance state, recover on rotation
        this.viewable = viewable;
    }

    public void sendButtonClick(String deviceLabelText, String deviceModelText) {
        registerNewDeviceBackend(deviceLabelText, deviceModelText);
    }

    public void verifyButtonState(String deviceId, String deviceModel) {
        if (StringHelper.isEmpty(deviceId) || StringHelper.isEmpty(deviceModel)) {
            viewable.deactivateButtonClick();
        } else {
            viewable.activateButtonClick();
        }
    }

    private void registerNewDeviceBackend(String deviceLabelText, String deviceModelText) {
        Device device = new Device(deviceLabelText, deviceModelText);
        App.getServiceRegistry()
                .getInstance(DeviceClient.class)
                .setNewDevice(device, new RegisterDeviceResponseHandler());
        App.getServiceRegistry()
                .getInstance(PreferencesHelper.class)
                .registerDevice(device);
    }

    private class RegisterDeviceResponseHandler implements ResponseHandler {

        @Override
        public void onSuccess() {
            viewable.launchMainActivityForResult();
        }

        @Override
        public void onFailure(Throwable throwable) {
            // TODO: VincentD 15-11-12 display an error, let user retry 
        }
    }
}
