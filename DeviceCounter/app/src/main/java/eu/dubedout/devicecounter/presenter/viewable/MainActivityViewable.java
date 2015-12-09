package eu.dubedout.devicecounter.presenter.viewable;

import java.util.List;

import eu.dubedout.devicecounter.business.bo.Device;

public interface MainActivityViewable {

    void launchLoginActivity();

    void launchDeviceRegistering();
    void showRegisteringDeviceButton();
    void showContent();
    void loadDevicesList(List<Device> deviceList);
    void removeKeyboard();
    void clearEditText();

    void showSentUserSuccess();
}
