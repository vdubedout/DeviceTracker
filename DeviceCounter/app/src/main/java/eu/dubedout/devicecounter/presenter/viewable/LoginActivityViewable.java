package eu.dubedout.devicecounter.presenter.viewable;

public interface LoginActivityViewable {
    void launchMainActivityForResult();
    void activateLoginButtonClick();
    void deactivateLoginButtonClick();
    void displayErrorUserDoesNotExist();
    void displayRegisteringForm();
    void displaySignInForm();

    void displayErrorRegisteringEmailAlreadyExist();

    void displayGenericError(String message);
}
