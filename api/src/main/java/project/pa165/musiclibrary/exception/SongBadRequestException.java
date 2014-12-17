package project.pa165.musiclibrary.exception;

/**
 * @author Milan
 */
public class SongBadRequestException extends RuntimeException {

    public SongBadRequestException() {}

    public SongBadRequestException(String message) {
        super(message);
    }

    public SongBadRequestException(Throwable cause) {
        super(cause);
    }

    public SongBadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
