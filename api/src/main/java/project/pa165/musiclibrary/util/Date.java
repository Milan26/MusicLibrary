package project.pa165.musiclibrary.util;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author Milan
 */
@Documented
@Pattern.List({
        @Pattern(regexp = "(0?[1-9]|[12][0-9]|3[01])-(0?[1-9]|1[012])-((19|20)\\d\\d)" ,
        message = "must match pattern dd-MM-yyyy")
})
@Target({ FIELD, METHOD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = DateValidator.class)
public @interface Date {
    String message() default "not valid date";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
