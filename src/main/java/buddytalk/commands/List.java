package buddytalk.commands;

import buddytalk.storage.Storage;
import buddytalk.tasks.TaskList;
import buddytalk.ui.Ui;

/**
 * Represents a command to list all tasks in the task list.
 */
public class List extends Command {
    /**
     * Executes the list command. Retrieves all tasks from the task list
     * and displays them to the user.
     *
     * @param tasks   The {@code TaskList} containing the current tasks.
     * @param storage The {@code Storage} instance to manage task storage (unused in this command).
     * @param ui      The {@code Ui} instance used to display the list of tasks to the user.
     * @return A {@code String} containing the formatted list of all tasks.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        return ui.displayList(tasks.getAllTasks());
    }
}
