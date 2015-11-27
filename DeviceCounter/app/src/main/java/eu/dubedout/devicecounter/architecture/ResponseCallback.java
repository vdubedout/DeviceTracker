package eu.dubedout.devicecounter.architecture;

public interface ResponseCallback<T> {
    void onSuccess(T object);
    void onFailure(Throwable throwable);
}
