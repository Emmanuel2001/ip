package buddytalk.parser;

import buddytalk.commands.Find;
import buddytalk.exceptions.BuddyException;

/**
 * Parses the "find" command from user input and creates a {@code Find} command.
 * The "find" command is used to search for tasks that contain a specific keyword.
 */
public class FindParser implements CommandParser {

    /**
     * Parses the input tokens for the "find" command and returns the corresponding {@code Find} command.
     *
     * @param tokens The array of strings containing the command and its arguments.
     *               The first token should be "find", followed by the search term as the second token.
     * @return A {@code Find} command that encapsulates the search keyword.
     * @throws BuddyException If the search term is missing or blank.
     */
    @Override
    public Find parse(String[] tokens) throws BuddyException {
        if (tokens.length < 2 || tokens[1].isBlank()) {
            throw new BuddyException("The find command must include a search term.");
        }

        return new Find(tokens[1].strip());
    }
}
