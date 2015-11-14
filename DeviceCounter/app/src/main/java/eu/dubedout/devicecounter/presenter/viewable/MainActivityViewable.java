package eu.dubedout.devicecounter.presenter.viewable;

import java.util.List;

import eu.dubedout.devicecounter.bo.Device;

public interface MainActivityViewable {

    void launchDeviceRegistering();

    void showContent();
//    void showLoading();
//    void showError();

    void loadDevicesList(List<Device> deviceList);

    void removeFocusOnNewUserText();
}
