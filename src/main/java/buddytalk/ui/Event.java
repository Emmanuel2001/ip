package buddytalk.ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter DISPLAY_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");

    public Event(String taskDescription, String from, String to, boolean isDone) {
        super(taskDescription, TaskType.EVENT, isDone);
        this.from = LocalDateTime.parse(from, INPUT_FORMAT);
        this.to = LocalDateTime.parse(to, INPUT_FORMAT);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(DISPLAY_FORMAT) + " to: " + to.format(DISPLAY_FORMAT) + ")";
    }

    @Override
    public String toFileFormat() {
        return "E" + super.toFileFormatPrefix() + super.task + " | " + this.from.format(INPUT_FORMAT) + " | " + this.to.format(INPUT_FORMAT);
    }

    public LocalDateTime getFrom() {
        return from;
    }

    public LocalDateTime getTo() {
        return to;
    }
}
