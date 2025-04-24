package code.visualcountingproblems;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class PrimaryController {

    @FXML
    private Label question;
    @FXML
    private Button optionA;
    @FXML
    private Button optionB;

    @FXML
    void handleOptionAClick(ActionEvent event) throws IOException, InterruptedException {
        switch (optionA.getText()) {
            case "SET":
                question.setText("Does the set contain repeated elements or not?");
                optionA.setText("REPEATED");
                optionB.setText("NOT REPEATED");
                break;
            case "REPETITION":
                {
                    // Call animation for permutation with repetition.
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("permutationRepetition.fxml"));
                    Parent animationScene = loader.load();
                    PermutationRepetitionController permutationRepititionController = loader.getController();
                    Scene newScene = new Scene(animationScene, 1280, 720);
                    Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    currentStage.setScene(newScene);
                    currentStage.setResizable(false);
                    currentStage.show();
                    break;
                }
            case "REPEATED":
                {
                    // Call animation for counting multisets.
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("multiset.fxml"));
                    Parent animationScene = loader.load();
                    MultisetController multisetController = loader.getController();
                    Scene newScene = new Scene(animationScene, 1280, 720);
                    Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    currentStage.setScene(newScene);
                    currentStage.setResizable(false);
                    currentStage.show();
                    multisetController.startAnimation();
                    break;
                }
            case "A LOT TO COUNT":
                {
                    // Call animation for count by complement.
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("complement.fxml"));
                    Parent animationScene = loader.load();
                    ComplementController complementController = loader.getController();
                    Scene newScene = new Scene(animationScene, 1280, 720);
                    Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    currentStage.setScene(newScene);
                    currentStage.setResizable(false);
                    currentStage.show();
                    break;
                }
            default:
                break;
        }
    }

    @FXML
    void handleOptionBClick(ActionEvent event) throws IOException {
        switch (optionB.getText()) {
            case "SEQUENCE":
                question.setText("Can you repeat objects in the sequence or not?");
                optionA.setText("REPETITION");
                optionB.setText("NO REPETITION");
                break;
            case "NOT REPEATED":
                question.setText("Is there a lot to count (\"at least...\") or not?");
                optionA.setText("A LOT TO COUNT");
                optionB.setText("NOT A LOT");
                break;
            case "NO REPETITION":
                {
                    // Call animation for permutation without repetition.
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("permutation.fxml"));
                    Parent animationScene = loader.load();
                    PermutationController permutationController = loader.getController();
                    Scene newScene = new Scene(animationScene, 1280, 720);
                    Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    currentStage.setScene(newScene);
                    currentStage.setResizable(false);
                    currentStage.show();
                    break;
                }
            case "NOT A LOT":
                {
                    // Call animation for normal counting.
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("counting.fxml"));
                    Parent animationScene = loader.load();
                    CountingController countingController = loader.getController();
                    Scene newScene = new Scene(animationScene, 1280, 720);
                    Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    currentStage.setScene(newScene);
                    currentStage.setResizable(false);
                    currentStage.show();
                    break;
                }
            default:
                break;
        }
    }
}
