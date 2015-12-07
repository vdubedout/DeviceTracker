package eu.dubedout.devicecounter.client;

import android.support.annotation.NonNull;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

import eu.dubedout.devicecounter.architecture.Exception.UserDoesNotExistException;
import eu.dubedout.devicecounter.architecture.ResponseCallback;
import eu.dubedout.devicecounter.business.bo.User;
import eu.dubedout.devicecounter.business.bo.UserParsedSpecific;

public class UserClientImpl implements UserClient {
    @Override
    public void login(String username, String password, ResponseCallback<User> responseCallback) {
        ParseUser.logInInBackground(username, password, logInCallback(responseCallback));
    }

    @NonNull
    private LogInCallback logInCallback(final ResponseCallback<User> responseCallback) {
        return new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (user != null) {
                    responseCallback.onSuccess(new UserParsedSpecific(user));
                } else if(userDoesNotExist(e)) {
                    responseCallback.onFailure(new UserDoesNotExistException(e.getMessage()));
                }
                // TODO: VincentD 15-12-07 handle wrong password
            }
        };
    }

    private boolean userDoesNotExist(ParseException e) {
        return e != null && e.getCode() == ParseException.OBJECT_NOT_FOUND;
    }

    @Override
    public void signUp(String username, String password, String email, ResponseCallback responseCallback) {
        responseCallback.onSuccess(new Throwable());
    }
}
