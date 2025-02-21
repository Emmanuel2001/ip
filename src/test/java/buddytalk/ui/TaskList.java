package buddytalk.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import buddytalk.tasks.Deadline;
import buddytalk.tasks.Event;
import buddytalk.tasks.Task;
import buddytalk.tasks.TaskList;
import buddytalk.tasks.ToDo;

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

    @Test
    void deleteLastTask_test() {
        taskList.addTask(new ToDo("Task 1", false));
        taskList.addTask(new ToDo("Task 2", true));
        taskList.deleteTask(1); // Delete the last task

        assertEquals(1, taskList.size());
        assertEquals("Task 1", taskList.getTask(0).getTask());
    }

    @Test
    void addMultipleTasks_test() {
        Task task1 = new ToDo("Task 1", false);
        Task task2 = new Deadline("Task 2", "2099-08-02 1500", true);
        Task task3 = new Event("Task 3", "2099-08-01 0800", "2099-08-01 1700", false);

        taskList.addTask(task1);
        taskList.addTask(task2);
        taskList.addTask(task3);

        assertEquals(3, taskList.size());
        assertEquals(task1, taskList.getTask(0));
        assertEquals(task2, taskList.getTask(1));
        assertEquals(task3, taskList.getTask(2));
    }
}
