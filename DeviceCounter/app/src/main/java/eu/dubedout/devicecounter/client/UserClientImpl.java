package eu.dubedout.devicecounter.client;

import android.support.annotation.NonNull;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import eu.dubedout.devicecounter.architecture.Exception.UserDoesNotExistException;
import eu.dubedout.devicecounter.architecture.Exception.UsernameAlreadyTaken;
import eu.dubedout.devicecounter.architecture.ResponseHandler;
import eu.dubedout.devicecounter.business.bo.User;
import eu.dubedout.devicecounter.business.bo.UserParsedSpecific;

public class UserClientImpl implements UserClient {
    @Override
    public void login(String email, String password, ResponseHandler<User> responseHandler) {
        ParseUser.logInInBackground(email, password, logInCallback(responseHandler));
    }

    @NonNull
    private LogInCallback logInCallback(final ResponseHandler<User> responseHandler) {
        return new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (user != null) {
                    responseHandler.onSuccess(new UserParsedSpecific(user));
                } else if (userDoesNotExist(e)) {
                    responseHandler.onFailure(new UserDoesNotExistException(e.getMessage()));
                } else { // Generic error // TODO: VincentD 15-12-07 handle more specific errors
                    responseHandler.onFailure(new Throwable(e.getMessage()));
                }
            }
        };
    }

    private boolean userDoesNotExist(ParseException e) {
        return e != null && e.getCode() == ParseException.OBJECT_NOT_FOUND;
    }

    @Override
    public void signUp(String email, String password, final ResponseHandler<User> responseHandler) {
        final ParseUser parseUser = new ParseUser();
        parseUser.setUsername(email);
        parseUser.setEmail(email);
        parseUser.setPassword(password);
        parseUser.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    responseHandler.onSuccess(new UserParsedSpecific(parseUser));
                } else if (e.getCode() == ParseException.EMAIL_TAKEN
                        || e.getCode() == ParseException.USERNAME_TAKEN) {
                    responseHandler.onFailure(new UsernameAlreadyTaken(e.getMessage()));
                } else { // Generic Error // TODO: VincentD 15-12-07 handle more specific errors
                    responseHandler.onFailure(new Throwable(e.getMessage()));
                }
            }
        });
    }

    @Override
    public boolean isUserLoggedIn() {
        return ParseUser.getCurrentUser().isAuthenticated();
    }
}
