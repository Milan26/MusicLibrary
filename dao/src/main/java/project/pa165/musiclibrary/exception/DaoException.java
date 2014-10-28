package project.pa165.musiclibrary.exception;

import org.springframework.dao.DataAccessException;

/**
 * @author Milan
 */
public class DaoException extends DataAccessException {

    public DaoException(String message) {
        super(message);
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
