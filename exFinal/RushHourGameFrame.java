package exFinal;

// Importing AWT library for graphics
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
// Importing Swing library for GUI components
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

//class to draw the gameFrame
//TODO pipeline make diffrent levels
//TODO move validate moves to backend by translating a grid to the screen
//
public class RushHourGameFrame extends Screen {
	private CarComp[] cars; // Array to hold car buttons
	private int gridSize = 80; // Size of each cell in the grid
	private JButton[][] cells; // 2D array to hold grid cells
	testGameBoard gameBoard;

	public RushHourGameFrame(ScreenManager screenManager, testGameBoard gameBoard) {
		super();

		// layeredPane = new JLayeredPane();
		// Create the 7x7 board with cells
		/*JPanel boardPanel = new JPanel(new GridLayout(7, 7)); // Initialize boardPanel with GridLayout
		boardPanel.setBounds(0, 0, 7 * gridSize, 7 * gridSize); // Set bounds for boardPanel
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
		}*/
		ImageIcon bg = new ImageIcon("textures/boardGame2.png");
		JButton boardPanel = new JButton(bg);
		boardPanel.setContentAreaFilled(false);
		boardPanel.setBorderPainted(false);
		boardPanel.setFocusPainted(false);
		boardPanel.setBounds(0, 0, boardPanel.getIcon().getIconWidth(), boardPanel.getIcon().getIconHeight());
		this.gameBoard = gameBoard;

		// TODO add level init level change path to car.path
		for (testCar car : gameBoard.getCars()) {
			_components.add(new CarComp("path", null, gridSize, gameBoard, car).getButton());
		}

		// Initialize cars
		cars = new CarComp[8]; // Initialize cars array
		// Red car (1x2) starting at (3,0)
		testCar car0 = new testCar(0, 0, 3, false);
		cars[0] = new CarComp("green_car_large_right", Color.RED, gridSize, gameBoard, car0); // Create red car button
		gameBoard.addCar(car0);
		
		testCar car1 = new testCar(0, 1, 2, true);
		cars[1] = new CarComp("blue2_car_up", Color.RED, gridSize, gameBoard, car1); // Create red car button
		gameBoard.addCar(car1);
		// Blue car (3x1) starting vertically from (3,2) to (3,5)
		testCar car2 = new testCar(0, 4, 2, true);
		cars[2] = new CarComp("pink_car_down", Color.BLUE, gridSize, gameBoard, car2); // Create blue car button
		gameBoard.addCar(car2);

		// Another car (1x3) starting horizontally from (2,3) to (5,3)
		testCar car3 = new testCar(1, 3, 2, false);
		cars[3] = new CarComp("red_car", Color.ORANGE, gridSize, gameBoard, car3); // Create orange car button
		gameBoard.addCar(car3);

		testCar car4 = new testCar(3, 1, 2, true);
		cars[4] = new CarComp("blue_car_up", Color.ORANGE, gridSize, gameBoard, car4); // Create orange car button
		gameBoard.addCar(car4);
		

		testCar car5 = new testCar(2, 5, 3, false);
		cars[5] = new CarComp("green_car_large_right", Color.ORANGE, gridSize, gameBoard, car5); // Create orange car button
		gameBoard.addCar(car5);
		

		testCar car6 = new testCar(4, 4, 2, false);
		cars[6] = new CarComp("blue2_car_left", Color.ORANGE, gridSize, gameBoard, car6); // Create orange car button
		gameBoard.addCar(car6);
		
		testCar car7 = new testCar(4, 0, 3, true);
		cars[7] = new CarComp("green_car_large_down", Color.ORANGE, gridSize, gameBoard, car7); // Create orange car button
		gameBoard.addCar(car7);

		for (CarComp car : cars) { // Loop through cars
			_components.add(car.getCar()); // Add car to layeredPane at drag layer
			System.out.println("cars added");
		}
		_components.add(boardPanel);
	}

	private static void displayWinningMessage() {
		JFrame winningFrame = new JFrame("Game Over"); // Create a new JFrame for the winning message
		winningFrame.setSize(300, 200); // Set the size of the winning frame
		winningFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set default close operation
		winningFrame.setLocationRelativeTo(null); // Center the winning frame on the screen
		//TODO add return to menu
		JLabel messageLabel = new JLabel("Game Finished - You Won!", SwingConstants.CENTER); // Create a new JLabel for
																								// the winning message
		messageLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Set the font of the message
		winningFrame.add(messageLabel); // Add the message label to the winning frame

		winningFrame.setVisible(true); // Make the winning frame visible
	}

}
