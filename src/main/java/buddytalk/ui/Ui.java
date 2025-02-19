package buddytalk.ui;

import java.util.ArrayList;

import buddytalk.tasks.Task;

/**
 * Handles all user interface interactions for the BuddyTalk application.
 * <p>
 * The {@code Ui} class provides static methods for displaying messages, task lists,
 * and error messages to the user through the console.
 * </p>
 */
public class Ui {

    /**
     * Constructs a new {@code Ui} object.
     * <p>This constructor is optional since all methods in this class are static,
     * meaning the object does not need to be instantiated.</p>
     */
    public Ui() {}

    /**
     * Displays the list of tasks to the user.
     * <p>If the task list is empty, a message indicating that no tasks are present is displayed.
     * Otherwise, all tasks in the list are printed along with their index.</p>
     *
     * @param tasks The {@code ArrayList<Task>} containing tasks to display.
     */
    public String displayList(ArrayList<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        if (tasks.isEmpty()) {
            sb.append("You have no tasks!\n");
        } else {
            sb.append("Here are the tasks in your list:\n");
            for (int i = 0; i < tasks.size(); i++) {
                sb.append((i + 1)).append(".").append(tasks.get(i).toString()).append("\n");
            }
        }
        return sb.toString();
    }

    /**
     * Displays the tasks of tasks with proper formatting.
     *
     * If the tasks is empty, the method prints a message stating that there are no matching tasks.
     * Otherwise, it prints a formatted tasks of tasks with their index numbers.
     *
     * @param tasks The {@code ArrayList} of {@link Task} objects to be displayed.
     *             Each task is shown with its index number in the tasks.
     */
    public String showList(ArrayList<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        if (tasks.isEmpty()) {
            sb.append("You have no matching tasks!\n");
        } else {
            sb.append("Here are the matching tasks in your tasks:\n");
            for (int i = 0; i < tasks.size(); i++) {
                sb.append((i + 1)).append(".").append(tasks.get(i).toString()).append("\n");
            }
        }
        return sb.toString();
    }

    /**
     * Displays an error message when an error is encountered.
     *
     * @param error A string containing the error message to display.
     */
    public String displayError(String error) {
        assert error != null: "Error message cannot be null!";
        return String.format("""
                 Error: %s
                """, error);
    }
}
