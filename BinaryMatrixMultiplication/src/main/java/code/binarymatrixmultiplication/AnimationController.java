package code.binarymatrixmultiplication;

import java.io.IOException;
import javafx.util.Duration;
import java.util.Arrays;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

/**
 * This class is responsible for controlling the animation of binary matrix
 * multiplication. It handles the resizing and display of matrices, as well as
 * the animation logic.
 *
 * @author nicho
 */
public class AnimationController {

    @FXML
    GridPane matrixA;
    @FXML
    GridPane matrixB;
    @FXML
    GridPane productMatrix;
    @FXML
    Button returnButton;
    @FXML
    GridPane gridOfSums;

    private static final double GRID_WIDTH = 240;
    private static final double GRID_HEIGHT = 380;
    private static final double H_GAP = 10;
    private static final double V_GAP = 5;

    private int MATRIX_ROWS;
    private int MATRIX_COLUMNS;
    private int[][] matrixA_Array;
    private int[][] matrixB_Array;
    private int[][] productMatrix_Array;

    /**
     * Navigates back to the main screen when the return button is clicked.
     *
     * @param event is the action event triggered by the return button.
     * @throws IOException if there is an error loading the FXML for the main
     * screen.
     */
    @FXML
    public void returnToMainScreen(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MatrixInput.fxml"));
        Parent animationScene = loader.load();
        MatrixInputController matrixInputController = loader.getController();

        Scene newScene = new Scene(animationScene, 1280, 720);
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.setScene(newScene);
        currentStage.setResizable(false);
        currentStage.show();
    }

