package buddytalk.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import buddytalk.exceptions.BuddyException;
import buddytalk.tasks.Deadline;
import buddytalk.tasks.Event;
import buddytalk.tasks.Task;
import buddytalk.tasks.ToDo;

/**
 * Handles the saving and loading of tasks to and from the disk.
 * The {@code Storage} class is responsible for managing file operations for data persistence.
 */
public class Storage {
    /** The file path to store or load task data. */
    private final String filePath;

    /**
     * Constructs a {@code Storage} object with the specified file path for storing data.
     *
     * @param filePath The absolute path of the file to be used for saving and loading tasks.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the list of tasks to a file at the specified {@code filePath}. Creates parent directories
     * if they do not exist. Each task is written in a format defined by the task's {@code toFileFormat()} method.
     *
     * @param tasks The list of tasks to be saved to the file.
     * @throws IOException If an I/O error occurs during the writing process.
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
     * Loads tasks from the file at the specified {@code filePath}. If the file does not exist,
     * it creates a new file and returns an empty task list. Tasks are reconstructed based on
     * the stored file format.
     *
     * @return A list of tasks loaded from the file.
     * @throws IOException If an I/O error occurs during the reading process.
     * @throws BuddyException If the file data is corrupted or improperly formatted.
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
     * Parses a line from the file into a {@code Task} object. The line must be in a formatted structure
     * that specifies the type of task (e.g., "T", "D", "E"), its completion status, description, and other data.
     *
     * @param line The line to parse, formatted according to the application's data conventions.
     * @return A {@code Task} object reconstructed from the raw data in the line.
     * @throws BuddyException If the line's format is invalid or cannot be parsed into a valid {@code Task}.
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
