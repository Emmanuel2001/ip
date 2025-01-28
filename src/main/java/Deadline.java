public class Deadline extends Task {
    protected String by;

    public Deadline(String taskDescription, String by) {
        super(taskDescription, TaskType.DEADLINE);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
