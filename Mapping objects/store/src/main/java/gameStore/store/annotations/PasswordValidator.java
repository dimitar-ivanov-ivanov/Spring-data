package gameStore.store.annotations;

import gameStore.store.constants.TextConstants;
import gameStore.store.validation.AnnotationsUtil;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

@Component
public class PasswordValidator implements
        ConstraintValidator<Password, CharSequence> {

    private static final Pattern PATTERN_LOWER = Pattern.compile("[a-z]");
    private static final Pattern PATTERN_UPPER = Pattern.compile("[A-Z]");
    private static final Pattern PATTERN_DIGIT = Pattern.compile("[0-9]");

    private int minLength;
    private int maxLength;
    private boolean hasUpper;
    private boolean hasLower;
    private boolean hasDigit;

    @Override
    public void initialize(Password password) {
        this.minLength = password.minLength();
        this.maxLength = password.maxLength();
        this.hasUpper = password.containsUpperCase();
        this.hasLower = password.containsLowerCase();
        this.hasDigit = password.containsDigit();
    }

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {

        if (value == null) {
            AnnotationsUtil.setErrorMessage(
                    context,
                    TextConstants.PASSWORD_CANNOT_BE_NULL
            );
        }


        if (value.length() > this.maxLength) {
            AnnotationsUtil.setErrorMessage(
                    context,
                    TextConstants.PASSWORD_TOO_LONG
            );
            return false;
        }

        String password = value.toString();

        if (!PATTERN_LOWER.matcher(password).find() && this.hasLower) {
            AnnotationsUtil.setErrorMessage(
                    context,
                    TextConstants.PASSWORD_SHOULD_CONTAIN_LOWERCASE_LETTER
            );
            return false;
        }

        if (!PATTERN_LOWER.matcher(password).find() && this.hasLower) {
            AnnotationsUtil.setErrorMessage(
                    context,
                    TextConstants.PASSWORD_SHOULD_CONTAIN_LOWERCASE_LETTER
            );
            return false;
        }

        if (!PATTERN_UPPER.matcher(password).find() && this.hasUpper) {
            AnnotationsUtil.setErrorMessage(
                    context,
                    TextConstants.PASSWORD_SHOULD_CONTAIN_UPPERCASE_LETTER
            );
            return false;
        }

        if (!PATTERN_DIGIT.matcher(password).find() && this.hasDigit) {
            AnnotationsUtil.setErrorMessage(
                    context,
                    TextConstants.PASSWORD_SHOULD_CONTAIN_DIGIT
            );
            return false;
        }

        return true;
    }
}
