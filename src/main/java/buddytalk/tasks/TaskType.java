package buddytalk.tasks;

/**
 * Enumeration of task types supported by the application.
 *
 * <p>
 * Each task type corresponds to a specific type of task
 * supported by the application and reflects its unique attributes:
 * </p>
 * <ul>
 *     <li>{@link #TODO} - A basic task with only a description.</li>
 *     <li>{@link #DEADLINE} - A task with an associated deadline.</li>
 *     <li>{@link #EVENT} - A task with a start and end time.</li>
 * </ul>
 */
public enum TaskType {
    /**
     * Represents a simple task with a description only.
     * Example: "todo Read a book."
     */
    TODO,

    /**
     * Represents a task with a description and a deadline.
     * Example: "deadline Submit report by 5 PM."
     */
    DEADLINE,

    /**
     * Represents a task with a description, a start time, and an end time.
     * Example: "event Meeting from 2 PM to 3 PM."
     */
    EVENT
}
