package buddytalk.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Represents an event task with a specific start and end time.
 * This class encapsulates both the event's time range and description
 * and provides functionality to display and save the event.
 */
public class Event extends Task {
    /** Formatter for input date and time, using the pattern "yyyy-MM-dd HHmm". */
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    /** Formatter for displaying date and time, using "MMM dd yyyy, h:mm a" in US locale. */
    private static final DateTimeFormatter SHOW_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a", Locale.US);

    /** The start time of the event as a {@code LocalDateTime}. */
    protected LocalDateTime from;

    /** The end time of the event as a {@code LocalDateTime}. */
    protected LocalDateTime to;
    /**
     * Constructs an Event task with the specified task description, start time, end time,
     * and completion status.
     *
     * @param taskDescription The description of the event task.
     * @param from A string representation of the start time in "yyyy-MM-dd HHmm" format.
     * @param to A string representation of the end time in "yyyy-MM-dd HHmm" format.
     * @param isDone True if the task is completed, false otherwise.
     */
    public Event(String taskDescription, String from, String to, boolean isDone) {
        super(taskDescription, TaskType.EVENT, isDone);
        this.from = LocalDateTime.parse(from, INPUT_FORMAT);
        this.to = LocalDateTime.parse(to, INPUT_FORMAT);
    }

    /**
     * Returns the string representation of the event task for display to the user.
     * The string includes the start and end times in a user-friendly format.
     *
     * @return The event's string representation, including its time range.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + from.format(SHOW_FORMAT) + " to: " + to.format(SHOW_FORMAT) + ")";
    }

    /**
     * Returns the string representation of the event task in a format
     * suitable for saving to a file.
     *
     * @return The formatted string representation of the event task for file storage.
     */
    @Override
    public String toFileFormat() {
        return "E" + super.toFileFormatPrefix()
                + super.task + " | " + this.from.format(INPUT_FORMAT) + " | " + this.to.format(INPUT_FORMAT);
    }

    /**
     * Retrieves the start time of the event.
     *
     * @return The event's start time as a {@code LocalDateTime}.
     */
    public LocalDateTime getFrom() {
        return from;
    }

    /**
     * Retrieves the end time of the event.
     *
     * @return The event's end time as a {@code LocalDateTime}.
     */
    public LocalDateTime getTo() {
        return to;
    }
}
