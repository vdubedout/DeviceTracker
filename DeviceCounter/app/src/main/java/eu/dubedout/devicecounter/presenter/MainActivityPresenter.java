package eu.dubedout.devicecounter.presenter;

import android.os.Bundle;

import java.util.List;

import eu.dubedout.devicecounter.architecture.ResponseCallback;
import eu.dubedout.devicecounter.architecture.ResponseHandler;
import eu.dubedout.devicecounter.business.PreferencesService;
import eu.dubedout.devicecounter.business.bo.Device;
import eu.dubedout.devicecounter.client.DeviceClient;
import eu.dubedout.devicecounter.client.UserClient;
import eu.dubedout.devicecounter.helper.StringHelper;
import eu.dubedout.devicecounter.presenter.viewable.MainActivityViewable;

public class MainActivityPresenter {
    private final MainActivityViewable viewable;
    private final DeviceClient deviceClient;
    private final UserClient userClient;
    private final PreferencesService preferencesService;

    public MainActivityPresenter(MainActivityViewable viewable,
                                 DeviceClient deviceClient,
                                 UserClient userClient,
                                 PreferencesService preferencesService) {
        this.viewable = viewable;
        this.deviceClient = deviceClient;
        this.userClient = userClient;
        this.preferencesService = preferencesService;
    }

    public void onCreate(Bundle savedInstanceState) {
        // TODO: VincentD 15-10-21 get savedInstanceState

        if (!userClient.isUserLoggedIn()) {
            viewable.launchLoginActivity();
        } else if (!userClient.isUserVerifiedEmail()){
            viewable.showWarningNonVerifiedEmail();
        } else if (!isDeviceRegistered()) {
            viewable.showRegisteringDeviceButton();
        }

        loadDeviceList();

        // TODO: VincentD 15-10-21 last launch one day ago, show registering new user
    }

    public void registerNewDeviceClick() {
        viewable.launchDeviceRegistering();
    }

    public void onErrorButtonClick() {
        if (userClient.isUserVerifiedEmail()) {
            viewable.hideWarningNonVerifiedEmail();
        }
    }

    private void loadDeviceList() {
        deviceClient.getDevices(new ResponseHandler<List<Device>>() {
            @Override
            public void onSuccess(List<Device> deviceList) {
                viewable.loadDevicesList(deviceList);
                viewable.showContent();
            }

            @Override
            public void onFailure(Throwable throwable) {
            }
        });
    }

    public void onSuccessRegisteringDevice() {
        viewable.showRegisteringDeviceButton();
        loadDeviceList();
        // TODO: VincentD 15-11-12 display snack bar to let know the device is corretly setup
    }

    public void onFailedRegisteringDevice() {
        // TODO: VincentD 15-11-10 display error popup, send back to registering device
    }

    public boolean isDeviceRegistered() {
        return preferencesService
                .getDeviceRegistered()
                .isEmpty();
    }

    public void sendNewUser(String newUserName) {
        if (!StringHelper.isEmpty(newUserName)) {
            Device device = preferencesService.getDeviceRegistered();
            device.setUser(newUserName);
            deviceClient.setNewUser(device, new SetNewUserResponseCallback());
        }
        viewable.removeKeyboard();

    }

    private class SetNewUserResponseCallback implements ResponseCallback {
        @Override
        public void onSuccess() {
            viewable.clearEditText();
            viewable.showSentUserSuccess();
            loadDeviceList();
        }

        @Override
        public void onFailure(Throwable throwable) {
            // TODO: VincentD 15-11-12 send user an error saying that it doesn't worked out,
            // TODO handle multiple parse exceptions
        }
    }
}
