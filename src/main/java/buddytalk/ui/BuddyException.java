package buddytalk.ui;

/**
 * Represents a custom exception specific to the BuddyTalk application.
 * {@code BuddyException} is used to handle application-specific error scenarios and provide
 * informative error messages to the user or developer.
 */
public class BuddyException extends Exception {

    /**
     * Constructs a {@code BuddyException} with a specified message.
     *
     * @param message A string containing the detailed description of the exception,
     *                explaining the reason for this exception being thrown.
     */
    public BuddyException(String message) {
        super(message);
    }
}