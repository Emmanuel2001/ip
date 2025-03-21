package buddytalk.gui;

import java.io.IOException;

import buddytalk.BuddyTalk;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private BuddyTalk buddyTalk;

    public Main() {
        this.buddyTalk = new BuddyTalk("data/BuddyTalk.txt");
    }

    @Override
    public void start(Stage stage) {
        try {
            stage.setTitle("BuddyTalk");
            stage.setMinHeight(417);
            stage.setMinWidth(417);
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setBuddyTalk(buddyTalk);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
