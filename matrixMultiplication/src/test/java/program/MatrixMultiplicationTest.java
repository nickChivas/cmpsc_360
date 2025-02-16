/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package program;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author nickcubuntu
 */
public class MatrixMultiplicationTest {
  
  public MatrixMultiplicationTest() {
  }
  
  @BeforeAll
  public static void setUpClass() {
  }
  
  @AfterAll
  public static void tearDownClass() {
  }
  
  @BeforeEach
  public void setUp() {
  }
  
  @AfterEach
  public void tearDown() {
  }


  /**
   * First regular test of matrixMultiplication() method.
   */
  @org.junit.jupiter.api.Test
  void testValidMultiplication1() {
    int[][] matrix1 = {
      {1, 0, 0},
      {0, 1, 0},
      {0, 0, 1}
    };
    int[][] matrix2 = {
      {0, 0, 1},
      {0, 1, 0},
      {1, 1, 0}
    };
    int[][] expected = {
      {0, 0, 1},
      {0, 1, 1},
      {1, 0, 0}
    };
    assertArrayEquals(expected, MatrixMultiplication.multiplyMatrices(matrix1, matrix2));
  }

  /**
   * Second regular test of matrixMultiplication() method.
   */
  @org.junit.jupiter.api.Test
  void testValidMultiplication2() {
    int[][] matrix1 = {
      {0, 1, 0},
      {0, 0, 1},
      {1, 0, 0}
    };
    int[][] matrix2 = {
      {0, 0, 1},
      {1, 0, 0},
      {0, 1, 0}
    };
    int[][] expected = {
      {1, 0, 0},
      {0, 1, 0},
      {0, 0, 1}
    };
    assertArrayEquals(expected, MatrixMultiplication.multiplyMatrices(matrix1, matrix2));
  }
  
    /**
   * Third regular test of matrixMultiplication() method.
   */
  @org.junit.jupiter.api.Test
  void testValidMultiplication3() {
    int[][] matrix1 = {
      {0, 1, 0},
      {0, 0, 1},
      {1, 0, 0}
    };
    int[][] matrix2 = {
      {0, 0, 1},
      {1, 0, 0}
    };
    int[][] expected = {
      {1, 0},
      {0, 1},
      {0, 0}
    };
    assertArrayEquals(expected, MatrixMultiplication.multiplyMatrices(matrix1, matrix2));
  }

  /**
   * Test of exception handling in matrixMultiplication() method with valid
   * dimensions.
   */
  @org.junit.jupiter.api.Test
  void testInvalidDimensions() {
    int[][] matrix1 = {
      {0, 1},
      {1, 1}
    };
    int[][] matrix2 = {
      {0, 0, 1},
      {1, 0, 0},
      {0, 1, 0}
    };
    assertThrows(IllegalArgumentException.class, () -> {
      MatrixMultiplication.multiplyMatrices(matrix1, matrix2);
    });
  }
  
}
