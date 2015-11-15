package eu.dubedout.devicecounter.helper;

public interface ResponseHandler {
    void onSuccess();
    void onFailure(Throwable throwable);
}
