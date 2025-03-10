package com.psu.matrixmultiplication;

import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

/**
 * FXML Animation class
 *
 * @author nicho
 */
public class AnimationController {

    @FXML
    private GridPane matrix1Grid;

    @FXML
    private GridPane matrix2Grid;

    @FXML
    private GridPane productMatrixGrid;

    private int[][] matrix1;
    private int[][] matrix2;
    private int[][] productMatrix;

    public void setMatrices(int[][] matrix1, int[][] matrix2, int[][] productMatrix) {
        this.matrix1 = matrix1;
        this.matrix2 = matrix2;
        this.productMatrix = productMatrix;
        displayMatrices();
        startAnimation();
    }

    private void displayMatrices() {
        displayMatrix(matrix1, matrix1Grid);
        displayMatrix(matrix2, matrix2Grid);
        displayMatrix(productMatrix, productMatrixGrid);
    }

    private void displayMatrix(int[][] matrix, GridPane grid) {
        grid.getChildren().clear();
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                Text text = new Text(String.valueOf(matrix[row][col]));
                grid.add(text, col, row);
            }
        }
    }

    private void startAnimation() {
        Timeline timeline = new Timeline();
        // Add KeyFrames for animation steps
        // Example: timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), e -> animateStep()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void animateStep() {
        // Implement animation steps for matrix multiplication
    }

}
