import java.util.ArrayList;

public class Display {

    /**
     * Constructs a new {@code Display} object.
     */
    public Display() {}

    /**
     * Displays message when the program starts.
     */
    public static void start() {
        String start = """
                ____________________________________________________________
                 Hello! I'm BuddyTalk
                 What can I do for you?
                ____________________________________________________________
                """;
        start = start.indent(4);
        System.out.println(start);
    }

    /**
     * Displays message when the program exits.
     */
    public static void end() {
        String bye = """
                ____________________________________________________________
                 Bye. Hope to see you again soon!
                ____________________________________________________________
                """;
        bye = bye.indent(4);
        System.out.println(bye);
    }

    /**
     * Displays list of tasks.
     */
    public static void displayList(ArrayList<Task> list) {
        String bar = "____________________________________________________________";
        bar = bar.indent(4);
        System.out.print(bar);
        if (list.isEmpty()) {
            System.out.println("     " + "You have no tasks!");
        } else {
            System.out.println("     " + "Here are the tasks in your list:");
            for (int i = 0; i < list.size(); i++) {
                System.out.println("     " + (i + 1) + "." + list.get(i).toString());
            }
        }
        System.out.println(bar);
    }

    /**
     * Displays error message when an error is encountered.
     */
    public static void displayError(String error) {
        String str = String.format("""
                ____________________________________________________________
                 Error: %s
                ____________________________________________________________
                """, error);
        str = str.indent(4);
        System.out.println(str);
    }

    /**
     * Displays message when an action has been successfully executed.
     */
    public static void toPrint(String message) {
        String str = String.format("""
                ____________________________________________________________
                 %s
                ____________________________________________________________
                """, message);
        str = str.indent(4);
        System.out.println(str);
    }
}
