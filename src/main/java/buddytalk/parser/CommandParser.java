package buddytalk.parser;

import buddytalk.commands.Command;
import buddytalk.exceptions.BuddyException;

/**
 * Represents a parser for a specific command in the application.
 * Each implementation of this interface is responsible for parsing user input and creating
 * the corresponding {@code Command} object.
 */
public interface CommandParser {

    /**
     * Parses the input tokens and returns a {@code Command} object representing the user's command.
     *
     * @param tokens The array of strings containing the command and its arguments.
     *               The tokens are expected to be preprocessed (e.g., split by whitespace).
     * @return A {@code Command} object that represents the parsed command.
     * @throws BuddyException If the input tokens are invalid or improperly formatted for the specific command.
     */
    Command parse(String[] tokens) throws BuddyException;
}
