package buddytalk.parser;

import buddytalk.commands.Delete;
import buddytalk.exceptions.BuddyException;

/**
 * Parses the "delete" command from user input and creates a {@code Delete} command.
 * The "delete" command is used to remove a task from the task list based on its index.
 */
public class DeleteParser implements CommandParser {

    /**
     * Parses the input tokens for the "delete" command and returns the corresponding {@code Delete} command.
     * The "delete" command requires a single argument specifying the 1-based index of the task to be deleted.
     *
     * @param tokens The array of strings containing the command and its arguments.
     *               The first token should be "delete", followed by the task index as the second token.
     * @return A {@code Delete} command that contains the 0-based index of the task to be deleted.
     * @throws BuddyException If the task index is missing, invalid, or not a number.
     */
    @Override
    public Delete parse(String[] tokens) throws BuddyException {
        if (tokens.length < 2) {
            throw new BuddyException("The delete command must include a task index. \n"
                    + "Try 'help delete' for more info");
        }

        try {
            int index = Integer.parseInt(tokens[1].strip()) - 1;
            return new Delete(index);
        } catch (NumberFormatException | IllegalStateException e) {
            throw new BuddyException("Invalid task index for delete command. \n"
                    + "Try 'help delete' for more info");
        }
    }
}
