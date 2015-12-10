package eu.dubedout.devicecounter.client;

import eu.dubedout.devicecounter.architecture.ResponseHandler;
import eu.dubedout.devicecounter.business.bo.User;

public interface UserClient {
    void login(String email, String password, ResponseHandler<User> responseHandler);

    void signUp(String email, String password, ResponseHandler<User> responseHandler);

    boolean isUserLoggedIn();
}
