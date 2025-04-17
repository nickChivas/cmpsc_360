package code.truthtablebuilder;

import java.io.IOException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class PrimaryController {

    @FXML
    private TextField inputField;
    @FXML
    private TextArea outputArea;
    @FXML
    private VBox contentBox;
    @FXML
    private ScrollPane scrollPane;

    @FXML
    public void handleTextInput(ActionEvent event) {
        String userInput = inputField.getText();
        System.out.println("User input: " + userInput);
        String[] inputToArray = userInput.split(" ");
        String command = inputToArray[0].toUpperCase();

        outputArea = new TextArea();
        String outputText;
        if (command.equals("HELP")) {
            outputText = "1) Enter \"HELP\" before any of the commands below to "
                    + "get more information\n2) Define your variables with "
                    + "\"DEFINE\"\n3) Enter a predicate with \"EVALUATE\"\n4) "
                    + "Reset your truth table with \"RESET\"\n5) Save your truth"
                    + "table with \"SAVE\"\n6) Load a previous truth table with \"LOAD\"\n";
        } else {
            outputText = "ERROR: Please enter a valid command.";
        }
        
        outputArea.setText(outputText);
        outputArea.setEditable(false);
        Text text = new Text(outputText);
        text.setFont(outputArea.getFont());
        text.setWrappingWidth(outputArea.getPrefWidth());
        outputArea.setPrefHeight(text.getBoundsInLocal().getHeight());
        outputArea.setPrefWidth(1150.0);
        outputArea.setMaxWidth(Double.NEGATIVE_INFINITY);
        outputArea.setMinWidth(Double.NEGATIVE_INFINITY);

        VBox.setMargin(outputArea, new Insets(10.0, 0.0, 10.0, 10.0));

        contentBox.getChildren().add(outputArea);

        TextField newInputField = new TextField();
        newInputField.setPrefHeight(40.0);
        newInputField.setPrefWidth(1150.0);
        newInputField.setMaxHeight(Double.NEGATIVE_INFINITY);
        newInputField.setMaxHeight(Double.NEGATIVE_INFINITY);
        newInputField.setMaxWidth(Double.NEGATIVE_INFINITY);
        newInputField.setMinWidth(Double.NEGATIVE_INFINITY);

        VBox.setMargin(newInputField, new Insets(10.0, 0.0, 10.0, 10.0));

        contentBox.getChildren().add(newInputField);

        inputField = newInputField;

        inputField.setOnAction(this::handleTextInput);

        inputField.requestFocus();

        Platform.runLater(() -> {
            scrollPane.layout(); // Ensure layout recalculation
            scrollPane.setVvalue(1.0); // Scroll to the bottom
        });
    }
}
