package buddytalk.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import buddytalk.tasks.Event;

class EventTest {

    @Test
    void constructor_test() {
        String task = "Do 2109";
        String to = "2100-08-02 1500";
        String from = "2099-08-02 1500";
        boolean isDone = false;

        Event event = new Event(task, from, to, isDone);

        assertEquals(task, event.getTask());
        assertFalse(event.isDone());

        assertEquals("Aug 02 2099, 3:00 pm", event.getFrom().format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a")));
    }

    @Test
    void toFileFormat_test() {
        String task = "Do 2109";
        String to = "2100-08-02 1500";
        String from = "2099-08-02 1500";
        boolean isDone = true;

        Event event = new Event(task, from, to, isDone);

        String expected = "E | 1 | Do 2109 | 2099-08-02 1500 | 2100-08-02 1500";
        assertEquals(expected, event.toFileFormat());
    }
}
