import java.util.ArrayList;
import java.util.Scanner;

public class BuddyTalk {
    public static void main(String[] args) {
        ArrayList<Task> list = new ArrayList<>();

        String start = """
                ____________________________________________________________
                 Hello! I'm BuddyTalk
                 What can I do for you?
                ____________________________________________________________
                """;
        start = start.indent(4);

        String bye = """
                ____________________________________________________________
                 Bye. Hope to see you again soon!
                ____________________________________________________________
                """;
        bye = bye.indent(4);

        String bar = "____________________________________________________________";
        bar = bar.indent(4);

        System.out.println(start);

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                exit = true;
                System.out.println(bye);
            } else if (input.equals("list")){
                System.out.print(bar);
                for (int i = 0; i < list.size(); i++) {
                    int j = i + 1;
                    System.out.println("     " + j + "." + list.get(i).toString());
                }
                System.out.println(bar);
            } else if (input.startsWith("mark")) {
                String[] parts = input.split(" ");
                int num = Integer.parseInt(parts[1]);
                list.get(num - 1).mark();
                String text = String.format("""
                        ____________________________________________________________
                         Nice! I've marked this task as done:
                           %s
                        ____________________________________________________________
                        """, list.get(num - 1).toString());
                text = text.indent(4);
                System.out.println(text);
            } else if (input.startsWith("unmark")) {
                String[] parts = input.split(" ");
                int num = Integer.parseInt(parts[1]);
                list.get(num - 1).unmark();
                String text = String.format("""
                        ____________________________________________________________
                         OK, I've marked this task as not done yet:
                           %s
                        ____________________________________________________________
                        """, list.get(num - 1).toString());
                text = text.indent(4);
                System.out.println(text);
            } else {
                Task task = new Task(input);
                list.add(task);
                String text = String.format("""
                        ____________________________________________________________
                         added: %s
                        ____________________________________________________________
                        """, input);
                text = text.indent(4);
                System.out.println(text);
            }
        }
    }
}
