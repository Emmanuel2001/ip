package buddytalk.parser;

import buddytalk.commands.Help;
import buddytalk.exceptions.BuddyException;

/**
 * Parses the "help" command from user input and creates a {@code Help} command.
 * The "help" command is used to display information about available commands or
 * detailed information about a specific command's usage.
 */
public class HelpParser implements CommandParser {

    /**
     * Parses the input tokens for the "help" command and returns the corresponding {@code Help} command.
     * The input should follow one of the following formats:
     * {@code help} - Displays all available commands and their details.
     * {@code help [command]} - Displays detailed information about the specified command.
     * If the input contains more than two tokens, an exception is thrown.
     *
     * @param tokens The array of strings containing the command and its arguments.
     *               The first token should always be "help".
     *               The second token is optional, it specifies the command name for which help is requested.
     * @return A {@code Help} command containing the requested help information.
     * @throws BuddyException If the input contains more than two tokens or the command format is invalid.
     */
    @Override
    public Help parse(String[] tokens) throws BuddyException {
        if (tokens.length == 1) {
            return Help.getAllHelp();
        } else if (tokens.length == 2) {
            return Help.getHelp(tokens[1].strip());
        } else {
            throw new BuddyException("The 'help' command is not formatted correctly. \nUsage: help or help [command]");
        }
    }
}
