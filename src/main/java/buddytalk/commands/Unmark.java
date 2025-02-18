package buddytalk.commands;

import java.io.IOException;

import buddytalk.storage.Storage;
import buddytalk.tasks.Task;
import buddytalk.tasks.TaskList;
import buddytalk.ui.Ui;

/**
 * Represents a command to unmark a specific task in the task list, indicating it is not done.
 */
public class Unmark extends Command {
    private final int idx;

    /**
     * Creates an Unmark command to mark a task at the specified index as not done.
     *
     * @param idx The index of the task to be unmarked, zero-based.
     */
    public Unmark(int idx) {
        this.idx = idx;
    }

    /**
     * Executes the unmark command. Marks the task at the specified index as not done,
     * updates the storage to reflect the change, and provides feedback to the user.
     *
     * @param tasks   The {@code TaskList} containing the current tasks.
     * @param storage The {@code Storage} instance to save the updated task list.
     * @param ui      The {@code Ui} instance used to display feedback to the user.
     * @return A {@code String} message confirming that the task has been marked as not done.
     * @throws IOException If there is an error saving the updated task list to storage.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) throws IOException {
        Task task = tasks.getTask(idx);
        task.unmark();
        storage.saveTasks(tasks.getAllTasks());
        return String.format("OK, I've marked this task as not done yet: \n  %s", task);
    }
}
