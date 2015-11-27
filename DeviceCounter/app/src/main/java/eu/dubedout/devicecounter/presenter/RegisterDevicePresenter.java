package eu.dubedout.devicecounter.presenter;

import android.os.Bundle;

import javax.inject.Inject;

import eu.dubedout.devicecounter.App;
import eu.dubedout.devicecounter.architecture.ResponseHandler;
import eu.dubedout.devicecounter.business.PreferencesService;
import eu.dubedout.devicecounter.business.bo.Device;
import eu.dubedout.devicecounter.client.DeviceClient;
import eu.dubedout.devicecounter.helper.StringHelper;
import eu.dubedout.devicecounter.presenter.viewable.RegisterDeviceViewable;

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

    @Inject PreferencesService preferencesService;
    private void registerNewDeviceBackend(String deviceLabelText, String deviceModelText) {
        Device device = new Device(deviceLabelText, deviceModelText);
        App.getInstance(DeviceClient.class)
                .setNewDevice(device, new RegisterDeviceResponseHandler());
        preferencesService.registerDevice(device);
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
