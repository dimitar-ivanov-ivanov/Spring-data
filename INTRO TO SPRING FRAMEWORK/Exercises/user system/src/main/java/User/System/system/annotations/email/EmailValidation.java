package User.System.system.annotations.email;

import User.System.system.constants.TextConstants;
import User.System.system.validation.AnnotationsUtil;
import org.springframework.stereotype.Component;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

@Component
public class EmailValidation implements
        ConstraintValidator<Email, CharSequence> {

    private int minUserNameLength;
    private int maxUserNameLength;
    private int maxHostNameLength;
    private Pattern pattern;

    @Override
    public void initialize(Email email) {
        this.minUserNameLength = email.minUserNameLength();
        this.maxUserNameLength = email.maxUserNameLength();
        this.maxHostNameLength = email.maxHostNameLength();
        this.pattern = Pattern.compile(email.regex());
    }

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {

        if (value == null) {
            AnnotationsUtil.setErrorMessage(
                    context,
                    TextConstants.EMAIL_CANNOT_BE_NULL
            );
            return false;
        }

        String email = value.toString();
        int userNameLength = email.indexOf("@");
        int hostNameLength = email.length() - userNameLength - 1;

        if (userNameLength < this.minUserNameLength) {

            AnnotationsUtil.setErrorMessage(
                    context,
                    TextConstants.EMAIL_USERNAME_TOO_SHORT
            );
            return false;
        }

        if (userNameLength > this.maxUserNameLength) {
            AnnotationsUtil.setErrorMessage(
                    context,
                    TextConstants.EMAIL_USERNAME_TOO_LONG
            );
            return false;
        }

        if (hostNameLength > this.maxHostNameLength) {
            AnnotationsUtil.setErrorMessage(
                    context,
                    TextConstants.EMAIL_HOSTNAME_TOO_LONG
            );
            return false;
        }

        return this.pattern.matcher(email)
                .matches();
    }
}
