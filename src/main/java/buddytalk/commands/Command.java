package buddytalk.commands;

import java.io.IOException;

import buddytalk.exceptions.BuddyException;
import buddytalk.storage.Storage;
import buddytalk.tasks.TaskList;
import buddytalk.ui.Ui;

/**
 * Represents an abstract command to be executed.
 * Commands define specific functionality and interact with the task list, storage, and UI.
 */
public abstract class Command {

    /**
     * Executes the command, modifying the task list,
     * interacting with storage, or updating the user interface.
     *
     * @param tasks   The {@code TaskList} containing the current tasks managed by the application.
     * @param storage The {@code Storage} object handling the saving and loading of tasks from persistent storage.
     * @param ui      The {@code Ui} instance used for displaying feedback or messages to the user.
     * @return A {@code String} containing the result or feedback of the command execution.
     * @throws BuddyException If an error specific to the BuddyTalk application occurs during execution.
     * @throws IOException    If an input/output operation fails during execution.
     */
    public abstract String execute(TaskList tasks, Storage storage, Ui ui) throws BuddyException, IOException;
}
