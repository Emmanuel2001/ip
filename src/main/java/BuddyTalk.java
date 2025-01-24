import java.util.ArrayList;
import java.util.Scanner;

public class BuddyTalk {
    private static ArrayList<Task> list = new ArrayList<>();
    private static String bar = "____________________________________________________________";

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

    public static void end() {
        String bye = """
                ____________________________________________________________
                 Bye. Hope to see you again soon!
                ____________________________________________________________
                """;
        bye = bye.indent(4);
        System.out.println(bye);
    }

    public static void toPrint(String input) {
        String text = String.format("""
                ____________________________________________________________
                 %s
                ____________________________________________________________
                """, input);

        text = text.indent(4);
        System.out.println(text);
    }

    public static void list() {
        bar = bar.indent(4);
        System.out.print(bar);
        for (int i = 0; i < list.size(); i++) {
            int j = i + 1;
            System.out.println("     " + j + "." + list.get(i).toString());
        }
        System.out.println(bar);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        start();

        boolean exit = false;
        while (!exit) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                exit = true;
                end();
            } else if (input.equals("list")) {
                list();
            } else if (input.startsWith("mark")) {
                String[] parts = input.split(" ");
                int num = Integer.parseInt(parts[1]);
                list.get(num - 1).mark();
                String text = String.format("Nice! I've marked this task as done: \n  %s ", list.get(num - 1).toString());
                toPrint(text);
            } else if (input.startsWith("unmark")) {
                String[] parts = input.split(" ");
                int num = Integer.parseInt(parts[1]);
                list.get(num - 1).unmark();
                String text = String.format("OK, I've marked this task as not done yet: \n  %s", list.get(num - 1).toString());
                toPrint(text);
            } else {
                if (input.startsWith("todo")) {
                    try {
                        if (input.length() < 5) {
                            throw new Exception();
                        }
                        ToDo task = new ToDo(input.substring(5));
                        list.add(task);
                        String text = String.format("Got it. I've added this task: \n  %s \n Now you have %d tasks in the list.", task.toString(), list.size());
                        toPrint(text);
                    } catch (Exception e) {
                        toPrint("An error occurred while adding the 'todo' task. Please check your input format and try again.");
                    }
                } else if (input.startsWith("deadline")) {
                    String[] parts = input.split(" /by ");

                    if (parts.length != 2) {
                        toPrint("Invalid format for 'deadline'. Please use: deadline <task> /by <date>");
                    } else {
                        String desc = parts[0].substring(9).trim();
                        String d_Date = parts[1].trim();
                        Deadline task = new Deadline(desc, d_Date);
                        list.add(task);
                        String text = String.format(
                                "Got it. I've added this task: \n  %s \n Now you have %d tasks in the list.",
                                task.toString(), list.size()
                        );
                        toPrint(text);
                    }
                } else if (input.startsWith("event")) {
                    try {
                        String[] parts = input.split(" /from ");

                        if (parts.length != 2 || !parts[1].contains(" /to ")) {
                            toPrint("Invalid format for 'event'. Please use: event <description> /from <start time> /to <end time>");
                        } else {
                            String desc = parts[0].substring(6).trim();
                            String[] time = parts[1].split(" /to ");
                            String startTime = time[0].trim();
                            String endTime = time[1].trim();

                            Event task = new Event(desc, startTime, endTime);
                            list.add(task);
                            String text = String.format(
                                    "Got it. I've added this task: \n  %s \n Now you have %d tasks in the list.",
                                    task.toString(), list.size()
                            );
                            toPrint(text);
                        }
                    } catch (Exception e) {
                        toPrint("An error occurred while processing the 'event' task. Please try again.");
                    }
                } else if (input.startsWith("delete")) {
                    String[] parts = input.split(" ");
                    if (parts.length != 2) {
                        toPrint("Invalid format for 'delete'. Please use: delete <task number>");
                    } else {
                        int num = Integer.parseInt(parts[1]);
                        if (num > list.size()) {
                            toPrint("Invalid task number. Please try again.");
                        }
                        String temp = list.get(num - 1).toString();
                        list.remove(num - 1);
                        String text = String.format("Noted. I've removed this task: \n  %s \n Now you have %d tasks in the list.", temp, list.size());
                        toPrint(text);
                    }
                } else {
                    String text = "OOPS!!! I'm sorry, but I don't know what that means :-(";
                    toPrint(text);
                }
            }
        }
    }
}
