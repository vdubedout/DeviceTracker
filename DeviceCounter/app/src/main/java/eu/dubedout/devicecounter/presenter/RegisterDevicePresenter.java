package eu.dubedout.devicecounter.presenter;

import android.os.Bundle;

import eu.dubedout.devicecounter.architecture.ResponseCallback;
import eu.dubedout.devicecounter.business.PreferencesService;
import eu.dubedout.devicecounter.business.bo.Device;
import eu.dubedout.devicecounter.client.DeviceClient;
import eu.dubedout.devicecounter.helper.StringHelper;
import eu.dubedout.devicecounter.presenter.viewable.RegisterDeviceViewable;

public class RegisterDevicePresenter {
    private final DeviceClient deviceClient;
    private final PreferencesService preferencesService;
    private RegisterDeviceViewable viewable;

    public RegisterDevicePresenter(DeviceClient deviceClient, PreferencesService preferencesService) {
        this.deviceClient = deviceClient;
        this.preferencesService = preferencesService;
    }

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
        deviceClient.setNewDevice(device, new RegisterDeviceResponseCallback());
        preferencesService.registerDevice(device);
    }

    private class RegisterDeviceResponseCallback implements ResponseCallback {

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
