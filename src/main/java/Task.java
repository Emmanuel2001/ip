public class Task {
    protected boolean isDone;
    protected String task;
    protected TaskType taskType;

    public Task(String task, TaskType taskType) {
        this.task = task;
        this.isDone = false;
        this.taskType = taskType;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    };

    public boolean isDone() {
        return isDone;
    }

    public String getTask() {
        return task;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return String.format("%s %s", getStatusIcon(), task);
    }
}