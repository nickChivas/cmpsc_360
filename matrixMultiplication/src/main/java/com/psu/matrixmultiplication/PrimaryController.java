package com.psu.matrixmultiplication;

import program.MatrixMultiplication;
import exceptions.*;
import java.io.IOException;

import java.util.Arrays;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * Class for handling all events in the main screen of the app.
 *
 * @author nickcubuntu
 */
public class PrimaryController {

    @FXML
    private TextArea matrix1Text;
    @FXML
    private TextArea matrix2Text;
    private int[][] matrix1Nums;
    private int[][] matrix2Nums;
    private static final int MIN_SIZE = 2;
    private static final int MAX_SIZE = 5;

    @FXML
    public void initialize() {
        // Add input filters to the text areas
        addInputFilter(matrix1Text);
        addInputFilter(matrix2Text);
    }

    @FXML
    public void handleResetButton() {
        matrix1Text.setText("");
        matrix2Text.setText("");
    }

    @FXML
    public void handleStartButton(ActionEvent event) throws IOException {
        try {
            matrix1Nums = parseMatrix(matrix1Text.getText());
            matrix2Nums = parseMatrix(matrix2Text.getText());

            if (matrix1Nums[0].length != matrix2Nums.length) {
                throw new UnequalDimensionsException("Number of columns in matrix1 must be equal to the number of rows in matrix2.");
            }

            // Multiply matrices using the utility class
            int[][] productMatrix = MatrixMultiplication.multiplyMatrices(matrix1Nums, matrix2Nums);

            // Print the product matrix
            System.out.println("Product Matrix: " + Arrays.deepToString(productMatrix));
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("animation.fxml"));
            Parent animationScene = loader.load();
            
            // Get the controller and pass the product matrix to it
            AnimationController animationController = loader.getController();
            animationController.setMatrices(matrix1Nums, matrix2Nums, productMatrix);
            
            Scene newScene = new Scene(animationScene, 960, 600);
            
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.setScene(newScene);
            currentStage.show();
        } catch (InvalidFormatException | UnequalRowLengthException | UnequalDimensionsException e) {
            // Handle exceptions (e.g., show an error message to the user)
            System.err.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            // Handle dimension mismatch during multiplication
            System.err.println("Matrix multiplication error: " + e.getMessage());
        }
    }

    private int[][] parseMatrix(String input) throws InvalidFormatException, UnequalRowLengthException {
        String[] lines = input.split("\n");

        if (lines.length > MAX_SIZE | lines.length < MIN_SIZE) {
            throw new UnequalRowLengthException("Matrix must be between 2x2 and 5x5.");
        }

        int[][] matrix = new int[lines.length][];

        for (int i = 0; i < lines.length; i++) {
            String[] row = lines[i].split(",");
            if (row.length > MAX_SIZE | row.length < MIN_SIZE) {
                throw new UnequalRowLengthException("Matrix must be between 2x2 and 5x5.");
            }

            // Check for consistent row length
            if (i > 0 && row.length != matrix[0].length) {
                throw new UnequalRowLengthException("All rows in the matrix must have the same number of items.");
            }

            // Initialize the row in the matrix
            matrix[i] = new int[row.length];

            for (int j = 0; j < row.length; j++) {
                try {
                    matrix[i][j] = Integer.parseInt(row[j]);
                } catch (NumberFormatException e) {
                    throw new InvalidFormatException("Input is not an integer or formatted incorrectly.");
                }
            }
        }

        return matrix;
    }

    private void addInputFilter(TextArea textArea) {
        textArea.addEventFilter(KeyEvent.KEY_TYPED, event -> {
            String character = event.getCharacter();
            if (!character.matches("[0-1,\n]")) {
                event.consume();
            }
        });
    }

}
