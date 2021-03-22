package gameStore.store.constants;

public class TextConstants {

    public static final String INVALID_PASSWORD_FORMAT =
            "Password must be between 6 and 50 characters and include 1 digit,lowercase,uppercase and a special symbol.";

    public static final String PASSWORD_CANNOT_BE_NULL =
            "Password cannot be null";

    public static final String PASSWORD_TOO_SHORT =
            "Password must be at least 6 characters long";

    public static final String PASSWORD_TOO_LONG =
            "Password must be shorter than 30 characters.";

    public static final String PASSWORD_SHOULD_CONTAIN_LOWERCASE_LETTER
            = "Password should contain at least one lowercase letter.";

    public static final String PASSWORD_SHOULD_CONTAIN_UPPERCASE_LETTER
            = "Password should contain at least one uppercase letter.";

    public static final String PASSWORD_SHOULD_CONTAIN_DIGIT
            = "Password should contain at least one digit.";

    public static final String INVALID_EMAIL_FORMAT =
            "Invalid email format.";

    public static final String EMAIL_CANNOT_BE_NULL =
            "Email cannot be null.";

    public static final String EMAIL_USERNAME_TOO_SHORT =
            "Username must be at least a single character.";

    public static final String EMAIL_USERNAME_TOO_LONG =
            "Username should be lower than 50 characters.";

    public static final String EMAIL_HOSTNAME_TOO_LONG =
            "Hostname should be lower than 50 characters.";
}
