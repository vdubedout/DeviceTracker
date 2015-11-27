package eu.dubedout.devicecounter.architecture;

public interface ResponseHandler {
    void onSuccess();
    void onFailure(Throwable throwable);
}
