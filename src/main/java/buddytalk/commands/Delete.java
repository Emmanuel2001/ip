package buddytalk.commands;

import java.io.IOException;

import buddytalk.exceptions.BuddyException;
import buddytalk.storage.Storage;
import buddytalk.tasks.Task;
import buddytalk.tasks.TaskList;
import buddytalk.ui.Ui;

/**
 * Represents a command to delete a task from the task list.
 */
public class Delete extends Command {
    private final int idx;

    /**
     * Creates a Delete command to remove a task at a specific index in the task list.
     *
     * @param idx The index of the task to be deleted, zero-based.
     */
    public Delete(int idx) {
        this.idx = idx;
    }

    /**
     * Executes the delete command. Removes the task at the specified index
     * from the task list, updates the storage file, and provides feedback to the user.
     *
     * @param tasks   The {@code TaskList} containing the current tasks.
     * @param storage The {@code Storage} instance used to save the updated task list.
     * @param ui      The {@code Ui} instance used to display feedback to the user.
     * @return A {@code String} message confirming the removal of the task.
     * @throws IOException If there is an error saving the updated task list to storage.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) throws IOException {
        try {
            if (idx < 0 || idx >= tasks.size()) {
                throw new BuddyException(String.format("Invalid index! \n"
                        + "Please provide a number between 1 and %d.", tasks.size()));
            }
            Task temp = tasks.getTask(idx);
            tasks.deleteTask(idx);
            storage.saveTasks(tasks.getAllTasks());
            return String.format("Noted. I've removed this task: \n  %s", temp);
        } catch (BuddyException e) {
            return e.getMessage();
        }
    }
}
