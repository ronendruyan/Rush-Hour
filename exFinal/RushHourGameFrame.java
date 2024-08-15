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

		
		ImageIcon bg = new ImageIcon("textures/board_game.png");
		JButton boardPanel = new JButton(bg);
		boardPanel.setContentAreaFilled(false);
		boardPanel.setBorderPainted(false);
		boardPanel.setFocusPainted(false);
		boardPanel.setBounds(0, 0, boardPanel.getIcon().getIconWidth(), boardPanel.getIcon().getIconHeight());
		this.gameBoard = gameBoard;
		
		//restart button
		ButtonComp resetButton = new ButtonComp("Restart");
		resetButton.setBounds(700, 100, 100, 50);
		resetButton.setAction(new Action("toGame",1), screenManager);
		_components.add(resetButton.getButton());
		//menu button
		ButtonComp menuButton = new ButtonComp("Menu");
		menuButton.setBounds(700, 200, 100, 50);
		menuButton.setAction(new Action("toMenu",0), screenManager);
		_components.add(menuButton.getButton());
		
	
		
		// TODO add level init level change path to car.path
		for (testCar car : gameBoard.getCars()) {
			//System.out.println("1");
			_components.add(new CarComp(gridSize, gameBoard, car).getButton());
		}
		_components.add(boardPanel);
	}

		static void displayWinningMessage() {
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
