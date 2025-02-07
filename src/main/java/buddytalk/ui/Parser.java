package buddytalk.ui;

/**
 * A utility class that parses user input into a {@link Command} object.
 * The {@code Parser} class handles the extraction of command type
 * and arguments from the raw input string provided by the user.
 */
public class Parser {

    /**
     * Parses the user input into a {@link Command} object. The method splits
     * the input string into a command and its arguments, returning a
     * {@link Command} object that encapsulates this data.
     *
     * @param input The raw input string entered by the user.
     * @return A {@link Command} object containing the command and arguments.
     * @throws BuddyException If the input string is empty.
     */
    public static Command parse(String input) throws BuddyException {
        if (input.isEmpty()) {
            throw new BuddyException("Empty inputs are not accepted. Please try again.");
        }

        String[] parts = input.split(" ", 2);

        String command = parts[0];

        if (parts.length == 1) {
            return new Command(command, "");
        }

        return new Command(command, parts[1].trim());
    }
}
