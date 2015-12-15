package eu.dubedout.devicetracker.architecture;

public interface Handler<T> {
    void handle(T object);
}
