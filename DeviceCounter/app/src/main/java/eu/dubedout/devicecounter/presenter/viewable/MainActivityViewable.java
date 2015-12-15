package eu.dubedout.devicecounter.presenter.viewable;

import java.util.List;

import eu.dubedout.devicecounter.business.bo.Device;

public interface MainActivityViewable {

    void launchLoginActivity();
    void launchDeviceRegistering();

    ///// LAYOUTS MODE VISIBILITY
    // Header
    void showRegisterDeviceButton();
    void showRegisterNewUserField();
    void hideHeaderRegisteringFields();
    // Main
    void showLoadingData();
    void showRegisteredDevicesList();
    void showUserNotVerifiedHisEmail();



    void displaySentUserSuccess();
    void loadDevicesList(List<Device> deviceList);
    void removeKeyboard();
    void clearEditText();


}
