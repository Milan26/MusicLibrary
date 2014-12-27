package project.pa165.musiclibrary.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @author Milan
 */
public class DateValidator implements ConstraintValidator<Date, String> {

    private static final Logger logger = LoggerFactory.getLogger(DateValidator.class);
    static private final DateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");

    @Override
    public void initialize(Date date) {
        DATE_FORMAT.setLenient(false);
    }

    @Override
    public boolean isValid(String date, ConstraintValidatorContext constraintValidatorContext) {
        if (date == null) {
            logger.debug("date is null");
            return true;
        }

        java.util.Date parsedDate;
        try {
            parsedDate = DATE_FORMAT.parse(date);
            logger.debug("date={} was successfully parsed", date);
        } catch (ParseException e) {
            logger.debug("failed to parse date={}", date);
            return false;
        }
        return parsedDate != null;
    }
}
