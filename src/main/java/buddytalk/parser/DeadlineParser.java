package buddytalk.parser;

import java.time.format.DateTimeParseException;

import buddytalk.commands.Add;
import buddytalk.exceptions.BuddyException;
import buddytalk.tasks.Deadline;

/**
 * Parses the "deadline" command from user input and creates an {@code Add} command containing a {@code Deadline} task.
 * The "deadline" command is used to add a task with a description and a due date/time.
 */
public class DeadlineParser implements CommandParser {

    /**
     * Parses the input tokens for the "deadline" command and returns the corresponding {@code Add} command.
     * The "deadline" command requires a description and a due date/time, specified using the "/by" clause.
     *
     * @param tokens The array of strings containing the command and its arguments.
     *               The first token should be "deadline",
     *               while the second contains the description and date/time clause.
     * @return An {@code Add} command containing the {@code Deadline}
     *               task with the parsed description and due date/time.
     * @throws BuddyException If the description is missing or if the "/by" clause is missing or blank.
     */
    @Override
    public Add parse(String[] tokens) throws BuddyException {
        try {
            if (tokens.length < 2) {
                throw new BuddyException("The description of a deadline cannot be empty.");
            }

            String[] details = tokens[1].strip().split("/by", 2);
            if (details.length < 2 || details[1].isBlank()) {
                throw new BuddyException("The deadline task must include a /by clause.");
            }

            return new Add(new Deadline(details[0].strip(), details[1].strip(), false));
        } catch (DateTimeParseException e) {
            throw new BuddyException("The date format is invalid. Please use 'yyyy-MM-dd HH:mm'.");
        }
    }
}
