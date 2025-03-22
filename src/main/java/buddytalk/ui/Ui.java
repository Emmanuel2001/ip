package buddytalk.ui;

import java.util.ArrayList;

import buddytalk.tasks.Task;

/**
 * Handles all user interface interactions for the BuddyTalk application.
 * The {@code Ui} class provides static methods for displaying messages, task lists,
 * and error messages to the user through the console.
 */
public class Ui {

    /**
     * Constructs a new {@code Ui} object.
     * This constructor is optional since all methods in this class are static,
     * meaning the object does not need to be instantiated.
     */
    public Ui() {}

    /**
     * Generates a formatted task list message.
     * This method displays all tasks with their index, and formats a message based
     * on whether the task list is empty or not.
     *
     * @param tasks        The list of tasks to display.
     * @param message The message to display if the list is empty.
     * @param header       The header message for tasks if the list is not empty.
     * @return A formatted string containing the task list or an empty message.
     */
    public String printMessage(ArrayList<Task> tasks, String message, String header) {
        StringBuilder sb = new StringBuilder();
        if (tasks.isEmpty()) {
            sb.append(message).append("\n");
            return sb.toString();
        }
        sb.append(header).append("\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append((i + 1)).append(".").append(tasks.get(i).toString()).append("\n");
        }
        return sb.toString();
    }

    /**
     * Displays the complete list of tasks in the user's task list.
     *
     * @param tasks The {@code ArrayList} of {@link Task} objects to display.
     * @return A formatted string of the full task list.
     */
    public String displayList(ArrayList<Task> tasks) {
        String message = "You have no tasks!";
        String header = "Here are the tasks in your list:";
        return printMessage(tasks, message, header);
    }

    /**
     * Displays the filtered list of tasks that match certain criteria.
     *
     * @param tasks The {@code ArrayList} of {@link Task} objects to display.
     * @return A formatted string of the filtered task list.
     */
    public String showList(ArrayList<Task> tasks) {
        String message = "You have no matching tasks!";
        String header = "Here are the matching tasks in your list:";
        return printMessage(tasks, message, header);
    }

    /**
     * Displays an error message when an error is encountered.
     *
     * @param error A string containing the error message to display.
     */
    public String displayError(String error) {
        assert error != null : "Error message cannot be null!";
        return String.format("""
                 Note: %s
                """, error);
    }
}
