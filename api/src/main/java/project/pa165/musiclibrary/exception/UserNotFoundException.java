package project.pa165.musiclibrary.exception;

/**
 * @author Milan
 */
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {}

    public UserNotFoundException(String message) {
        super(message);
    }
}
