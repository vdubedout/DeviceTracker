package eu.dubedout.devicetracker.architecture.Exception;

public class UserDoesNotExistException extends Exception {

    public UserDoesNotExistException(String message) {
        super(message);
    }
}
