package project.pa165.musiclibrary.util;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @author Milan
 */
public class DateValidator implements ConstraintValidator<Date, String> {
    static private final DateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");

    @Override
    public void initialize(Date date) {
        DATE_FORMAT.setLenient(false);
    }

    @Override
    public boolean isValid(String date, ConstraintValidatorContext constraintValidatorContext) {
        if (date == null)
            return true;

        java.util.Date parsedDate = null;
        try {
            parsedDate = DATE_FORMAT.parse(date);
        } catch (ParseException e) {
            return false;
        }
        return parsedDate != null;
    }
}
