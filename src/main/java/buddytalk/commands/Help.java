package buddytalk.commands;

import java.util.HashMap;
import java.util.Map;

import buddytalk.storage.Storage;
import buddytalk.tasks.TaskList;
import buddytalk.ui.Ui;

/**
 * Represents the "help" command, providing detailed descriptions of supported commands
 * and their respective usages.
 * The {@code Help} class stores all command help information and allows retrieval of
 * a specific command's details or all supported commands.
 */
public class Help extends Command {

    private static final Map<String, String> COMMAND_HELP = new HashMap<>();
    private final String helpMessage;
    static {
        COMMAND_HELP.put("help", "Displays information about available commands. \n"
                + "Usage: help or help [command]\n"
                + "Example: help or help deadline");
        COMMAND_HELP.put("deadline", "Adds a task with a due date. \n"
                + "Usage: deadline [description] /by [due date: yyyy-mm-dd hh:mm]\n"
                + "Example: deadline meeting /by 2021-01-01 1259");
        COMMAND_HELP.put("event", "Adds an event with a start and end time. \n"
                + "Usage: event [description] /from [start: yyyy-mm-dd hh:mm] /to [end: yyyy-mm-dd hh:mm]\n"
                + "Example: event meeting /from 2021-01-01 1200 /to 2021-01-01 1300");
        COMMAND_HELP.put("delete", "Deletes a task by its index. \n"
                + "Usage: delete [task index]\n"
                + "Example: delete 1");
        COMMAND_HELP.put("list", "Lists all current tasks. \n"
                + "Usage: list \n"
                + "Example: list");
        COMMAND_HELP.put("todo", "Adds a todo task. \n"
                + "Usage: todo [description] \n"
                + "Example: todo meeting");
        COMMAND_HELP.put("mark", "Marks a task as done by its index. \n"
                + "Usage: mark [task index] \n"
                + "Example: mark 1");
        COMMAND_HELP.put("unmark", "Marks a task as not done by its index. \n"
                + "Usage: unmark [task index] \n"
                + "Example: unmark 1");
        COMMAND_HELP.put("find", "Finds tasks that contain the given keyword. \n"
                + "Usage: find [keyword] \n"
                + "Example: find meeting");
        COMMAND_HELP.put("bye", "Exit application. \n"
                + "Usage: bye");
    }

    /**
     * Constructs a new {@code Help} with a specific help message to be displayed.
     *
     * @param helpMessage The help message provides information about a specific command or all commands.
     */
    public Help(String helpMessage) {
        this.helpMessage = helpMessage;
    }

    /**
     * Retrieves help information for a specific command.
     *
     * @param command The name of the command for which help is requested.
     * @return A {@code Help} object containing the help description for the specified command.
     *         If the command is not found, a generic "No help available" message is returned.
     */
    public static Help getHelp(String command) {
        String helpDescription = COMMAND_HELP.getOrDefault(command, "No help available for this command. "
                + "Type 'help' to see all available commands");
        return new Help(helpDescription);
    }

    /**
     * Retrieves help information for all supported commands, including their usages.
     *
     * @return A {@code Help} object containing the help descriptions and usage information
     *         for all the commands in the application.
     */
    public static Help getAllHelp() {
        StringBuilder helpBuilder = new StringBuilder();
        COMMAND_HELP.forEach((command, desc) ->
                helpBuilder.append(command).append(": ").append(desc).append("\n").append("\n"));
        return new Help(helpBuilder.toString().trim());
    }

    /**
     * Displays the help message for the user.
     * This method is executed when the "help" command is called, providing descriptive
     * and user-friendly information about how to use the application's commands.
     *
     * @param tasks   An instance of the {@code TaskList} storing the list of tasks (not used in this method).
     * @param storage An instance of the {@code Storage} managing data storage (not used in this method).
     * @param ui      An instance of the {@code Ui} used for interacting with the user (not used in this method).
     * @return A {@code String} containing the help message to be displayed to the user.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        return helpMessage;
    }
}
