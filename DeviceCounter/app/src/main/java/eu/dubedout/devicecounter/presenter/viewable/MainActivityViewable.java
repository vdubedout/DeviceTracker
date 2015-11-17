package eu.dubedout.devicecounter.presenter.viewable;

import java.util.List;

import eu.dubedout.devicecounter.bo.Device;

public interface MainActivityViewable {

    void launchDeviceRegistering();
    void showNewUserRegisteringBox();
    void showContent();
    void loadDevicesList(List<Device> deviceList);
    void removeKeyboard();
    void clearEditText();

    void showSentUserSuccess();
}
