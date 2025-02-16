package program;

import java.util.Arrays;

/**
 * This is the main class which will run the Matrix Multiplication software.
 *
 * @author nickcubuntu
 */
public class MatrixMultiplication {
  /**
   * Return the product of two matrices.
   *
   * @param matrix1
   * @param matrix2
   * @return
   */
  public static int[][] multiplyMatrices(int[][] matrix1, int[][] matrix2) {
    // Create a new matrix to return.
    int[][] productMatrix = new int[matrix1[0].length][matrix2.length];

    // Do not multiply if the number of columns in the first matrix is not equal
    // to the number of rows in the second matrix.
    if (matrix1[1].length != matrix2[0].length) {
      throw new IllegalArgumentException("Invalid dimensions.");
    }

    // Iterate once per column.
    for (int column = 0; column < productMatrix[0].length; column++) {
      // For every row...
      for (int row = 0; row < productMatrix[0].length; row++) {
        // ...iterate {length} times per row.
        int i = 0;
        // Create an array for all the products.
        int[] thisRow = new int[productMatrix.length];
        for (int row2 = 0; row2 < productMatrix.length; row2++) {
          // Take the product and enter it in the array.
          thisRow[i] = matrix1[row2][column] * matrix2[row][row2];
          // Repeat for all entries in the row.
          i++;
        }
        // Sum the values of all of the products.
        int sum = 0;
        for (int num = 0; num < thisRow.length; num++) {
          sum += thisRow[num];
        }
        // If the array has sum >= 1, then the value of the cell is 1.
        if (sum >= 1) {
          productMatrix[column][row] = 1;
        } else {
          productMatrix[column][row] = 0;
        }
      }
    }
    return productMatrix;
  }
}

