package gameStore.store.annotations.url;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class URLValidator implements ConstraintValidator<URL, String> {

    @Override
    public void initialize(URL constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return value.startsWith("http://") ||
                value.startsWith("https://") ||
                value == null;
    }
}
