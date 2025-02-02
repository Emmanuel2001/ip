package buddytalk.ui;

import org.junit.jupiter.api.Test;
import java.time.format.DateTimeFormatter;
import static org.junit.jupiter.api.Assertions.*;

class DeadlineTest {

    @Test
    void constructor_test() {
        String task = "Do 2109";
        String by = "2099-08-02 1500";
        boolean isDone = false;

        Deadline deadline = new Deadline(task, by, isDone);

        assertEquals(task, deadline.task);
        assertFalse(deadline.isDone);

        assertEquals("Aug 02 2099, 3:00 pm", deadline.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a")));
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
}