package buddytalk.ui;

import java.util.ArrayList;

/**
 * Represents a list of tasks. The {@code TaskList} class provides methods to manage the collection
 * of {@link Task} objects, such as adding, deleting, retrieving, and getting the size of the list.
 */
public class TaskList {

    /** The list of tasks stored in this {@code TaskList}. */
    protected ArrayList<Task> tasks;

    /**
     * Constructs an empty {@code TaskList}.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a {@code TaskList} with an initial list of tasks.
     *
     * @param tasks The initial list of tasks to populate the {@code TaskList}.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The {@link Task} to be added to the list.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the task list at the specified index.
     *
     * @param index The index of the task to delete (0-based indexing).
     * @throws IndexOutOfBoundsException If the index is out of range (less than 0 or greater than or equal to the list size).
     */
    public void deleteTask(int index) throws IndexOutOfBoundsException {
        tasks.remove(index);
    }

    /**
     * Retrieves a task from the task list at the specified index.
     *
     * @param index The index of the task to retrieve (0-based indexing).
     * @return The {@link Task} at the specified index.
     * @throws IndexOutOfBoundsException If the index is out of range (less than 0 or greater than or equal to the list size).
     */
    public Task getTask(int index) throws IndexOutOfBoundsException {
        return tasks.get(index);
    }

    /**
     * Retrieves all the tasks in the task list.
     *
     * @return An {@code ArrayList} of all the {@link Task} objects in the list.
     */
    public ArrayList<Task> getAllTasks() {
        return tasks;
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return The size of the task list.
     */
    public int size() {
        return tasks.size();
    }
}