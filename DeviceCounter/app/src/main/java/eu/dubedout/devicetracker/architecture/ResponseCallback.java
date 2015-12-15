package eu.dubedout.devicetracker.architecture;

public interface ResponseCallback {
    void onSuccess();

    void onFailure(Throwable throwable);
}
