package project.pa165.musiclibrary.util;

/**
 * @author Milan
 */
public class FieldErrorInfo {

    private String field;
    private String message;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public FieldErrorInfo(String field, String message) {
        this.field = field;
        this.message = message;
    }
}
