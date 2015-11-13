package eu.dubedout.devicecounter.presenter;

import android.os.Bundle;

import eu.dubedout.devicecounter.App;
import eu.dubedout.devicecounter.helper.PreferencesHelper;
import eu.dubedout.devicecounter.presenter.viewable.MainActivityViewable;

public class MainActivityPresenter {
    private MainActivityViewable viewable;

    public MainActivityPresenter(MainActivityViewable viewable) {
        this.viewable = viewable;
    }

    public void onCreate(Bundle savedInstanceState) {
        // TODO: VincentD 15-10-21 get savedInstanceState

        if (isDeviceRegistered()) {
            viewable.launchDeviceRegistering();
        } else {
            viewable.showContent();
        }
        // TODO: VincentD 15-10-21 get devices registered
        // TODO: VincentD 15-10-21 last launch one day ago, show registering new user
    }

    public void onSuccessRegisteringDevice() {
        // TODO: VincentD 15-11-12 display snack bar to let know the device is corretly setup
        // TODO: VincentD 15-11-10 display all devices registered under same email,
    }

    public void onFailedRegisteringDevice() {
        // TODO: VincentD 15-11-10 display error popup, send back to registering device
    }

    public boolean isDeviceRegistered() {
        return App.getServiceRegistry()
                .getInstance(PreferencesHelper.class)
                .getDeviceRegistered()
                .isEmpty();
    }

    public void sendNewUser(String newUserName) {

    }
}
