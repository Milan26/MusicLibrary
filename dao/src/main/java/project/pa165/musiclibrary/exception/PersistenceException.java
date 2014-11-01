package project.pa165.musiclibrary.exception;

/**
 * @author Milan
 */
public class PersistenceException extends Exception {

    public PersistenceException() {

    }

    public PersistenceException(String message) {
        super(message);
    }

    public PersistenceException(Throwable cause) {
        super(cause);
    }

    public PersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
}
