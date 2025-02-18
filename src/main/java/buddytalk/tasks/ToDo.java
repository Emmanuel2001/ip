package buddytalk.tasks;

/**
 * Represents a "ToDo" task. A {@code ToDo} is a basic task with a description and completion status,
 * but no additional time or date information.
 * <p>
 * This class extends {@link Task} and implements specific formatting for representation
 * and file storage.
 * </p>
 */
public class ToDo extends Task {

    /**
     * Constructs a {@code ToDo} task with the specified description and completion status.
     *
     * @param taskDescription The description of the "ToDo" task.
     * @param isDone {@code true} if the task is completed, {@code false} otherwise.
     */
    public ToDo(String taskDescription, boolean isDone) {
        super(taskDescription, TaskType.TODO, isDone);
    }

    /**
     * Returns the string representation of the "ToDo" task for display to the user.
     * Prepends the task type "[T]" to the default string representation from {@link Task}.
     *
     * @return The string representation of the "ToDo" task (e.g., {@code "[T][X] Task description"}).
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Converts the "ToDo" task into a file-friendly format for storage.
     * Prepends the task type "T" to the common file format prefix and task description.
     *
     * @return The file-friendly string representation of the "ToDo" task.
     */
    @Override
    public String toFileFormat() {
        return "T" + super.toFileFormatPrefix() + super.task;
    }
}
