package code.binarymatrixmultiplication;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class MatrixInputController implements Initializable {

    @FXML
    GridPane matrixA;
    @FXML
    GridPane matrixB;
    @FXML
    GridPane productMatrix;

    @FXML
    Button resetButton;
    @FXML
    Button randomButton;
    @FXML
    Button startButton;
    @FXML
    Button TwoByTwoButton;
    @FXML
    Button ThreeByThreeButton;
    @FXML
    Button FourByFourButton;

    private int MATRIX_ROWS = 3;
    private int MATRIX_COLUMNS = 3;
    private static final double GRID_WIDTH = 240;
    private static final double GRID_HEIGHT = 380;
    private static final double H_GAP = 10;
    private static final double V_GAP = 5;

    private int[][] matrixA_Array;
    private int[][] matrixB_Array;

    @FXML
    public void handleResizeButtonClick(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        if (clickedButton.getText().equals("2 X 2")) {
            MATRIX_ROWS = 2;
            MATRIX_COLUMNS = 2;
        } else if (clickedButton.getText().equals("3 X 3")) {
            MATRIX_ROWS = 3;
            MATRIX_COLUMNS = 3;
        } else {
            MATRIX_ROWS = 4;
            MATRIX_COLUMNS = 4;
        }
        resizeMatrix(matrixA);
        resizeMatrix(matrixB);
    }

    private void resizeMatrix(GridPane matrix) {
        matrix.getChildren().clear();
        matrix.getColumnConstraints().clear();
        matrix.getRowConstraints().clear();

        // Calculate button size based on the grid size and gaps
        double maxButtonWidth = (GRID_WIDTH - (MATRIX_COLUMNS - 1) * H_GAP - 20) / MATRIX_COLUMNS;
        double maxButtonHeight = (GRID_HEIGHT - (MATRIX_ROWS - 1) * V_GAP - 20) / MATRIX_ROWS;
        double buttonSize = Math.min(maxButtonWidth, maxButtonHeight);

        for (int row = 0; row < MATRIX_ROWS; row++) {
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setPercentHeight(100.0 / MATRIX_ROWS);
            matrix.getRowConstraints().add(rowConstraints);

            for (int col = 0; col < MATRIX_COLUMNS; col++) {
                if (row == 0) {
                    ColumnConstraints colConstraints = new ColumnConstraints();
                    colConstraints.setPercentWidth(100.0 / MATRIX_COLUMNS);
                    matrix.getColumnConstraints().add(colConstraints);
                }

                Button button = new Button("0");
                button.setStyle("-fx-font-size: 24px;");
                button.setPrefSize(buttonSize, buttonSize);
                matrix.add(button, col, row);
            }
        }
    }

    @FXML
    public void handleMatrixButtonClick(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        if (clickedButton.getText().equals("0")) {
            clickedButton.setText("1");
        } else {
            clickedButton.setText("0");
        }
    }

    @FXML
    public void handleResetButtonClick(ActionEvent event) {
        matrixA.getChildren().forEach(node -> {
            if (node instanceof Button) {
                ((Button) node).setText("0");
            }
        });

        matrixB.getChildren().forEach(node -> {
            if (node instanceof Button) {
                ((Button) node).setText("0");
            }
        });
    }

    @FXML
    public void handleRandomButtonClick(ActionEvent event) {
        matrixA.getChildren().forEach(node -> {
            if (node instanceof Button) {
                Random random = new Random();
                int randomInt = random.nextInt(2);
                ((Button) node).setText(Integer.toString(randomInt));
            }
        });

        matrixB.getChildren().forEach(node -> {
            if (node instanceof Button) {
                Random random = new Random();
                int randomInt = random.nextInt(2);
                ((Button) node).setText(Integer.toString(randomInt));
            }
        });
    }

    @FXML
    public void handleStartButtonClick(ActionEvent event) throws IOException, InterruptedException {
        try {
            matrixA_Array = convertGridToArray(matrixA);
            matrixB_Array = convertGridToArray(matrixB);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("AnimationController.fxml"));
            Parent animationScene = loader.load();
            AnimationController animationController = loader.getController();
            animationController.setMATRIX_ROWS(MATRIX_ROWS);
            animationController.setMATRIX_COLUMNS(MATRIX_COLUMNS);
            animationController.displayMatrices(matrixA_Array, matrixB_Array);

            Scene newScene = new Scene(animationScene, 1280, 720);
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.setScene(newScene);
            currentStage.setResizable(false);
            currentStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int[][] convertGridToArray(GridPane grid) {
        int[] matrixCells = new int[MATRIX_ROWS * MATRIX_COLUMNS];
        int position = 0; // Initialize position outside the lambda
        for (Object child : grid.getChildren()) {
            if (child instanceof Button) {
                int cell = Integer.valueOf(((Button) child).getText());
                matrixCells[position] = cell;
                position++;
            }
        }
        int[][] matrix = new int[MATRIX_ROWS][MATRIX_COLUMNS];
        int currentRow = 0;
        int currentColumn = 0;
        for (int cell : matrixCells) {
            matrix[currentRow][currentColumn] = cell;
            if (currentColumn == MATRIX_COLUMNS - 1) {
                currentRow++;
                currentColumn = 0;
            } else {
                currentColumn++;
            }
        }
        return matrix;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        resizeMatrix(matrixA);
        resizeMatrix(matrixB);
    }
}
