package eu.dubedout.devicecounter.client;

import android.support.annotation.NonNull;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import eu.dubedout.devicecounter.architecture.Exception.UserDoesNotExistException;
import eu.dubedout.devicecounter.architecture.Exception.UsernameAlreadyTaken;
import eu.dubedout.devicecounter.architecture.ResponseCallback;
import eu.dubedout.devicecounter.business.bo.User;
import eu.dubedout.devicecounter.business.bo.UserParsedSpecific;

public class UserClientImpl implements UserClient {
    @Override
    public void login(String email, String password, ResponseCallback<User> responseCallback) {
        ParseUser.logInInBackground(email, password, logInCallback(responseCallback));
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
    public void signUp(String email, String password, final ResponseCallback<User> responseCallback) {
        final ParseUser parseUser = new ParseUser();
        parseUser.setUsername(email);
        parseUser.setEmail(email);
        parseUser.setPassword(password);
        parseUser.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    responseCallback.onSuccess(new UserParsedSpecific(parseUser));
                } else if (e.getCode() == ParseException.EMAIL_TAKEN
                        || e.getCode() == ParseException.USERNAME_TAKEN){
                    responseCallback.onFailure(new UsernameAlreadyTaken(e.getMessage()));
                }
            }
        });
    }
}
