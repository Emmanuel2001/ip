package buddytalk.ui;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
//import java.util.Scanner;

/**
 * Represents the main BuddyTalk application. This class handles user input,
 * processes commands, and manages interactions between the task list, and storage.
 */
public class BuddyTalk {
    private TaskList list;
    private Storage storage;

    /**
     * Initializes the BuddyTalk application. Attempts to load tasks from the file
     * specified in the {@code filePath}. If the file doesn't exist or is corrupted,
     * starts with an empty task list.
     */
    public BuddyTalk(String filePath) {
        this.storage = new Storage(filePath);
        try {
            this.list = new TaskList(storage.loadTasks());
            Ui.toPrint("Tasks loaded successfully.");
        } catch (IOException e) {
            Ui.displayError("Failed to load tasks. A new file will be created.");
            this.list = new TaskList();
        } catch (BuddyException e) {
            Ui.displayError("The data file is corrupted. Starting with an empty task list.");
            this.list = new TaskList();
        }
    }

    /**
     * Starts the main application loop. Continuously waits for user input, parses commands,
     * and performs the corresponding actions until the "bye" command is issued.
     */
    public String run(String input) {
        //Scanner scanner = new Scanner(System.in);
        //while (true) {
        //String input = scanner.nextLine();
        try {
            Command command = Parser.parse(input);
            String commandText = command.getCommand().toLowerCase();

            switch (commandText) {
            case "bye" -> {
                return Ui.end();
            }
            case "list" -> {
                return Ui.displayList(list.getAllTasks());
            }
            case "mark" -> {
                int num = Integer.parseInt(command.getArguments());
                list.getTask(num - 1).mark();
                storage.saveTasks(list.getAllTasks());
                String text = String.format("Nice! I've marked this task as done: \n  %s ",
                        list.getTask(num - 1).toString());
                return Ui.toPrint(text);
            }
            case "unmark" -> {
                int num = Integer.parseInt(command.getArguments());
                list.getTask(num - 1).unmark();
                storage.saveTasks(list.getAllTasks());
                String text = String.format("OK, I've marked this task as not done yet: \n  %s",
                        list.getTask(num - 1).toString());
                return Ui.toPrint(text);
            }
            case "find" -> {
                ArrayList<Task> items = list.findTasks(command.getArguments());
                return Ui.showList(items);
            }
            case "todo" -> {
                try {
                    if (input.length() < 5) {
                        throw new IllegalAccessException();
                    }
                    ToDo task = new ToDo(command.getArguments(), false);
                    saveTask(task);
                    String text = String.format("Got it. I've added this task: \n  %s \n "
                            + "Now you have %d tasks in the list.", task.toString(), list.size());
                    return Ui.toPrint(text);
                } catch (IllegalAccessException e) {
                    return Ui.displayError("Description for ToDo cannot be empty.");
                }
            }
            case "deadline" -> {
                String[] parts = command.getArguments().split(" /by ");

                if (parts.length != 2) {
                    return Ui.toPrint("Invalid format for 'deadline'. "
                            + "Please use: deadline <task> /by <yyyy-mm-dd>");
                } else {
                    try {
                        String desc = parts[0].trim();
                        String date = parts[1].trim();
                        Deadline task = new Deadline(desc, date, false);
                        saveTask(task);
                        String text = String.format(
                                "Got it. I've added this task: \n  %s \n Now you have %d tasks in the list.",
                                task.toString(), list.size()
                        );
                        return Ui.toPrint(text);
                    } catch (Exception e) {
                        return Ui.displayError("An error occurred while processing the 'deadline' task."
                                + " Please ensure it is in the format: yyyy-MM-dd HHmm.");
                    }

                }
            }
            case "event" -> {
                try {
                    String[] parts = command.getArguments().split(" /from ");

                    if (parts.length != 2 || !parts[1].contains(" /to ")) {
                        return Ui.toPrint("Invalid format for 'event'. "
                                + "Please use: event <description> /from <yyyy-MM-dd HHmm> /to <yyyy-MM-dd HHmm>");
                    } else {
                        String desc = parts[0].trim();
                        String[] time = parts[1].split(" /to ");
                        String startTime = time[0].trim();
                        String endTime = time[1].trim();

                        Event task = new Event(desc, startTime, endTime, false);
                        saveTask(task);
                        String text = String.format(
                                "Got it. I've added this task: \n  %s \n Now you have %d tasks in the list.",
                                task.toString(), list.size()
                        );
                        return Ui.toPrint(text);
                    }
                } catch (DateTimeParseException e) {
                    return Ui.displayError("Invalid date or time format. Please use: yyyy-MM-dd HHmm");

                } catch (Exception e) {
                    return Ui.displayError("An error occurred while processing the 'event' task. Please try again.");
                }
            }
            case "delete" -> {
                int num = Integer.parseInt(command.getArguments());
                if (num > list.size()) {
                    return Ui.toPrint("Invalid task number. Please try again.");
                }
                String temp = list.getTask(num - 1).toString();
                list.deleteTask(num - 1);
                String text = String.format("Noted. I've removed this task: \n  %s \n "
                        + "Now you have %d tasks in the list.", temp, list.size());
                return Ui.toPrint(text);
            }
            default -> {
                String text = "OOPS!!! I'm sorry, but I don't know what that means :-(";
                return Ui.toPrint(text);
            }
            }
        } catch (IOException e) {
            return Ui.displayError("Failed to save tasks: " + e.getMessage());
        } catch (BuddyException e) {
            return Ui.displayError(e.getMessage());
        }
        //}
    }

    /**
     * Saves a new task to the task list and persists it to storage.
     *
     * @param task The task to be added and saved.
     */
    public String saveTask(Task task) {
        this.list.addTask(task);

        try {
            storage.saveTasks(list.getAllTasks());
            return Ui.toPrint("Task saved successfully.");
        } catch (IOException e) {
            return Ui.displayError("Failed to save tasks: " + e.getMessage());
        }
    }

    /**
     * The main entry point of the application. Starts BuddyTalk.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        new BuddyTalk("data/BuddyTalk.txt").run(args[0]);
    }
}
