package eu.dubedout.devicecounter.client;

import eu.dubedout.devicecounter.architecture.ResponseCallback;

public interface UserClient {
    void login(String username, String password, ResponseCallback responseCallback);

    void signUp(String username, String password, String email, ResponseCallback responseCallback);
}
