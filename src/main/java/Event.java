public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String taskDescription, String from, String to, boolean isDone) {
        super(taskDescription, TaskType.EVENT, isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    @Override
    public String toFileFormat() {
        return "E" + super.toFileFormatPrefix() + super.task + " | " + this.from + " | " + this.to;
    }
}
