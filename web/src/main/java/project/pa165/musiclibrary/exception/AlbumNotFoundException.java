package project.pa165.musiclibrary.exception;

/**
 * @author Milan
 */
public class AlbumNotFoundException extends RuntimeException {
    public AlbumNotFoundException() {}

    public AlbumNotFoundException(String message) {
        super(message);
    }

    public AlbumNotFoundException(Throwable cause) {
        super(cause);
    }

    public AlbumNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
