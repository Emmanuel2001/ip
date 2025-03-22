package buddytalk.parser;

import buddytalk.commands.Mark;
import buddytalk.exceptions.BuddyException;

/**
 * Parses the "mark" command from user input and creates a {@code Mark} command.
 * The "mark" command is used to mark a specific task as completed.
 */
public class MarkParser implements CommandParser {

    /**
     * Parses the input tokens for the "mark" command and returns the corresponding {@code Mark} command.
     *
     * @param tokens The array of strings containing the command and its arguments.
     *               The first token should be "mark", and the second token should be the task index to mark.
     * @return A {@code Mark} command with the specified task index.
     * @throws BuddyException If the number of tokens is insufficient or if the task index is invalid.
     */
    @Override
    public Mark parse(String[] tokens) throws BuddyException {
        if (tokens.length < 2) {
            throw new BuddyException("The mark command must include a task index. \n"
                    + "Try 'help mark' for more information.");
        }

        try {
            int index = Integer.parseInt(tokens[1].strip()) - 1;
            return new Mark(index);
        } catch (NumberFormatException e) {
            throw new BuddyException("Invalid task index for mark command. \n"
                    + "Try 'help mark' for more information.");
        }
    }
}
