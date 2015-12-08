package eu.dubedout.devicecounter.presenter;

import android.os.Bundle;
import android.support.annotation.NonNull;

import eu.dubedout.devicecounter.architecture.Exception.UserDoesNotExistException;
import eu.dubedout.devicecounter.architecture.Exception.UsernameAlreadyTaken;
import eu.dubedout.devicecounter.architecture.ResponseCallback;
import eu.dubedout.devicecounter.business.bo.User;
import eu.dubedout.devicecounter.client.UserClient;
import eu.dubedout.devicecounter.helper.StringHelper;
import eu.dubedout.devicecounter.presenter.viewable.LoginActivityViewable;

import static eu.dubedout.devicecounter.helper.StringHelper.isEmpty;

public class LoginActivityPresenter {
    public static boolean isLoginMode = true;
    private LoginActivityViewable viewable;
    private final UserClient userClient;

    public LoginActivityPresenter(LoginActivityViewable viewable, UserClient userClient) {
        this.viewable = viewable;
        this.userClient = userClient;
    }

    public void onCreate(Bundle savedInstanceState) {
        // TODO: VincentD 15-11-24 handle rotation
        // TODO: VincentD 15-11-24 handle savedInstanceState
    }

    public void verifyButtonState(String email, String userPassword) {
        if (StringHelper.isEmpty(email) || StringHelper.isEmpty(userPassword)) {
            viewable.deactivateLoginButtonClick();
        } else {
            viewable.activateLoginButtonClick();
        }
    }

    public void onSendButtonClick(String email, String password) {
        if (isFieldsNotEmpty(email, password) && isLoginMode) {
            userClient.login(email, password, getLoginResponseCallback());
        } else if (isFieldsNotEmpty(email, password) && !isLoginMode) {
            userClient.signUp(email, password, getSignUpAccountCallback());
        }
    }

    private boolean isFieldsNotEmpty(String email, String password) {
        return !isEmpty(email) && !isEmpty(password);
    }

    @NonNull
    private ResponseCallback<User> getLoginResponseCallback() {
        return new ResponseCallback<User>() {
            @Override
            public void onSuccess(User user) {
                viewable.launchMainActivityForResult(); // TODO: VincentD 15-11-24 navigate to main activity
            }

            @Override
            public void onFailure(Throwable throwable) {
                if (throwable instanceof UserDoesNotExistException) {
                    viewable.displayErrorUserDoesNotExist();
                } else {
                    viewable.displayGenericError(throwable.getMessage());
                }
            }
        };
    }

    public ResponseCallback<User> getSignUpAccountCallback() {
        return new ResponseCallback<User>() {
            @Override
            public void onSuccess(User object) {
                viewable.launchMainActivityForResult();
            }

            @Override
            public void onFailure(Throwable throwable) {
                if (throwable instanceof UsernameAlreadyTaken) {
                    viewable.displayErrorRegisteringEmailAlreadyExist();
                }
            }
        };
    }

    public void onSwapRegisteringAndSignIn() {
        isLoginMode = !isLoginMode;
        if (isLoginMode) {
            viewable.displaySignInForm();
        } else {
            viewable.displayRegisteringForm();
        }
    }
}
