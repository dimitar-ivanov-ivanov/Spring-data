package User.System.system.models;

import User.System.system.constants.TextConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Arrays;
import java.util.Locale;
import java.util.Set;

import org.junit.Assert;

public class UserPasswordTests {

    private User user;
    private final String SHORT_PASSWORD = "ABC";
    private final String LONG_PASSWORD = "A".repeat(51);
    private final String NO_DIGIT_PASSWORD = "ABCDbadasdsadsad";
    private final String NO_LOWERCASE_PASSWORD = "A131AAAAAAA";
    private final String NO_UPPERCASE_PASSWORD = NO_LOWERCASE_PASSWORD.toLowerCase(Locale.ROOT);
    private final String NO_SPECIAL_SYMBOL_PASSWORD = "Ab1341214ab";
    private final String VALID_PASSWORD = "ABcd1234&*(";

    private final String ERROR_MESSAGE = "Violation message doesn't match constant message";

    @BeforeEach
    public void setUp() {
        user = new User();
    }

    @Test
    public void passwordIsTooShort() {
        user.setPassword(SHORT_PASSWORD);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        violations.forEach(
                violation -> {
                    if (violation.getPropertyPath().toString().equals("password")) {
                        Assert.assertEquals(
                                ERROR_MESSAGE,
                                0,
                                violation.getMessage().compareTo(TextConstants.PASSWORD_TOO_SHORT));
                    }
                });
    }

    @Test
    public void passwordIsTooLong() {
        user.setPassword(LONG_PASSWORD);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        violations.forEach(
                violation -> {
                    if (violation.getPropertyPath().toString().equals("password")) {
                        Assert.assertEquals(
                                ERROR_MESSAGE,
                                0,
                                violation.getMessage().compareTo(TextConstants.PASSWORD_TOO_LONG));
                    }
                });
    }

    @Test
    public void passwordDoesNotContainDigit() {
        user.setPassword(NO_DIGIT_PASSWORD);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        violations.forEach(
                violation -> {
                    if (violation.getPropertyPath().toString().equals("password")) {
                        Assert.assertEquals(
                                ERROR_MESSAGE,
                                0,
                                violation.getMessage().compareTo(TextConstants.PASSWORD_SHOULD_CONTAIN_DIGIT));
                    }
                });
    }

    @Test
    public void passwordDoesNotContainLowercase() {
        user.setPassword(NO_LOWERCASE_PASSWORD);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        violations.forEach(
                violation -> {
                    if (violation.getPropertyPath().toString().equals("password")) {
                        Assert.assertEquals(
                                ERROR_MESSAGE,
                                0,
                                violation.getMessage().compareTo(TextConstants.PASSWORD_SHOULD_CONTAIN_LOWERCASE_LETTER));
                    }
                });
    }

    @Test
    public void passwordDoesNotContainUppercase() {
        user.setPassword(NO_UPPERCASE_PASSWORD);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        violations.forEach(
                violation -> {
                    if (violation.getPropertyPath().toString().equals("password")) {
                        Assert.assertEquals(
                                ERROR_MESSAGE,
                                0,
                                violation.getMessage().compareTo(TextConstants.PASSWORD_SHOULD_CONTAIN_UPPERCASE_LETTER));
                    }
                });
    }

    @Test
    public void passwordDoesNotContainSpecialSymbol() {
        user.setPassword(NO_SPECIAL_SYMBOL_PASSWORD);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        violations.forEach(
                violation -> {
                    if (violation.getPropertyPath().toString().equals("password")) {
                        Assert.assertEquals(
                                ERROR_MESSAGE,
                                0,
                                violation.getMessage().compareTo(TextConstants.PASSWORD_SHOULD_CONTAIN_SPECIAL_SYMBOL));
                    }
                });
    }

    @Test
    public void passwordIsValid() {
        user.setPassword(VALID_PASSWORD);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        violations.forEach(
                violation -> {
                    if (violation.getPropertyPath().toString().equals("password")) {
                        Assert.assertEquals(
                                ERROR_MESSAGE,
                                0,
                                violation.getMessage().compareTo(TextConstants.INVALID_PASSWORD_FORMAT));
                    }
                });
    }
}
