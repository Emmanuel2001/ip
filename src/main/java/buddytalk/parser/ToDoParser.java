package buddytalk.parser;

import buddytalk.commands.Add;
import buddytalk.exceptions.BuddyException;
import buddytalk.tasks.ToDo;

/**
 * Parses the "todo" command from user input and creates an {@code Add} command
 * that encapsulates a {@code ToDo} task.
 * The "todo" command is used to create a new task with only a description.
 */
public class ToDoParser implements CommandParser {

    /**
     * Parses the input tokens for the "todo" command and returns the corresponding {@code Add} command.
     *
     * @param tokens The array of strings containing the command and its arguments.
     *               The first token should be "todo", followed by the task description.
     * @return An {@code Add} command that encapsulates a {@code ToDo} task with the specified description.
     * @throws BuddyException If the description of the todo is missing or empty.
     */
    @Override
    public Add parse(String[] tokens) throws BuddyException {
        if (tokens.length < 2 || tokens[1].isBlank()) {
            throw new BuddyException("The description of a todo cannot be empty.");
        }
        return new Add(new ToDo(tokens[1].strip(), false));
    }
}
