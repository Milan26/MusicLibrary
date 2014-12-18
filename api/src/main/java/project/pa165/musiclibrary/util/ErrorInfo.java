package project.pa165.musiclibrary.util;

import java.util.List;

/**
 * @author Milan
 */
public class ErrorInfo {

    private final Integer status;
    private final String message;
    private List<FieldErrorInfo> fieldErrors;

    public Integer getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public List<FieldErrorInfo> getFieldErrors() {
        return fieldErrors;
    }

    public void setFieldErrors(List<FieldErrorInfo> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }

    public ErrorInfo(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    public ErrorInfo(Integer status, String message, List<FieldErrorInfo> fieldErrors) {
        this.status = status;
        this.message = message;
        this.fieldErrors = fieldErrors;
    }
}
