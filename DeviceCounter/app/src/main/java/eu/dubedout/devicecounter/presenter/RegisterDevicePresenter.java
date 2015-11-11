package eu.dubedout.devicecounter.presenter;

import android.os.Bundle;

import eu.dubedout.devicecounter.App;
import eu.dubedout.devicecounter.activity.RegisterDeviceViewable;
import eu.dubedout.devicecounter.bo.Device;
import eu.dubedout.devicecounter.client.DeviceClient;
import eu.dubedout.devicecounter.helper.PreferencesHelper;

public class RegisterDevicePresenter {
    private RegisterDeviceViewable viewable;

    public void onCreate(Bundle savedInstanceState, RegisterDeviceViewable viewable) {
        // TODO: VincentD 15-10-27 save instance state, recover on rotation
        this.viewable = viewable;
    }

    public void sendButtonClick(String deviceLabelText, String deviceModelText) {
        registerNewDeviceBackend(deviceLabelText, deviceModelText);
        viewable.launchMainActivityForResult();
    }

    private void registerNewDeviceBackend(String deviceLabelText, String deviceModelText) {
        Device device = new Device(deviceLabelText, deviceModelText);
        App.getServiceRegistry()
                .getInstance(DeviceClient.class)
                .setNewDevice(device);
        App.getServiceRegistry()
                .getInstance(PreferencesHelper.class)
                .registerDevice(device);
    }
}
