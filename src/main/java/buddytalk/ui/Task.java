package buddytalk.ui;

/**
 * Represents a generic task. A {@code Task} object encapsulates the
 * characteristics of a task, such as its description, completion status,
 * and task type.
 * <p>
 * The {@code Task} class provides a base for specific task types (e.g., {@code ToDo},
 * {@code Deadline}, {@code Event}) and includes common functionality such as
 * marking tasks as done/undone and returning task information in various formats.
 * </p>
 */
public abstract class Task {

    /** Indicates whether the task is completed. */
    protected boolean isDone;

    /** The description of the task. */
    protected String task;

    /** The type of the task (e.g., TODO, DEADLINE, EVENT). */
    protected TaskType taskType;

    /**
     * Constructs a {@code Task} object with the specified description, task type, and completion status.
     *
     * @param task The description of the task.
     * @param taskType The type of the task (e.g., {@code TaskType.TODO}, {@code TaskType.DEADLINE}).
     * @param isDone {@code true} if the task is completed, {@code false} otherwise.
     */
    public Task(String task, TaskType taskType, boolean isDone) {
        this.task = task;
        this.isDone = isDone;
        this.taskType = taskType;
    }

    /**
     * Returns the task's completion status in a file-friendly prefix format.
     * <p>The format is: {@code " | 1 | "} if the task is completed, or {@code " | 0 | "} otherwise.</p>
     *
     * @return The file-friendly prefix for the task's completion status.
     */
    public String toFileFormatPrefix() {
        return isDone ? " | 1 | " : " | 0 | ";
    }

    /**
     * Retrieves the type of the task.
     *
     * @return The {@code TaskType} of the task (e.g., TODO, DEADLINE, EVENT).
     */
    public TaskType getTaskType() {
        return taskType;
    }

    /**
     * Retrieves the task's status icon.
     * <p>The icon indicates whether the task is completed:</p>
     * <ul>
     *     <li>{@code "[X]"} if the task is completed.</li>
     *     <li>{@code "[ ]"} if the task is not completed.</li>
     * </ul>
     *
     * @return The status icon as a string.
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Checks whether the task is completed.
     *
     * @return {@code true} if the task is completed, {@code false} otherwise.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Retrieves the description of the task.
     *
     * @return The task description as a string.
     */
    public String getTask() {
        return task;
    }

    /**
     * Marks the task as completed.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Marks the task as not completed.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task for display to the user.
     * The format includes the status icon and task description.
     *
     * @return The string representation of the task (e.g., {@code "[X] Task description"}).
     */
    @Override
    public String toString() {
        return String.format("%s %s", getStatusIcon(), task);
    }

    /**
     * Converts the task into a file-friendly format, suitable for saving to disk.
     *
     * @return The formatted string representation of the task for file storage.
     */
    public abstract String toFileFormat();
}