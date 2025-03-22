package buddytalk.parser;

import buddytalk.commands.Unmark;
import buddytalk.exceptions.BuddyException;

/**
 * Parses the "unmark" command from user input and creates an {@code Unmark} command.
 * The "unmark" command is used to mark a specific task as not completed.
 */
public class UnmarkParser implements CommandParser {

    /**
     * Parses the input tokens for the "unmark" command and returns the corresponding {@code Unmark} command.
     *
     * @param tokens The array of strings containing the command and its arguments.
     *               The first token should be "unmark", and the second token should be the task index to unmark.
     * @return An {@code Unmark} command with the specified task index.
     * @throws BuddyException If the number of tokens is insufficient or if the task index is invalid.
     */
    @Override
    public Unmark parse(String[] tokens) throws BuddyException {
        if (tokens.length < 2) {
            throw new BuddyException("The unmark command must include a task index. \n"
                    + "Try 'help unmark' for more information.");
        }

        try {
            int index = Integer.parseInt(tokens[1].strip()) - 1;
            return new Unmark(index);
        } catch (NumberFormatException e) {
            throw new BuddyException("Invalid task index for unmark command. \n"
                    + "Try 'help unmark' for more information.");
        }
    }
}
