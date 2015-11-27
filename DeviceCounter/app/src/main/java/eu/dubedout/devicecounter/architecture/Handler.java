package eu.dubedout.devicecounter.architecture;

public interface Handler<T> {
    void handle(T object);
}
