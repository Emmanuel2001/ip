package buddytalk.commands;

import java.io.IOException;

import buddytalk.exceptions.BuddyException;
import buddytalk.storage.Storage;
import buddytalk.tasks.Task;
import buddytalk.tasks.TaskList;
import buddytalk.ui.Ui;

/**
 * Represents a command to mark a specific task in the task list as done.
 */
public class Mark extends Command {
    private final int idx;

    /**
     * Creates a Mark command to mark a task at the specified index as done.
     *
     * @param idx The index of the task to be marked as done, zero-based.
     */
    public Mark(int idx) {
        this.idx = idx;
    }

    /**
     * Executes the mark command. Marks the task at the specified index as done,
     * updates the storage to reflect the change, and provides feedback to the user.
     *
     * @param tasks   The {@code TaskList} containing the current tasks.
     * @param storage The {@code Storage} instance to save the updated task list.
     * @param ui      The {@code Ui} instance used to display feedback to the user.
     * @return A {@code String} message confirming that the task has been marked as done.
     * @throws IOException If there is an error saving the updated task list to storage.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) throws IOException {
        try {
            if (idx < 0 || idx >= tasks.size()) {
                throw new BuddyException(String.format("Invalid index! \n"
                        + "Please provide a number between 1 and %d.", tasks.size()));
            }
            Task task = tasks.getTask(idx);
            task.mark();
            storage.saveTasks(tasks.getAllTasks());
            return String.format("Nice! I've marked this task as done: \n  %s ", task);
        } catch (BuddyException e) {
            return e.getMessage();
        }
    }
}
