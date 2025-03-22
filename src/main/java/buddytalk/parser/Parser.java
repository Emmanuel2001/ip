package buddytalk.parser;

import java.util.HashMap;
import java.util.Map;

import buddytalk.commands.Command;
import buddytalk.exceptions.BuddyException;


/**
 * Represents a parser that interprets user input and converts it into corresponding executable commands.
 * This class delegates the parsing of specific commands to specialized {@code CommandParser} instances.
 */
public class Parser {

    private static final Map<String, CommandParser> commandParsers = new HashMap<>();

    static {
        commandParsers.put("todo", new ToDoParser());
        commandParsers.put("deadline", new DeadlineParser());
        commandParsers.put("event", new EventParser());
        commandParsers.put("mark", new MarkParser());
        commandParsers.put("unmark", new UnmarkParser());
        commandParsers.put("delete", new DeleteParser());
        commandParsers.put("find", new FindParser());
        commandParsers.put("list", new ListParser());
        commandParsers.put("help", new HelpParser());
    }

    /**
     * Parses the user input string and returns the corresponding {@code Command}.
     * This method determines the command type from the input and delegates the parsing
     * to the appropriate {@code CommandParser}.
     *
     * @param input The user input string to be parsed, which typically starts with a
     *              command word and may include additional arguments (e.g., "todo Task 1").
     * @return A {@code Command} object that corresponds to the user input.
     * @throws BuddyException If the input is null, empty, or does not match a known command type.
     */
    public static Command parse(String input) throws BuddyException {
        if (input == null || input.isBlank()) {
            throw new BuddyException("Input cannot be empty or null");
        }

        String[] tokens = input.strip().split("\\s+", 2);
        String commandType = tokens[0].toLowerCase();

        CommandParser parser = commandParsers.get(commandType);
        if (parser == null) {
            throw new BuddyException("Unknown command: " + commandType + ". \nTry 'help' for a list of commands");
        }

        return parser.parse(tokens);
    }
}
