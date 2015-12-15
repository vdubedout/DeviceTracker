package eu.dubedout.devicecounter.architecture;

public interface ResponseCallback {
    void onSuccess();
    void onFailure(Throwable throwable);
}
