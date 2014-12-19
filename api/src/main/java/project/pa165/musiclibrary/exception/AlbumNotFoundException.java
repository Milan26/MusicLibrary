package project.pa165.musiclibrary.exception;

/**
 * @author Milan
 */
public class AlbumNotFoundException extends RuntimeException {

    public AlbumNotFoundException() {}

    public AlbumNotFoundException(String message) {
        super(message);
    }
}
