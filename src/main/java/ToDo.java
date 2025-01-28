public class ToDo extends Task {
    public ToDo(String taskDescription) {
        super(taskDescription, TaskType.TODO); // Inherit task description from Task
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
