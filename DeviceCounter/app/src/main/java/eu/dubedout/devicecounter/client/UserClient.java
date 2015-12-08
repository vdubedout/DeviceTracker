package eu.dubedout.devicecounter.client;

import eu.dubedout.devicecounter.architecture.ResponseCallback;
import eu.dubedout.devicecounter.business.bo.User;

public interface UserClient {
    void login(String email, String password, ResponseCallback<User> responseCallback);

    void signUp(String email, String password, ResponseCallback<User> responseCallback);
}
