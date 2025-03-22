package buddytalk.commands;

import java.util.ArrayList;

import buddytalk.storage.Storage;
import buddytalk.tasks.Task;
import buddytalk.tasks.TaskList;
import buddytalk.ui.Ui;

/**
 * Represents a command to find tasks in the task list that match a specific keyword or phrase.
 */
public class Find extends Command {
    private String item;

    /**
     * Creates a Find command to search for tasks that match the given keyword or phrase.
     *
     * @param item The keyword or phrase to search for in the task list.
     */
    public Find(String item) {
        this.item = item;
    }

    /**
     * Executes the find command. Searches task list for tasks that match the specified
     * keyword or phrase and returns the results to the user.
     *
     * @param tasks   The {@code TaskList} containing the current tasks.
     * @param storage The {@code Storage} instance to manage task storage (unused in this command).
     * @param ui      The {@code Ui} instance used to display the results of the search.
     * @return A {@code String} containing the list of tasks that match the search keyword or phrase.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        ArrayList<Task> items = tasks.findTasks(item);
        return ui.showList(items);
    }
}
