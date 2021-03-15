package User.System.system.annotations.email;

import User.System.system.constants.TextConstants;
import org.springframework.stereotype.Component;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Component
@Constraint(validatedBy = EmailValidation.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Email {

    String message()
            default TextConstants.INVALID_EMAIL_FORMAT;

    int minUserNameLength() default 1;

    int maxUserNameLength() default 50;

    int maxHostNameLength() default 50;

    String regex()
            default "^[\\w]+[\\w._-]+[\\w]+@[\\w]+[\\w.-_]+[\\w]+$";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
