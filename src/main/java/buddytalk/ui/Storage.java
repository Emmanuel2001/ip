package buddytalk.ui;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the task list to the disk.
     *
     * @param tasks the list of tasks to be saved
     * @throws IOException if an I/O error occurs
     */
    public void saveTasks(List<Task> tasks) throws IOException {
        Path parentDir = Paths.get(filePath).getParent();
        if (parentDir != null && !Files.exists(parentDir)) {
            Files.createDirectories(parentDir);
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : tasks) {
                writer.write(task.toFileFormat() + System.lineSeparator());
            }
        }
    }

    /**
     * Loads the task list from disk.
     *
     * @return the list of loaded tasks
     * @throws IOException          if an I/O error occurs
     * @throws BuddyException if the data file is corrupted
     */
    public ArrayList<Task> loadTasks() throws IOException, BuddyException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            Files.createFile(Paths.get(filePath));
            return tasks;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    Task task = parseTask(line);
                    tasks.add(task);
                } catch (IllegalArgumentException e) {
                    throw new BuddyException("Data file corrupted: " + e.getMessage());
                }
            }
        }

        return tasks;
    }

    /**
     * Parses a line from the file into a Task object.
     *
     * @param line the line to parse
     * @return the parsed Task
     * @throws BuddyException if the line format is invalid
     */
    private Task parseTask(String line) throws BuddyException {
        String[] parts = line.split(" \\| ");

        String taskType = parts[0];
        boolean isDone = Integer.parseInt(parts[1]) == 1;

        return switch (taskType) {
            case "T" -> new ToDo(parts[2], isDone);
            case "D" -> new Deadline(parts[2], parts[3], isDone);
            case "E" -> new Event(parts[2], parts[3], parts[4], isDone);
            default -> throw new BuddyException("Corrupted data file! Unable to parse task.");
        };
    }
}