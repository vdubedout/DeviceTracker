package eu.dubedout.devicecounter.presenter.viewable;

public interface MainActivityViewable {

    void launchDeviceRegistering();

    void showContent();
//    void showLoading();
//    void showError();

    void loadDevicesList();

    void removeFocusOnNewUserText();
}
