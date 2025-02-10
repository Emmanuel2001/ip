package buddytalk.ui;

import java.util.ArrayList;

/**
 * Handles all user interface interactions for the BuddyTalk application.
 * <p>
 * The {@code Ui} class provides static methods for displaying messages, task lists,
 * and error messages to the user through the console.
 * </p>
 */
public class Ui {

    /**
     * Constructs a new {@code Ui} object.
     * <p>This constructor is optional since all methods in this class are static,
     * meaning the object does not need to be instantiated.</p>
     */
    public Ui() {}

    /**
     * Displays the welcome message when the BuddyTalk program starts.
     */
    public static String start() {
        String start = """
                 Hello! I'm BuddyTalk
                 What can I do for you?
                """;
        //start = start.indent(4);
        //System.out.println(start);
        return start;
    }

    /**
     * Displays the farewell message when the BuddyTalk program exits.
     */
    public static String end() {
        String bye = """
                 Bye. Hope to see you again soon!
                """;
        //bye = bye.indent(4);
        //System.out.println(bye);
        return bye;
    }

    /**
     * Displays the list of tasks to the user.
     * <p>If the task list is empty, a message indicating that no tasks are present is displayed.
     * Otherwise, all tasks in the list are printed along with their index.</p>
     *
     * @param list The {@code ArrayList<Task>} containing tasks to display.
     */
    public static String displayList(ArrayList<Task> list) {
        //String bar = "____________________________________________________________";
        //bar = bar.indent(4);
        //System.out.print(bar);
        StringBuilder sb = new StringBuilder();
        if (list.isEmpty()) {
            //System.out.println("     " + "You have no tasks!");
            sb.append("You have no tasks!\n");
        } else {
            //System.out.println("     " + "Here are the tasks in your list:");
            sb.append("Here are the tasks in your list:\n");
            for (int i = 0; i < list.size(); i++) {
                //System.out.println("     " + (i + 1) + "." + list.get(i).toString());
                sb.append((i + 1)).append(".").append(list.get(i).toString()).append("\n");
            }
        }
        //System.out.println(bar);
        //sb.append(bar);
        return sb.toString();
    }

    /**
     * Displays the list of tasks with proper formatting.
     *
     * If the list is empty, the method prints a message stating that there are no matching tasks.
     * Otherwise, it prints a formatted list of tasks with their index numbers.
     *
     * @param list The {@code ArrayList} of {@link Task} objects to be displayed.
     *             Each task is shown with its index number in the list.
     */
    public static String showList(ArrayList<Task> list) {
        //String bar = "____________________________________________________________";
        //bar = bar.indent(4);
        //System.out.print(bar);
        StringBuilder sb = new StringBuilder();
        //sb.append(bar).append("\n");
        if (list.isEmpty()) {
            //System.out.println("     " + "You have no matching tasks!");
            sb.append("You have no matching tasks!\n");
        } else {
            //System.out.println("     " + "Here are the matching tasks in your list:");
            sb.append("Here are the matching tasks in your list:\n");
            for (int i = 0; i < list.size(); i++) {
                //System.out.println("     " + (i + 1) + "." + list.get(i).toString());
                sb.append((i + 1)).append(".").append(list.get(i).toString()).append("\n");
            }
        }
        //System.out.println(bar);
        //sb.append(bar);
        return sb.toString();
    }

    /**
     * Displays an error message when an error is encountered.
     *
     * @param error A string containing the error message to display.
     */
    public static String displayError(String error) {
        //str = str.indent(4);
        //System.out.println(str);
        return String.format("""
                 Error: %s
                """, error);
    }

    /**
     * Displays a custom message indicating the result of an action.
     *
     * @param message The message to display, typically describing a successfully performed action.
     */
    public static String toPrint(String message) {
        //str = str.indent(4);
        //System.out.println(str);
        return String.format("""
                 %s
                """, message);
    }
}
