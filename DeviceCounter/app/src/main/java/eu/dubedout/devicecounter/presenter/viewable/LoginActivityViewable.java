package eu.dubedout.devicecounter.presenter.viewable;

import eu.dubedout.devicecounter.business.bo.User;

public interface LoginActivityViewable {
    void launchMainActivity(User user);
    void activateLoginButtonClick();
    void deactivateLoginButtonClick();
    void displayErrorUserDoesNotExist();
    void displayRegisteringForm();
    void displaySignInForm();
    void displayErrorRegisteringEmailAlreadyExist();
    void displayGenericError(String message);
}
