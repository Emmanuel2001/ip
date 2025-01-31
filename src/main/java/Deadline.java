public class Deadline extends Task {
    protected String by;

    public Deadline(String taskDescription, String by, boolean isDone) {
        super(taskDescription, TaskType.DEADLINE, isDone);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toFileFormat() {
        return String.format("D" + super.toFileFormatPrefix() + super.task + " | "
                             + this.by);
    }
}
