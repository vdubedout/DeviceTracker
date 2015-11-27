package eu.dubedout.devicecounter.client;

import eu.dubedout.devicecounter.architecture.ResponseCallback;

public class UserClientImpl implements UserClient {
    @Override
    public void login(String username, String password, ResponseCallback responseCallback) {
        responseCallback.onSuccess(new Throwable());
    }

    @Override
    public void signUp(String username, String password, String email, ResponseCallback responseCallback) {
        responseCallback.onSuccess(new Throwable());
    }
}
