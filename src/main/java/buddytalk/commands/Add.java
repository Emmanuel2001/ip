package buddytalk.commands;

import java.io.IOException;

import buddytalk.storage.Storage;
import buddytalk.tasks.Task;
import buddytalk.tasks.TaskList;
import buddytalk.ui.Ui;

/**
 * Represents a command to add a task to the task list.
 * This command updates the task list, saves the updated list to storage,
 * and provides feedback to the user via the UI.
 */
public class Add extends Command {
    private final Task task;

    /**
     * Constructs an Add command with the specified task.
     *
     * @param task The task to be added to the task list.
     */
    public Add(Task task) {
        this.task = task;
    }

    /**
     * Executes the add command by adding the specified task to the given task list,
     * saving the updated task list to the storage, and returning
     * a confirmation message to the user.
     *
     * @param tasks   The list of tasks to which the new task will be added.
     * @param storage The storage system where tasks are saved persistently.
     * @param ui      The user interface used to provide feedback to the user.
     * @return A String containing the confirmation message for the user.
     * @throws IOException If an input or output operation fails during saving the task list.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) throws IOException {
        tasks.addTask(task);
        storage.saveTasks(tasks.getAllTasks());
        return String.format("Got it. I've added this task: \n %s", task.toString());
    }
}
