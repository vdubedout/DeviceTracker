package eu.dubedout.devicetracker.presenter.viewable;

import eu.dubedout.devicetracker.business.bo.User;

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
