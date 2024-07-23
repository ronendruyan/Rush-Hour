package exFinal;

// Importing AWT library for graphics
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;

// Importing Swing library for GUI components
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class RushHourGameFrame extends Screen {
    private CarComp[] cars; // Array to hold car buttons
    private int gridSize = 100; // Size of each cell in the grid
    private JButton[][] cells; // 2D array to hold grid cells

    public RushHourGameFrame(ScreenManager screenManager) {
        super();
        
    	//layeredPane = new JLayeredPane();
                // Create the 7x7 board with cells
        JPanel boardPanel = new JPanel(new GridLayout(7, 7)); // Initialize boardPanel with GridLayout
        boardPanel.setBounds(0, 0, 700, 700); // Set bounds for boardPanel
        cells = new JButton[7][7]; // Initialize cells array
        for (int i = 0; i < 7; i++) { // Loop through rows
            for (int j = 0; j < 7; j++) { // Loop through columns
                cells[i][j] = new JButton(); // Create a new JButton for each cell
                if (i == 3 && j == 6) {
                    cells[i][j].setBackground(Color.GREEN); // Set background color for the exit cell
                } else {
                    cells[i][j].setEnabled(false); // Disable non-exit cells
                }
                boardPanel.add(cells[i][j]); // Add cell to boardPanel
            }
        }
        

        // Initialize cars
        cars = new CarComp[3]; // Initialize cars array

        // Red car (1x2) starting at (3,0)
        cars[0] = new CarComp("car", Color.RED, 0, 300, 200, 100,gridSize,this); // Create red car button

        // Blue car (3x1) starting vertically from (3,2) to (3,5)
        cars[1] = new CarComp("car", Color.BLUE, 200, 300, 100, 300,gridSize,this); // Create blue car button

        // Another car (1x3) starting horizontally from (2,3) to (5,3)
        cars[2] = new CarComp("Other Car", Color.ORANGE, 300, 200, 300, 100,gridSize,this); // Create orange car button

        for (CarComp car : cars) { // Loop through cars
            _components.add(car.getCar()); // Add car to layeredPane at drag layer
            System.out.println("cars added");
        }
        _components.add(boardPanel);
    }

    
    public boolean isValidMove(Rectangle bounds) {
    	for(CarComp car: cars) {
    		if((car.getLocation().intersects(bounds))){
    			return false;
    		}
    	}
    	return true;
    }
   
    public static void checkGameStatus(JButton redCar) {
        // Check if the red car is in the exit cell
         // Get the red car button
        if (redCar.getLocation().x == 600 && redCar.getLocation().y == 300) {
            displayWinningMessage(); // Display winning message if red car is in the exit cell
        }
    }

    private static void displayWinningMessage() {
        JFrame winningFrame = new JFrame("Game Over"); // Create a new JFrame for the winning message
        winningFrame.setSize(300, 200); // Set the size of the winning frame
        winningFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set default close operation
        winningFrame.setLocationRelativeTo(null); // Center the winning frame on the screen

        JLabel messageLabel = new JLabel("Game Finished - You Won!", SwingConstants.CENTER); // Create a new JLabel for the winning message
        messageLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Set the font of the message
        winningFrame.add(messageLabel); // Add the message label to the winning frame

        winningFrame.setVisible(true); // Make the winning frame visible
    }

}
