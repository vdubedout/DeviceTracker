package eu.dubedout.devicecounter.architecture.Exception;

public class UserDoesNotExistException extends Exception {

    public UserDoesNotExistException(String message) {
        super(message);
    }
}
