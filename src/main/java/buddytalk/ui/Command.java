package buddytalk.ui;

/**
 * Represents a command entered by the user. A command object contains
 * the command type and its associated arguments.
 */
public class Command {
    protected String command;
    protected String arguments;

    /**
     * Constructs a Command object with the specified command type
     * and arguments.
     *
     * @param command The type of the command (e.g., "todo", "list").
     * @param arguments The arguments for the command (e.g., task description or parameters).
     */
    public Command(String command, String arguments) {
        this.command = command;
        this.arguments = arguments;
    }

    /**
     * Retrieves the type of the command.
     *
     * @return The command type as a String.
     */
    public String getCommand() {
        return command;
    }

    /**
     * Retrieves the arguments associated with the command.
     *
     * @return The command arguments as a String.
     */
    public String getArguments() {
        return arguments;
    }
}
