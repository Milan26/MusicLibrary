package project.pa165.musiclibrary.util;

/**
 * @author Milan
 */
public class ErrorInfo {

    private final Integer status;
    private final String message;

    public Integer getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public ErrorInfo(Integer status, String message) {
        this.status = status;
        this.message = message;
    }
}
