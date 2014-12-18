package project.pa165.musiclibrary.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import project.pa165.musiclibrary.util.ErrorInfo;
import project.pa165.musiclibrary.util.FieldErrorInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Milan
 */
@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AlbumBadRequestException.class)
    public ResponseEntity<Object> handleAlbumBadRequest(AlbumBadRequestException badRequestEx, WebRequest request) {

        List<FieldErrorInfo> fieldErrorsInfo = new ArrayList<>();

        for (FieldError fieldError : badRequestEx.getErrors().getFieldErrors()) {
            fieldErrorsInfo.add(new FieldErrorInfo(fieldError.getField(), fieldError.getDefaultMessage()));
        }

        return handleExceptionInternal(
                badRequestEx,
                new ErrorInfo(400, badRequestEx.getMessage(), fieldErrorsInfo),
                new HttpHeaders(){{setContentType(MediaType.APPLICATION_JSON);}},
                HttpStatus.UNPROCESSABLE_ENTITY,
                request);
    }
}
