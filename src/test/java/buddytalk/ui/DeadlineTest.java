package buddytalk.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import buddytalk.tasks.Deadline;

class DeadlineTest {

    @Test
    void constructor_test() {
        String task = "Do 2109";
        String by = "2099-08-02 1500";
        boolean isDone = false;

        Deadline deadline = new Deadline(task, by, isDone);

        assertEquals(task, deadline.getTask());
        assertFalse(deadline.isDone());

        assertEquals("Aug 02 2099, 3:00 pm",
                deadline.getDeadline().format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a")));
    }

    @Test
    void toFileFormat_test() {
        String description = "Do 2109";
        String by = "2099-08-02 1500";
        boolean isDone = true;

        Deadline deadline = new Deadline(description, by, isDone);

        String expected = "D | 1 | Do 2109 | 2099-08-02 1500";
        assertEquals(expected, deadline.toFileFormat());
    }

    @Test
    void invalidDateFormat_test() {
        String task = "Do something";
        String by = "2099/08/02 1500"; // Invalid format
        boolean isDone = false;

        Exception exception = null;
        try {
            new Deadline(task, by, isDone);
        } catch (Exception e) {
            exception = e;
        }

        assertEquals("Text '2099/08/02 1500' could not be parsed at index 4", exception.getMessage());
    }
}
