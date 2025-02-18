package buddytalk.parser;

import buddytalk.commands.Add;
import buddytalk.commands.Command;
import buddytalk.commands.Delete;
import buddytalk.commands.Find;
import buddytalk.commands.List;
import buddytalk.commands.Mark;
import buddytalk.commands.Unmark;
import buddytalk.exceptions.BuddyException;
import buddytalk.tasks.Deadline;
import buddytalk.tasks.Event;
import buddytalk.tasks.ToDo;

/**
 * Represents a parser that interprets user input and converts it into executable commands.
 */
public class Parser {

    /**
     * Parses the user input string and returns the corresponding {@code Command}.
     *
     * @param input The user input string to be parsed.
     * @return The {@code Command} that corresponds to the user input.
     * @throws BuddyException If the input is invalid or if the command is unknown.
     */
    public static Command parse(String input) throws BuddyException {
        String[] tokens = input.split(" ", 2);
        String command = tokens[0];

        return switch (command) {
        case "todo" -> parseToDo(tokens);
        case "deadline" -> parseDeadline(tokens);
        case "event" -> parseEvent(tokens);
        case "mark" -> parseMark(tokens);
        case "unmark" -> parseUnmark(tokens);
        case "find" -> parseFind(tokens);
        case "list" -> new List();
        case "delete" -> parseDelete(tokens);
        default -> throw new BuddyException("Unknown command: " + command);
        };
    }

    /**
     * Parses the input for a "todo" command and creates the corresponding {@code Add} command.
     *
     * @param tokens The input split into tokens.
     * @return The {@code Command} to add a ToDo task.
     * @throws BuddyException If the description of the ToDo is empty.
     */
    private static Command parseToDo(String[] tokens) throws BuddyException {
        if (tokens.length < 2) {
            throw new BuddyException("The description of a todo cannot be empty.");
        }
        return new Add(new ToDo(tokens[1], false));
    }

    /**
     * Parses the input for a "deadline" command and creates the corresponding {@code Add} command.
     *
     * @param tokens The input split into tokens.
     * @return The {@code Command} to add a Deadline task.
     * @throws BuddyException If the description or /by clause is missing.
     */
    private static Command parseDeadline(String[] tokens) throws BuddyException {
        if (tokens.length < 2) {
            throw new BuddyException("The description of a deadline cannot be empty.");
        }
        String[] deadlineTokens = tokens[1].split("/by", 2);
        if (deadlineTokens.length < 2) {
            throw new BuddyException("The deadline must have a '/by' clause.");
        }
        return new Add(new Deadline(deadlineTokens[0].trim(), deadlineTokens[1].trim(), false));
    }

    /**
     * Parses the input for an "event" command and creates the corresponding {@code Add} command.
     *
     * @param tokens The input split into tokens.
     * @return The {@code Command} to add an Event task.
     * @throws BuddyException If the description, /from clause, or /to clause is missing.
     */
    private static Command parseEvent(String[] tokens) throws BuddyException {
        if (tokens.length < 2) {
            throw new BuddyException("The description of an event cannot be empty.");
        }
        String[] eventTokens = tokens[1].split("/from", 2);
        if (eventTokens.length < 2) {
            throw new BuddyException("The event must have a '/from' clause.");
        }
        String[] timeTokens = eventTokens[1].split("/to", 2);
        if (timeTokens.length < 2) {
            throw new BuddyException("The event must have a '/to' clause.");
        }
        return new Add(new Event(eventTokens[0].trim(), timeTokens[0].trim(), timeTokens[1].trim(), false));
    }

    /**
     * Parses the input for a "mark" command and creates the corresponding {@code Mark} command.
     *
     * @param tokens The input split into tokens.
     * @return The {@code Command} to mark a task as completed.
     * @throws BuddyException If the index is invalid or missing.
     */
    private static Command parseMark(String[] tokens) throws BuddyException {
        return new Mark(parseIndex(tokens));
    }

    /**
     * Parses the input for an "unmark" command and creates the corresponding {@code Unmark} command.
     *
     * @param tokens The input split into tokens.
     * @return The {@code Command} to mark a task as not completed.
     * @throws BuddyException If the index is invalid or missing.
     */
    private static Command parseUnmark(String[] tokens) throws BuddyException {
        return new Unmark(parseIndex(tokens));
    }

    /**
     * Parses the input for a "find" command and creates the corresponding {@code Find} command.
     *
     * @param tokens The input split into tokens.
     * @return The {@code Command} to find tasks matching the search term.
     * @throws BuddyException If the search term is missing.
     */
    private static Command parseFind(String[] tokens) throws BuddyException {
        if (tokens.length < 2) {
            throw new BuddyException("The search term cannot be empty.");
        }
        return new Find(tokens[1]);
    }

    /**
     * Parses the input for a "delete" command and creates the corresponding {@code Delete} command.
     *
     * @param tokens The input split into tokens.
     * @return The {@code Command} to delete a task.
     * @throws BuddyException If the index is invalid or missing.
     */
    private static Command parseDelete(String[] tokens) throws BuddyException {
        return new Delete(parseIndex(tokens));
    }

    /**
     * Parses the index specified in the input string.
     *
     * @param tokens The input split into tokens.
     * @return The zero-based index parsed from the input.
     * @throws BuddyException If the index is missing or not a valid number.
     */
    private static int parseIndex(String[] tokens) throws BuddyException {
        if (tokens.length < 2) {
            throw new BuddyException("An index must be specified.");
        }
        try {
            return Integer.parseInt(tokens[1]) - 1;
        } catch (NumberFormatException e) {
            throw new BuddyException("Invalid index format. Index must be a number.");
        }
    }
}
