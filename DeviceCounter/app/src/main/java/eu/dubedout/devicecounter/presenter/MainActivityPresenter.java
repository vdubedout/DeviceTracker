package eu.dubedout.devicecounter.presenter;

import android.os.Bundle;

import java.util.List;

import eu.dubedout.devicecounter.App;
import eu.dubedout.devicecounter.bo.Device;
import eu.dubedout.devicecounter.client.DeviceClient;
import eu.dubedout.devicecounter.helper.PreferencesHelper;
import eu.dubedout.devicecounter.helper.ResponseCallback;
import eu.dubedout.devicecounter.helper.ResponseHandler;
import eu.dubedout.devicecounter.helper.StringHelper;
import eu.dubedout.devicecounter.presenter.viewable.MainActivityViewable;

public class MainActivityPresenter {
    private MainActivityViewable viewable;

    public MainActivityPresenter(MainActivityViewable viewable) {
        this.viewable = viewable;
    }

    public void onCreate(Bundle savedInstanceState) {
        // TODO: VincentD 15-10-21 get savedInstanceState

        if (isDeviceRegistered()) {
            viewable.showContent();
            loadDeviceList();
        } else {
            viewable.launchDeviceRegistering();
        }
        // TODO: VincentD 15-10-21 get devices registered
        // TODO: VincentD 15-10-21 last launch one day ago, show registering new user
    }

    private void loadDeviceList() {
        App.getInstance(DeviceClient.class)
                .getDevices(new ResponseCallback<List<Device>>() {
                    @Override
                    public void onSuccess(List<Device> deviceList) {
                        viewable.loadDevicesList(deviceList);
                        viewable.showLoading(false);
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        // TODO: VincentD 15-11-14 handle error
                    }
                });
    }

    public void onSuccessRegisteringDevice() {
        viewable.showContent();
        // TODO: VincentD 15-11-12 display snack bar to let know the device is corretly setup
        // TODO: VincentD 15-11-10 display all devices registered under same email,
    }

    public void onFailedRegisteringDevice() {
        // TODO: VincentD 15-11-10 display error popup, send back to registering device
    }

    public boolean isDeviceRegistered() {
        return !App.getInstance(PreferencesHelper.class)
                .getDeviceRegistered()
                .isEmpty();
    }

    public void sendNewUser(String newUserName) {
        if (!StringHelper.isEmpty(newUserName)) {
            Device device = App.getInstance(PreferencesHelper.class).getDeviceRegistered();
            device.setUser(newUserName);
            App.getInstance(DeviceClient.class).setNewUser(device, new SetNewUserResponseHandler());
        }
    }

    private class SetNewUserResponseHandler implements ResponseHandler {
        @Override
        public void onSuccess() {
            viewable.removeFocusOnNewUserText();
            loadDeviceList();
        }

        @Override
        public void onFailure(Throwable throwable) {
            // TODO: VincentD 15-11-12 send user an error saying that it doesn't worked out,
            // TODO handle multiple parse exceptions
        }
    }
}
