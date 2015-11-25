package eu.dubedout.devicecounter.presenter;

import android.os.Bundle;

import com.orhanobut.logger.Logger;

import eu.dubedout.devicecounter.BuildConfig;
import eu.dubedout.devicecounter.client.UserClient;
import eu.dubedout.devicecounter.helper.ResponseCallback;
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

    public void onLoginButtonClick(String username, String password) {
        if (!isEmpty(username) && !isEmpty(password)) {
            userClient.login(username, password, new ResponseCallback() {
                @Override
                public void onSuccess(Object object) {
                    if (BuildConfig.DEBUG) {
                        Logger.e("Always return successfully response, implement client");
                    }

                    // TODO: VincentD 15-11-24 navigate to main activity
                }

                @Override
                public void onFailure(Throwable throwable) {
                    // TODO: VincentD 15-11-24 display error
                }
            });
        }
    }
}
