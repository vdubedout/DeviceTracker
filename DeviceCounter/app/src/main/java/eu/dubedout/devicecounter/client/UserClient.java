package eu.dubedout.devicecounter.client;

import eu.dubedout.devicecounter.architecture.ResponseCallback;
import eu.dubedout.devicecounter.business.bo.User;

public interface UserClient {
    void login(String username, String password, ResponseCallback<User> responseCallback);

    void signUp(String username, String password, String email, ResponseCallback responseCallback);
}
