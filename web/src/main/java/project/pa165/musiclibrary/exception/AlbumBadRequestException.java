package project.pa165.musiclibrary.exception;

import org.springframework.validation.Errors;

/**
 * @author Milan
 */
public class AlbumBadRequestException extends RuntimeException {

    private Errors errors;

    public Errors getErrors() {
        return errors;
    }

    public AlbumBadRequestException(String message, Errors errors) {
        super(message);
        this.errors = errors;
    }
}
