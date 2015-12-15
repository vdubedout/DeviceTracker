package eu.dubedout.devicetracker.client;

import eu.dubedout.devicetracker.architecture.ResponseHandler;
import eu.dubedout.devicetracker.business.bo.User;

public interface UserClient {
    void login(String email, String password, ResponseHandler<User> responseHandler);

    void signUp(String email, String password, ResponseHandler<User> responseHandler);

    boolean isUserLoggedIn();

    boolean isUserVerifiedEmail();
}
