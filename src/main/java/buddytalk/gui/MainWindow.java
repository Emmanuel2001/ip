package buddytalk.gui;

import buddytalk.BuddyTalk;
import buddytalk.exceptions.BuddyException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for the main GUI of the BuddyTalk application.
 * This class manages user interactions and displays messages in the chat interface.
 * It handles both user inputs and responses from BuddyTalk.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private BuddyTalk buddyTalk;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image botImage = new Image(this.getClass().getResourceAsStream("/images/Bot.png"));

    /**
     * Initializes the main GUI components.
     * This method is automatically called after the FXML layout is loaded. It sets up
     * scroll pane behavior and displays an initial welcome message to the user.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        displayMessage();
    }

    /**
     * Sets the BuddyTalk instance for this GUI.
     *
     * @param b The {@link BuddyTalk} instance that handles the application's logic.
     */
    public void setBuddyTalk(BuddyTalk b) {
        buddyTalk = b;
    }

    /**
     * Handles user input from the text field and processes commands.
     * When the user enters input, the input is sent to the BuddyTalk logic, and the corresponding response
     * is retrieved and displayed in the dialog container. If the user types "bye", the application exits.
     *
     * @throws BuddyException If an error occurs during the processing of the user input.
     */
    @FXML
    private void handleUserInput() throws BuddyException {
        try {
            String input = userInput.getText();
            if (input == null || input.trim().isEmpty()) {
                dialogContainer.getChildren().add(
                        DialogBox.getBuddyTalkDialog("Input cannot be empty! \n"
                                + "Try 'help' for a list of command.", botImage)
                );
                return;
            }
            String response = buddyTalk.run(input);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getBuddyTalkDialog(response, botImage)
            );
            userInput.clear();
            if (input.equals("bye")) {
                Platform.exit();
                System.exit(0);
            }
        } catch (Exception e) {
            throw new BuddyException(e.getMessage());
        }
    }

    /**
     * Displays a welcome message in the dialog container.
     * This method is invoked during initialization to greet the user with a welcome message and
     * provide instructions for obtaining a list of commands ("help").
     */
    public void displayMessage() {
        String welcomeText = """
                Hello! I am BuddyTalk.
                How can I assist you today?
                For a list of commands, type 'help'.""";
        dialogContainer.getChildren().add(DialogBox.getBuddyTalkDialog(welcomeText, botImage));
    }
}
