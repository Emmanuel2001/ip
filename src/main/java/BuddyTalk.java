import java.util.ArrayList;
import java.util.Scanner;

public class BuddyTalk {
    private static ArrayList<Task> list = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Display.start();

        boolean exit = false;
        while (!exit) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                exit = true;
                Display.end();
            } else if (input.equals("list")) {
                Display.displayList(list);
            } else if (input.startsWith("mark")) {
                String[] parts = input.split(" ");
                int num = Integer.parseInt(parts[1]);
                list.get(num - 1).mark();
                String text = String.format("Nice! I've marked this task as done: \n  %s ", list.get(num - 1).toString());
                Display.toPrint(text);
            } else if (input.startsWith("unmark")) {
                String[] parts = input.split(" ");
                int num = Integer.parseInt(parts[1]);
                list.get(num - 1).unmark();
                String text = String.format("OK, I've marked this task as not done yet: \n  %s", list.get(num - 1).toString());
                Display.toPrint(text);
            } else {
                if (input.startsWith("todo")) {
                    try {
                        if (input.length() < 5) {
                            throw new Exception();
                        }
                        ToDo task = new ToDo(input.substring(5));
                        list.add(task);
                        String text = String.format("Got it. I've added this task: \n  %s \n Now you have %d tasks in the list.", task.toString(), list.size());
                        Display.toPrint(text);
                    } catch (Exception e) {
                        Display.toPrint("An error occurred while adding the 'todo' task. Please check your input format and try again.");
                    }
                } else if (input.startsWith("deadline")) {
                    String[] parts = input.split(" /by ");

                    if (parts.length != 2) {
                        Display.toPrint("Invalid format for 'deadline'. Please use: deadline <task> /by <date>");
                    } else {
                        String desc = parts[0].substring(9).trim();
                        String d_Date = parts[1].trim();
                        Deadline task = new Deadline(desc, d_Date);
                        list.add(task);
                        String text = String.format(
                                "Got it. I've added this task: \n  %s \n Now you have %d tasks in the list.",
                                task.toString(), list.size()
                        );
                        Display.toPrint(text);
                    }
                } else if (input.startsWith("event")) {
                    try {
                        String[] parts = input.split(" /from ");

                        if (parts.length != 2 || !parts[1].contains(" /to ")) {
                            Display.toPrint("Invalid format for 'event'. Please use: event <description> /from <start time> /to <end time>");
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
                            Display.toPrint(text);
                        }
                    } catch (Exception e) {
                        Display.toPrint("An error occurred while processing the 'event' task. Please try again.");
                    }
                } else if (input.startsWith("delete")) {
                    String[] parts = input.split(" ");
                    if (parts.length != 2) {
                        Display.toPrint("Invalid format for 'delete'. Please use: delete <task number>");
                    } else {
                        int num = Integer.parseInt(parts[1]);
                        if (num > list.size()) {
                            Display.toPrint("Invalid task number. Please try again.");
                        }
                        String temp = list.get(num - 1).toString();
                        list.remove(num - 1);
                        String text = String.format("Noted. I've removed this task: \n  %s \n Now you have %d tasks in the list.", temp, list.size());
                        Display.toPrint(text);
                    }
                } else {
                    String text = "OOPS!!! I'm sorry, but I don't know what that means :-(";
                    Display.toPrint(text);
                }
            }
        }
    }
}
