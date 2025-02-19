package buddytalk.parser;

import buddytalk.commands.List;

/**
 * Parses the "list" command from user input and creates a {@code List} command.
 * The "list" command is used to display all tasks currently stored.
 */
public class ListParser implements CommandParser {

    /**
     * Parses the input tokens for the "list" command and returns a {@code List} command.
     * The "list" command does not require any additional arguments.
     *
     * @param tokens The array of strings containing the command and its arguments.
     *               For the "list" command, no arguments are expected.
     * @return A {@code List} command that represents the "list" operation.
     */
    @Override
    public List parse(String[] tokens) {
        return new List();
    }
}
