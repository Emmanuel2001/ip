package buddytalk.ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime by;
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter DISPLAY_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");

    public Deadline(String taskDescription, String by, boolean isDone) {
        super(taskDescription, TaskType.DEADLINE, isDone);
        this.by = LocalDateTime.parse(by, INPUT_FORMAT);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DISPLAY_FORMAT) + ")";
    }

    @Override
    public String toFileFormat() {
        return String.format("D" + super.toFileFormatPrefix() + super.task + " | "
                             + this.by.format(INPUT_FORMAT));
    }

    public LocalDateTime getDeadline() {
        return this.by;
    }
}
