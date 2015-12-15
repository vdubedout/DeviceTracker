package eu.dubedout.devicetracker.client;

import android.support.annotation.NonNull;

import com.orhanobut.logger.Logger;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import eu.dubedout.devicetracker.architecture.Exception.UserDoesNotExistException;
import eu.dubedout.devicetracker.architecture.Exception.UsernameAlreadyTaken;
import eu.dubedout.devicetracker.architecture.ResponseHandler;
import eu.dubedout.devicetracker.business.bo.User;
import eu.dubedout.devicetracker.business.bo.UserParsedSpecific;

public class UserClientImpl implements UserClient {
    private static final String EMAIL_VERIFIED = "emailVerified";

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
                    Logger.e("User does not exists : " + e.getMessage());
                    responseHandler.onFailure(new UserDoesNotExistException(e.getMessage()));
                } else { // Generic error // TODO: VincentD 15-12-07 handle more specific errors
                    Logger.e(e);
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
                    Logger.e(e);
                    responseHandler.onFailure(new UsernameAlreadyTaken(e.getMessage()));
                } else { // Generic Error // TODO: VincentD 15-12-07 handle more specific errors
                    Logger.e(e);
                    responseHandler.onFailure(new Throwable(e.getMessage()));
                }
            }
        });
    }

    @Override
    public boolean isUserLoggedIn() {
        return ParseUser.getCurrentUser() != null && ParseUser.getCurrentUser().isAuthenticated();
    }

    @Override
    public boolean isUserVerifiedEmail() {
        if (ParseUser.getCurrentUser() != null
                && ParseUser.getCurrentUser().getBoolean(EMAIL_VERIFIED)) {
            return true;
        } else if (ParseUser.getCurrentUser() != null
                && !ParseUser.getCurrentUser().getBoolean(EMAIL_VERIFIED)) {
            refreshCurrentUser();
            if (ParseUser.getCurrentUser().getBoolean(EMAIL_VERIFIED)) {
                return true;
            }
        }

        return false;
    }

    private void refreshCurrentUser() {
        try {
            ParseUser.getCurrentUser().fetch();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
