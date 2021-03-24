package gameStore.store.annotations.email;

import gameStore.store.constants.TextConstants;
import gameStore.store.validation.AnnotationsUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class EmailValidator implements
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
