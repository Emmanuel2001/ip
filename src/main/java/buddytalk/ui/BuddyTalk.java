package buddytalk.ui;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class BuddyTalk {
    private static final String FILE_PATH = "data/BuddyTalk.txt";
    private TaskList list;
    private Storage storage;
    //private Ui ui;

    public BuddyTalk() {
        this.storage = new Storage(FILE_PATH); // Initialize with the file path
        try {
            this.list = new TaskList(storage.loadTasks()); // Load tasks from the file on startup
            System.out.println("Tasks loaded successfully.");
        } catch (IOException e) {
            System.out.println("Failed to load tasks. A new file will be created.");
            this.list = new TaskList(); // Start with an empty list if file doesn't load
        } catch (BuddyException e) {
            System.out.println("The data file is corrupted. Starting with an empty task list.");
            this.list = new TaskList(); // Start fresh on data corruption
        }
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        Ui.start();

        boolean exit = false;
        while (!exit) {
            String input = scanner.nextLine();
            try {
                Command command = Parser.parse(input);
                String c = command.getCommand().toLowerCase();

                switch (c) {
                    case "bye" -> {
                        exit = true;
                        Ui.end();
                    }
                    case "list" -> Ui.displayList(list.getAllTasks());
                    case "mark" -> {
                        String[] parts = input.split(" ");
                        int num = Integer.parseInt(parts[1]);
                        list.getTask(num - 1).mark();
                        storage.saveTasks(list.getAllTasks());
                        String text = String.format("Nice! I've marked this task as done: \n  %s ", list.getTask(num - 1).toString());
                        Ui.toPrint(text);
                    }
                    case "unmark" -> {
                        String[] parts = input.split(" ");
                        int num = Integer.parseInt(parts[1]);
                        list.getTask(num - 1).unmark();
                        storage.saveTasks(list.getAllTasks());
                        String text = String.format("OK, I've marked this task as not done yet: \n  %s", list.getTask(num - 1).toString());
                        Ui.toPrint(text);
                    }
                    case "find" -> {
                        ArrayList<Task> items = list.findTasks(command.getArguments());
                        Ui.showList(items);
                    }
                    default -> {
                        switch (c) {
                            case "todo" -> {
                                try {
                                    if (input.length() < 5) {
                                        throw new Exception();
                                    }
                                    ToDo task = new ToDo(input.substring(5), false);
                                    saveTask(task);
                                    String text = String.format("Got it. I've added this task: \n  %s \n Now you have %d tasks in the list.", task.toString(), list.size());
                                    Ui.toPrint(text);
                                } catch (Exception e) {
                                    Ui.toPrint("An error occurred while adding the 'todo' task. Please check your input format and try again.");
                                }
                            }
                            case "deadline" -> {
                                String[] parts = input.split(" /by ");

                                if (parts.length != 2) {
                                    Ui.toPrint("Invalid format for 'deadline'. Please use: deadline <task> /by <yyyy-mm-dd>");
                                } else {
                                    try {
                                        String desc = parts[0].substring(9).trim();
                                        String d_Date = parts[1].trim();
                                        Deadline task = new Deadline(desc, d_Date, false);
                                        saveTask(task);
                                        String text = String.format(
                                                "Got it. I've added this task: \n  %s \n Now you have %d tasks in the list.",
                                                task.toString(), list.size()
                                        );
                                        Ui.toPrint(text);
                                    } catch (Exception e) {
                                        Ui.displayError("An error occurred while processing the 'deadline' task. Please ensure it is in the format: yyyy-MM-dd HHmm.");
                                    }

                                }
                            }
                            case "event" -> {
                                try {
                                    String[] parts = input.split(" /from ");

                                    if (parts.length != 2 || !parts[1].contains(" /to ")) {
                                        Ui.toPrint("Invalid format for 'event'. Please use: event <description> /from <yyyy-MM-dd HHmm> /to <yyyy-MM-dd HHmm>");
                                    } else {
                                        String desc = parts[0].substring(6).trim();
                                        String[] time = parts[1].split(" /to ");
                                        String startTime = time[0].trim();
                                        String endTime = time[1].trim();

                                        Event task = new Event(desc, startTime, endTime, false);
                                        saveTask(task);
                                        String text = String.format(
                                                "Got it. I've added this task: \n  %s \n Now you have %d tasks in the list.",
                                                task.toString(), list.size()
                                        );
                                        Ui.toPrint(text);
                                    }
                                } catch (DateTimeParseException e) {
                                    Ui.displayError("Invalid date or time format. Please use: yyyy-MM-dd HHmm");

                                } catch (Exception e) {
                                    Ui.displayError("An error occurred while processing the 'event' task. Please try again.");
                                }
                            }
                            case "delete" -> {
                                String[] parts = input.split(" ");
                                if (parts.length != 2) {
                                    Ui.toPrint("Invalid format for 'delete'. Please use: delete <task number>");
                                } else {
                                    int num = Integer.parseInt(parts[1]);
                                    if (num > list.size()) {
                                        Ui.toPrint("Invalid task number. Please try again.");
                                    }
                                    String temp = list.getTask(num - 1).toString();
                                    list.deleteTask(num - 1);
                                    String text = String.format("Noted. I've removed this task: \n  %s \n Now you have %d tasks in the list.", temp, list.size());
                                    Ui.toPrint(text);
                                }
                            }
                            default -> {
                                String text = "OOPS!!! I'm sorry, but I don't know what that means :-(";
                                Ui.toPrint(text);
                            }
                        }
                    }
                }
            } catch (IOException e) {
                System.out.println("Failed to save tasks: " + e.getMessage());
            } catch (BuddyException e) {
                Ui.displayError(e.getMessage());
            }
        }
    }

    public void saveTask(Task task) {
        this.list.addTask(task);

        try {
            storage.saveTasks(list.getAllTasks());
            System.out.println("Task saved successfully.");
        } catch (IOException e) {
            System.out.println("Failed to save tasks: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new BuddyTalk().run();
    }
}
