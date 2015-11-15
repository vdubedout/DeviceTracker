package eu.dubedout.devicecounter.helper;

public interface ResponseCallback<T> {
    void onSuccess(T object);
    void onFailure(Throwable throwable);
}
