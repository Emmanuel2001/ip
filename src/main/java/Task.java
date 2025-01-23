public class Task {
    private boolean isDone;
    private String task;

    public Task(String task) {
        this.task = task;
        this.isDone = false;
    }

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
        return (isDone ? "[X]" : "[ ]") + " " + task;
    }
}