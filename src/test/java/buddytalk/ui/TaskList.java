package buddytalk.ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {

    private TaskList taskList;

    @BeforeEach
    void setUp() {
        // Initialize a new TaskList before each test
        taskList = new TaskList();
    }

    @Test
    void addTask_test() {
        // Create a mock Task
        Task task = new ToDo("Read a book", false);

        // Add the task
        taskList.addTask(task);

        // Verify the task was added
        assertEquals(1, taskList.size());
        assertEquals(task, taskList.getTask(0));
    }

    @Test
    void deleteTask_test() {
        Task task1 = new ToDo("Task 1", false);
        Task task2 = new ToDo("Task 2", true);

        taskList.addTask(task1);
        taskList.addTask(task2);
        taskList.deleteTask(0);

        assertEquals(1, taskList.size());
        assertEquals(task2, taskList.getTask(0));
    }
}