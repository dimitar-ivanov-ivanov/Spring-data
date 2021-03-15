package User.System.system.models;

import User.System.system.constants.TextConstants;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class UserAgeTests {

    private User user;

    private final int AGE_LOW = -1;
    private final int AGE_HIGH = 150;
    private final String ERROR_MESSAGE = "Violation message doesn't match constant message";


    @BeforeEach
    public void setUp() {
        user = new User();
    }

    @Test
    public void testAgeTooLow() {
        user = new User();
        user.setAge(AGE_LOW);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        violations.forEach(
                violation -> {
                    if (violation.getPropertyPath().toString().equals("age")) {
                        Assert.assertEquals(
                                ERROR_MESSAGE,
                                0,
                                violation.getMessage().compareTo(TextConstants.AGE_TOO_LOW));
                    }
                });
    }

    @Test
    public void testAgeTooHigh() {
        user = new User();
        user.setAge(AGE_HIGH);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        violations.forEach(
                violation -> {
                    if (violation.getPropertyPath().toString().equals("age")) {
                        Assert.assertEquals(
                                ERROR_MESSAGE,
                                0,
                                violation.getMessage().compareTo(TextConstants.AGE_TOO_HIGH));
                    }
                });
    }
}
