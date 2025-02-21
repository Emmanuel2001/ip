package buddytalk.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Represents a task with a deadline. This class encapsulates the deadline
 * information of the task and provides functionality to display and save the task
 * with its deadline details.
 */
public class Deadline extends Task {
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter SHOW_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a", Locale.US);
    private LocalDateTime by;
    /**
     * Constructs a Deadline task with the specified task description, deadline,
     * and completion status.
     *
     * @param taskDescription The description of the task.
     * @param by A string representation of the deadline in "yyyy-MM-dd HHmm" format.
     * @param isDone True if the task is completed, false otherwise.
     */
    public Deadline(String taskDescription, String by, boolean isDone) {
        super(taskDescription, TaskType.DEADLINE, isDone);
        this.by = LocalDateTime.parse(by, INPUT_FORMAT);
    }

    /**
     * Returns the string representation of the deadline task
     * in a format suitable for display to the user.
     *
     * @return The formatted string representation of the task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(SHOW_FORMAT) + ")";
    }

    /**
     * Returns the string representation of the deadline task in a format
     * suitable for saving to a file.
     *
     * @return The formatted string representation of the task for file storage.
     */
    @Override
    public String toFileFormat() {
        return String.format("D" + super.toFileFormatPrefix() + super.task + " | "
                + this.by.format(INPUT_FORMAT));
    }

    /**
     * Retrieves the deadline of the task as a {@code LocalDateTime} object.
     *
     * @return The deadline of the task.
     */
    public LocalDateTime getDeadline() {
        return this.by;
    }
}
