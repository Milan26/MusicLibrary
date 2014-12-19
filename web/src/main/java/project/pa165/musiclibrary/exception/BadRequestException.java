package project.pa165.musiclibrary.exception;

import org.springframework.validation.Errors;

/**
 * @author Milan
 */
public class BadRequestException extends RuntimeException {

    private Errors errors;

    public Errors getErrors() {
        return errors;
    }

    public BadRequestException(String message, Errors errors) {
        super(message);
        this.errors = errors;
    }
}
