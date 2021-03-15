package User.System.system.models;

import User.System.system.constants.TextConstants;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class UserUsernameTests {

    private User user;
    private final String SHORT_USERNAME = "ABC";
    private final String LONG_USERNAME = "A".repeat(31);
    private final String ERROR_MESSAGE = "Violation message doesn't match constant message";

    @BeforeEach
    public void setUp() {
        user = new User();
    }

    @Test
    public void userNameIsTooShort() {
        user.setUsername(SHORT_USERNAME);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        violations.forEach(
                violation -> {
                    if (violation.getPropertyPath().toString().equals("username")) {
                        Assert.assertEquals(
                                ERROR_MESSAGE,
                                0,
                                violation.getMessage().compareTo(TextConstants.USERNAME_INCORRECT_LENGTH));
                    }
                });
    }

    @Test
    public void userNameIsTooLong() {
        user.setUsername(LONG_USERNAME);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        violations.forEach(
                violation -> {
                    if (violation.getPropertyPath().toString().equals("username")) {
                        Assert.assertEquals(
                                ERROR_MESSAGE,
                                0,
                                violation.getMessage().compareTo(TextConstants.USERNAME_INCORRECT_LENGTH));
                    }
                });
    }
}
