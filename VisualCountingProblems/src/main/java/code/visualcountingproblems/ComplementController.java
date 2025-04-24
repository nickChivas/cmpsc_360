package code.visualcountingproblems;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 *
 * @author nicho
 */
public class ComplementController {

    @FXML
    Button returnButton;

    @FXML
    void handleReturnButtonClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("primary.fxml"));
        Parent animationScene = loader.load();
        PrimaryController primaryController = loader.getController();
        Scene newScene = new Scene(animationScene, 1280, 720);
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.setScene(newScene);
        currentStage.setResizable(false);
        currentStage.show();
    }
}
