package eu.dubedout.devicecounter.helper;

public interface Callback<T> {
    void handle(T object);
}
