package eu.dubedout.devicecounter.presenter;

import android.os.Bundle;

import com.orhanobut.logger.Logger;

import eu.dubedout.devicecounter.BuildConfig;
import eu.dubedout.devicecounter.architecture.Exception.UserDoesNotExistException;
import eu.dubedout.devicecounter.architecture.ResponseCallback;
import eu.dubedout.devicecounter.business.bo.User;
import eu.dubedout.devicecounter.client.UserClient;
import eu.dubedout.devicecounter.helper.StringHelper;
import eu.dubedout.devicecounter.presenter.viewable.LoginActivityViewable;

import static eu.dubedout.devicecounter.helper.StringHelper.isEmpty;

public class LoginActivityPresenter {
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

    public void verifyButtonState(String username, String userpassword) {
        if (StringHelper.isEmpty(username) || StringHelper.isEmpty(userpassword)) {
            viewable.deactivateLoginButtonClick();
        } else {
            viewable.activateLoginButtonClick();
        }
    }
    public void onLoginButtonClick(String username, String password) {
        if (!isEmpty(username) && !isEmpty(password)) {
            userClient.login(username, password, new ResponseCallback<User>() {
                @Override
                public void onSuccess(User user) {
                    if (BuildConfig.DEBUG) {
                        Logger.e("Always return successfully response, implement client");
                    }
                    viewable.launchMainActivityForResult(); // TODO: VincentD 15-11-24 navigate to main activity
                }

                @Override
                public void onFailure(Throwable throwable) {
                    if (throwable instanceof UserDoesNotExistException) {
                        viewable.displayErrorUserDoesNotExist();
                    }
                }
            });
        }
    }
}
