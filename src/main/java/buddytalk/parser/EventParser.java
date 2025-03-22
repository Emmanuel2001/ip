package buddytalk.parser;

import java.time.format.DateTimeParseException;

import buddytalk.commands.Add;
import buddytalk.exceptions.BuddyException;
import buddytalk.tasks.Event;

/**
 * Parses the "event" command from user input and creates an {@code Add} command containing an {@code Event} task.
 * The "event" command is used to add a new event with a description, start time, and end time.
 */
public class EventParser implements CommandParser {

    /**
     * Parses the input tokens for the "event" command and returns the corresponding {@code Add} command.
     * The "event" command requires a description, a start time (specified using "/from"),
     * and an end time (specified using "/to").
     *
     * @param tokens The array of strings containing the command and its arguments.
     *               The first token should be "event", while the second contains the description and required clauses.
     * @return An {@code Add} command containing the {@code Event} task
     *              with the parsed description, start time, and end time.
     * @throws BuddyException If the description is missing or
     *                        if any of the required clauses (/from or /to) are missing or blank.
     */
    @Override
    public Add parse(String[] tokens) throws BuddyException {
        try {
            if (tokens.length < 2) {
                throw new BuddyException("The description of an event cannot be empty. \n"
                        + "Try 'help event' for more info.");
            }

            String[] details = tokens[1].strip().split("/from", 2);
            if (details.length < 2 || details[1].isBlank()) {
                throw new BuddyException("The event task must be in the format: \n"
                        + "event description /from date (yyyy-mm-dd hh:mm) /to date (yyyy-mm-dd hh:mm). \n"
                        + "Try 'help event' for more info.");
            }

            String[] times = details[1].strip().split("/to", 2);
            if (times.length < 2 || times[1].isBlank()) {
                throw new BuddyException("The event task must include a /to clause. \n"
                        + "Example: event description /from date (yyyy-mm-dd hh:mm) /to date (yyyy-mm-dd hh:mm). \n"
                        + "Try 'help event' for more info.");
            }

            return new Add(new Event(details[0].strip(), times[0].strip(), times[1].strip(), false));
        } catch (DateTimeParseException e) {
            throw new BuddyException("The date format is invalid. Please use 'yyyy-mm-dd hh:mm'.");
        }
    }
}
