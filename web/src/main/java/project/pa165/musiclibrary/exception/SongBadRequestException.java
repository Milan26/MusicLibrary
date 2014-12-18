package project.pa165.musiclibrary.exception;

import org.springframework.validation.Errors;

/**
 * @author Milan
 */
public class SongBadRequestException extends RuntimeException {

    private Errors errors;

    public Errors getErrors() {
        return errors;
    }

    public SongBadRequestException(String message, Errors errors) {
        super(message);
        this.errors = errors;
    }
}
