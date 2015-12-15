package eu.dubedout.devicetracker.architecture;

public interface ResponseHandler<T> {
    void onSuccess(T object);

    void onFailure(Throwable throwable);
}
