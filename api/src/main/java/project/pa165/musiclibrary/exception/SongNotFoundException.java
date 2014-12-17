package project.pa165.musiclibrary.exception;

/**
 * @author Milan
 */
public class SongNotFoundException extends RuntimeException {

    public SongNotFoundException() {}

    public SongNotFoundException(String message) {
        super(message);
    }

    public SongNotFoundException(Throwable cause) {
        super(cause);
    }

    public SongNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
