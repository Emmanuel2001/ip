package buddytalk;

import java.io.IOException;

import buddytalk.commands.Command;
import buddytalk.exceptions.BuddyException;
import buddytalk.parser.Parser;
import buddytalk.storage.Storage;
import buddytalk.tasks.TaskList;
import buddytalk.ui.Ui;

/**
 * Represents the main BuddyTalk application. This class handles user input,
 * processes commands, and manages interactions between the task list, and storage.
 */
public class BuddyTalk {
    private TaskList tasks;
    private Storage storage;
    private Ui ui;

    /**
     * Initializes the BuddyTalk application. Attempts to load tasks from the file
     * specified in the {@code filePath}. If the file doesn't exist or is corrupted,
     * starts with an empty task list.
     */
    public BuddyTalk(String filePath) {
        storage = new Storage(filePath);
        ui = new Ui();
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (BuddyException | IOException e) {
            ui.displayError("The data file is corrupted. Starting with an empty task list.");
            tasks = new TaskList();
        }
    }

    /**
     * Starts the main application loop. Continuously waits for user input, parses commands,
     * and performs the corresponding actions until the "bye" command is issued.
     */
    public String run(String input) {
        try {
            Command command = Parser.parse(input);
            return command.execute(tasks, storage, ui);

        } catch (BuddyException | IOException e) {
            return ui.displayError(e.getMessage());
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
