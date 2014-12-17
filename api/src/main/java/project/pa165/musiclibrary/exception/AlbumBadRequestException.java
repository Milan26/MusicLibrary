package project.pa165.musiclibrary.exception;

/**
 * @author Milan
 */
public class AlbumBadRequestException extends RuntimeException {

    public AlbumBadRequestException() {}

    public AlbumBadRequestException(String message) {
        super(message);
    }

    public AlbumBadRequestException(Throwable cause) {
        super(cause);
    }

    public AlbumBadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