    /**
     * Resizes the given matrix GridPane to fit the current number of rows and
     * columns.
     *
     * @param matrix The GridPane to resize.
     */
    private void resizeMatrix(GridPane matrix) {
        matrix.getChildren().clear();
        matrix.getColumnConstraints().clear();
        matrix.getRowConstraints().clear();
        resizeGridOfSums();

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

    /**
     * Resizes the grid used to display intermediate sums during the animation.
     */
    private void resizeGridOfSums() {
        gridOfSums.getChildren().clear();

        for (int i = 0; i < MATRIX_ROWS; i++) {
            Label thisLabel = new Label();
            gridOfSums.addColumn(i, thisLabel);
            GridPane.setMargin(thisLabel, new Insets(30));
        }
        gridOfSums.setPrefWidth(450);
        gridOfSums.setGridLinesVisible(true);
    }

    /**
     * Displays the given matrices and starts the animation process.
     *
     * @param matrixA_Array
     * @param matrixB_Array
     * @throws InterruptedException
     */
    public void displayMatrices(int[][] matrixA_Array, int[][] matrixB_Array) throws InterruptedException {
        resizeMatrix(matrixA);
        resizeMatrix(matrixB);
        resizeMatrix(productMatrix);
        displayArrayAsGrid(matrixA, matrixA_Array);
        setMatrixA_Array(matrixA_Array);
        displayArrayAsGrid(matrixB, matrixB_Array);
        setMatrixB_Array(matrixB_Array);
        setAllButtonsInvisible(productMatrix);
        displayArrayAsGrid(productMatrix, multiplyMatrices(matrixA_Array, matrixB_Array));
        startAnimation();
    }

    private void setAllButtonsInvisible(GridPane grid) {
        returnButton.setVisible(false);
        for (Object child : grid.getChildren()) {
            if (child instanceof Button) {
                ((Button) child).setVisible(false);
            }
        }
    }

    private void displayArrayAsGrid(GridPane grid, int[][] matrix) {
        int currentRow = 0;
        int currentColumn = 0;
        for (Object child : grid.getChildren()) {
            if (child instanceof Button) {
                String childAsString = Integer.toString(matrix[currentRow][currentColumn]);
                ((Button) child).setText(childAsString);
                if (currentColumn == MATRIX_COLUMNS - 1) {
                    currentRow++;
                    currentColumn = 0;
                } else {
                    currentColumn++;
                }
            }
        }
    }

    /**
     * Takes two matrices and returns their product.
     *
     * @param matrixA_Array
     * @param matrixB_Array
     * @return
     */
    public int[][] multiplyMatrices(int[][] matrixA_Array, int[][] matrixB_Array) {
        productMatrix_Array = new int[MATRIX_ROWS][MATRIX_COLUMNS];
        for (int column = 0; column < MATRIX_COLUMNS; column++) {
            for (int row = 0; row < MATRIX_ROWS; row++) {
                int i = 0;
                int sum = 0;
                for (int item = 0; item < MATRIX_COLUMNS; item++) {
                    int product = matrixA_Array[row][item] * matrixB_Array[item][column];
                    sum += product;
                    i++;
                }
                if (sum >= 1) {
                    productMatrix_Array[row][column] = 1;
                } else {
                    productMatrix_Array[row][column] = 0;
                }
            }
        }
        return productMatrix_Array;
    }

    /**
     * Handles all animation events.
     *
     * @throws InterruptedException
     */
    public void startAnimation() throws InterruptedException {
        Button[][] matrixA_Buttons = convertGridToArrayOfButtons(matrixA);
        Button[][] matrixB_Buttons = convertGridToArrayOfButtons(matrixB);
        Button[][] productMatrix_Buttons = convertGridToArrayOfButtons(productMatrix);
        Timeline animation = new Timeline();
        Duration startTime = Duration.seconds(1);
        Button currentButtonA = null;
        Button currentButtonB = null;
        Button currentButtonProduct;
        for (int column = 0; column < MATRIX_COLUMNS; column++) {
            for (int row = 0; row < MATRIX_ROWS; row++) {
                for (int item = 0; item < MATRIX_COLUMNS; item++) {

                    String defaultStyle = matrixA_Buttons[row][item].getStyle();
                    String updatedStyle = "-fx-base: #ff0000; -fx-font-size: 24px;";
                    currentButtonA = matrixA_Buttons[column][item];
                    currentButtonB = matrixB_Buttons[item][row];

                    Duration currentItemStartTime = startTime.add(Duration.seconds(item * 2));

                    KeyFrame highlightMatrixA = new KeyFrame(currentItemStartTime, new KeyValue(currentButtonA.styleProperty(), updatedStyle));
                    KeyFrame highlightMatrixB = new KeyFrame(currentItemStartTime, new KeyValue(currentButtonB.styleProperty(), updatedStyle));

                    KeyFrame resetMatrixA = new KeyFrame(currentItemStartTime.add(Duration.seconds(1)), new KeyValue(currentButtonA.styleProperty(), defaultStyle));
                    KeyFrame resetMatrixB = new KeyFrame(currentItemStartTime.add(Duration.seconds(1)), new KeyValue(currentButtonB.styleProperty(), defaultStyle));

                    animation.getKeyFrames().addAll(highlightMatrixA, highlightMatrixB, resetMatrixA, resetMatrixB);
                    // Update the label in the gridOfSums
                    int labelIndex = item;
                    if (labelIndex < gridOfSums.getChildren().size() && gridOfSums.getChildren().get(labelIndex) instanceof Label) {
                        gridOfSums.setGridLinesVisible(true);
                        Label thisLabel = (Label) gridOfSums.getChildren().get(labelIndex);
                        String stringToDisplay = currentButtonA.getText() + " x " + currentButtonB.getText() + " = " + quickBinaryMultiplication(currentButtonA.getText(), currentButtonB.getText());
                        System.out.println(stringToDisplay);
                        KeyFrame updateLabel = new KeyFrame(currentItemStartTime, new KeyValue(thisLabel.textProperty(), stringToDisplay));
                        animation.getKeyFrames().add(updateLabel);
                    }

                }
                Duration revealTime = startTime.add(Duration.seconds(MATRIX_COLUMNS * 2));
                currentButtonProduct = productMatrix_Buttons[column][row];
                KeyFrame revealSumOfProducts = new KeyFrame(revealTime, new KeyValue(currentButtonProduct.visibleProperty(), true));
                animation.getKeyFrames().add(revealSumOfProducts);
                for (Object child : gridOfSums.getChildren()) {
                    if (child instanceof Label) {
                        Label thisLabel = (Label) child;
                        KeyFrame removeLabel = new KeyFrame(revealTime, new KeyValue(thisLabel.textProperty(), ""));
                        animation.getKeyFrames().add(removeLabel);
                    }
                }

                Duration nextStartTime = revealTime.add(Duration.seconds(1));
                KeyFrame emptyKeyFrame = new KeyFrame(nextStartTime);
                animation.getKeyFrames().add(emptyKeyFrame);

                startTime = nextStartTime;

            }
        }
        KeyFrame revealReturnButton = new KeyFrame(startTime, new KeyValue(returnButton.visibleProperty(), true));
        animation.getKeyFrames().add(revealReturnButton);
        animation.play();
    }

    /**
     * Converts any grid into an array of buttons.
     *
     * @param grid
     * @return
     */
    public Button[][] convertGridToArrayOfButtons(GridPane grid) {
        int currentRow = 0;
        int currentColumn = 0;
        Button[][] matrixButtons = new Button[MATRIX_ROWS][MATRIX_COLUMNS];
        for (Object child : grid.getChildren()) {
            if (child instanceof Button) {
                matrixButtons[currentRow][currentColumn] = (Button) child;
                if (currentColumn == MATRIX_COLUMNS - 1) {
                    currentRow++;
                    currentColumn = 0;
                } else {
                    currentColumn++;
                }
            }
        }
        return matrixButtons;
    }

    private int quickBinaryMultiplication(String item1, String item2) {
        if (Integer.valueOf(item1) * Integer.valueOf(item2) == 1) {
            return 1;
        }
        return 0;
    }

    /**
     * Sets the first matrix.
     *
     * @param matrixA
     */
    public void setMatrixA(GridPane matrixA) {
        this.matrixA = matrixA;
    }

    /**
     * Sets the second matrix.
     *
     * @param matrixB
     */
    public void setMatrixB(GridPane matrixB) {
        this.matrixB = matrixB;
    }

    /**
     * Sets the array in the first matrix.
     *
     * @param matrixA_Array
     */
    public void setMatrixA_Array(int[][] matrixA_Array) {
        this.matrixA_Array = matrixA_Array;
    }

    /**
     * Sets the array in the second matrix.
     *
     * @param matrixB_Array
     */
    public void setMatrixB_Array(int[][] matrixB_Array) {
        this.matrixB_Array = matrixB_Array;
    }

    /**
     * Sets the number of matrix rows.
     *
     * @param MATRIX_ROWS
     */
    public void setMATRIX_ROWS(int MATRIX_ROWS) {
        this.MATRIX_ROWS = MATRIX_ROWS;
    }

    /**
     * Sets the number of matrix columns.
     *
     * @param MATRIX_COLUMNS
     */
    public void setMATRIX_COLUMNS(int MATRIX_COLUMNS) {
        this.MATRIX_COLUMNS = MATRIX_COLUMNS;
    }
}
