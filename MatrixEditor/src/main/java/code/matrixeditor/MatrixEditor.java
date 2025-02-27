package code.matrixeditor;

import javax.swing.*;
import java.awt.*;

public class MatrixEditor extends JFrame {
    private JPanel gridPanel;
    
    public MatrixEditor() {
        // Set up the frame
        setTitle("Matrix Editor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Create the panel that will hold our matrix
        gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(3, 3, 5, 5));  // 3x3 grid with 5-pixel gaps
        
        // Add some initial text fields to the grid
        for (int i = 0; i < 9; i++) {
            JTextField cell = new JTextField();
            gridPanel.add(cell);
        }
        
        // Add the grid panel to the frame
        add(gridPanel);
        
        // Size and display the frame
        pack();
        setLocationRelativeTo(null);  // Center on screen
        setVisible(true);
    }
    
    public static void main(String[] args) {
        // Create and show the GUI on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> new MatrixEditor());
    }
}