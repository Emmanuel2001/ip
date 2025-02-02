package buddytalk.ui;

public class ToDo extends Task {
    public ToDo(String taskDescription, boolean isDone) {
        super(taskDescription, TaskType.TODO, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toFileFormat() {
        return "T" + super.toFileFormatPrefix() + super.task;
    }
}
