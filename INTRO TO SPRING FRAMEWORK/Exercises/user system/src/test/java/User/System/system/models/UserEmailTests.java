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
import java.util.List;

public class UserEmailTests {

    private User user;

    private final String BAD_CHAR_FIRST = ".";
    private final String BAD_CHAR_SECOND = "-";
    private final String BAD_CHAR_THIRD = "_";

    private final String USERNAME = "dimitar.ivanov";
    private final String USERNAME_LONG = "dimitar.ivanov".repeat(10);
    private final String HOSTNAME = "abv.bg";
    private final String HOSTNAME_LONG = "abv.bg".repeat(10);


    private final String ERROR_MESSAGE = "Violation message doesn't match constant message";

    @BeforeEach
    public void setUp() {
        user = new User();
    }

    @Test
    public void emailUserNameIsTooShort() {
        User user = new User();
        user.setEmail("" + "@" + HOSTNAME);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        violations.forEach(
                violation -> {
                    if (violation.getPropertyPath().toString().equals("email")) {
                        Assert.assertEquals(
                                ERROR_MESSAGE,
                                0,
                                violation.getMessage().compareTo(TextConstants.EMAIL_USERNAME_TOO_SHORT));
                    }
                });
    }

    @Test
    public void emailUserNameIsTooLong() {
        User user = new User();
        user.setEmail(USERNAME_LONG + "@" + HOSTNAME);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        violations.forEach(
                violation -> {
                    if (violation.getPropertyPath().toString().equals("email")) {
                        Assert.assertEquals(
                                ERROR_MESSAGE,
                                0,
                                violation.getMessage().compareTo(TextConstants.EMAIL_USERNAME_TOO_LONG));
                    }
                });
    }

    @Test
    public void emailHostNameIsTooLong() {
        User user = new User();
        user.setEmail(USERNAME + "@" + HOSTNAME_LONG);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        violations.forEach(
                violation -> {
                    if (violation.getPropertyPath().toString().equals("email")) {
                        Assert.assertEquals(
                                ERROR_MESSAGE,
                                0,
                                violation.getMessage().compareTo(TextConstants.EMAIL_HOSTNAME_TOO_LONG));
                    }
                });
    }

    @Test
    public void emailStartsWithForbiddenCharacters() {
        User user = new User();
        String firstEmail = BAD_CHAR_FIRST + USERNAME + "@" + HOSTNAME;
        String secondEmail = BAD_CHAR_SECOND + USERNAME + "@" + HOSTNAME;
        String thirdEmail = BAD_CHAR_THIRD + USERNAME + "@" + HOSTNAME;

        List<String> badBeginningEmails = List.of(firstEmail, secondEmail, thirdEmail);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        badBeginningEmails.forEach(
                email -> {
                    user.setEmail(email);
                    Set<ConstraintViolation<User>> violations = validator.validate(user);

                    violations.forEach(violation -> {
                        if (violation.getPropertyPath().toString().equals("email")) {
                            Assert.assertEquals(
                                    ERROR_MESSAGE,
                                    0,
                                    violation.getMessage().compareTo(TextConstants.INVALID_EMAIL_FORMAT));
                        }
                    });
                });

    }

    @Test
    public void emailWithForbiddenCharactersBeforeHost() {
        User user = new User();
        String firstEmail = USERNAME + BAD_CHAR_FIRST + "@" + HOSTNAME;
        String secondEmail = USERNAME + BAD_CHAR_SECOND + "@" + HOSTNAME;
        String thirdEmail = USERNAME + BAD_CHAR_THIRD + "@" + HOSTNAME;

        List<String> badBeginningEmails = List.of(firstEmail, secondEmail, thirdEmail);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        badBeginningEmails.forEach(
                email -> {
                    user.setEmail(email);
                    Set<ConstraintViolation<User>> violations = validator.validate(user);

                    violations.forEach(violation -> {
                        if (violation.getPropertyPath().toString().equals("email")) {
                            Assert.assertEquals(
                                    ERROR_MESSAGE,
                                    0,
                                    violation.getMessage().compareTo(TextConstants.INVALID_EMAIL_FORMAT));
                        }
                    });
                });
    }

    @Test
    public void emailWithForbiddenCharactersAfterHost() {
        User user = new User();
        String firstEmail = USERNAME + "@" + BAD_CHAR_FIRST + HOSTNAME;
        String secondEmail = USERNAME + "@" + BAD_CHAR_SECOND + HOSTNAME;
        String thirdEmail = USERNAME + "@" + BAD_CHAR_THIRD + HOSTNAME;

        List<String> badBeginningEmails = List.of(firstEmail, secondEmail, thirdEmail);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        badBeginningEmails.forEach(
                email -> {
                    user.setEmail(email);
                    Set<ConstraintViolation<User>> violations = validator.validate(user);

                    violations.forEach(violation -> {
                        if (violation.getPropertyPath().toString().equals("email")) {
                            Assert.assertEquals(
                                    ERROR_MESSAGE,
                                    0,
                                    violation.getMessage().compareTo(TextConstants.INVALID_EMAIL_FORMAT));
                        }
                    });
                });
    }

    @Test
    public void emailWithForbiddenCharactersAtTheEnd() {
        User user = new User();
        String firstEmail = USERNAME + "@" + HOSTNAME + BAD_CHAR_FIRST;
        String secondEmail = USERNAME + "@" + HOSTNAME + BAD_CHAR_SECOND;
        String thirdEmail = USERNAME + "@" + HOSTNAME + BAD_CHAR_THIRD;

        List<String> badBeginningEmails = List.of(firstEmail, secondEmail, thirdEmail);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        badBeginningEmails.forEach(
                email -> {
                    user.setEmail(email);
                    Set<ConstraintViolation<User>> violations = validator.validate(user);

                    violations.forEach(violation -> {
                        if (violation.getPropertyPath().toString().equals("email")) {
                            Assert.assertEquals(
                                    ERROR_MESSAGE,
                                    0,
                                    violation.getMessage().compareTo(TextConstants.INVALID_EMAIL_FORMAT));
                        }
                    });
                });
    }
}
