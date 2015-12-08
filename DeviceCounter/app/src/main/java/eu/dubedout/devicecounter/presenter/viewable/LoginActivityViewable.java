package eu.dubedout.devicecounter.presenter.viewable;

public interface LoginActivityViewable {
    void launchMainActivityForResult();
    void activateLoginButtonClick();
    void deactivateLoginButtonClick();
    void displayErrorUserDoesNotExist();
    void displaySignUpForm();

    void displayErrorRegisteringEmailAlreadyExist();
    void displayGenericError(String message);
}
