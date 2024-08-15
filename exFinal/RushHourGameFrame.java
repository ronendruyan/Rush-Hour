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
	private ScreenManager screenManager;
	testGameBoard gameBoard;
	//private Pair<int,int> [] optimal ;
	//1 =8 2- 25   3 -- 22   4 -28 5 : 48
	private static int[] optimal = {0,8,25,22,28,48};
	  int level;
	
	

	public RushHourGameFrame(ScreenManager screenManager, testGameBoard gameBoard, int level) {
		super();

		
		ImageIcon bg = new ImageIcon("textures/board_game.png");
		JButton boardPanel = new JButton(bg);
		boardPanel.setContentAreaFilled(false);
		boardPanel.setBorderPainted(false);
		boardPanel.setFocusPainted(false);
		boardPanel.setBounds(0, 0, boardPanel.getIcon().getIconWidth(), boardPanel.getIcon().getIconHeight());
		this.gameBoard = gameBoard;
		gameBoard.setFrame(this);
		this.screenManager = screenManager;
		this.level = level;
		//restart button
		ButtonComp resetButton = new ButtonComp("Restart");
		resetButton.setBounds(700, 100, 100, 50);
		resetButton.setAction(new Action("toGame",gameBoard.numOfLevel), screenManager);
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
	private static int calculateScore(int userMoves, int optimalMoves) {
	    // If user finishes in optimal moves, give maximum score
	    if (userMoves == optimalMoves) {
	        return 10;
	    }

	    // Calculate the ratio of user moves to optimal moves
	    double ratio = (double) userMoves / optimalMoves;

	    // Linear scaling: if the ratio is 1, score is 10, if ratio is 2, score might be 5, etc.
	    // To fit the score in the range [1, 10], we use the following formula:
	    // score = 10 - (ratio - 1) * 9
	    // This ensures that the score linearly decreases as the ratio increases.

	    int score = (int) Math.max(1, 10 - (ratio - 1) * 9);

	    // Ensuring the score does not exceed 10 or fall below 1
	    return Math.min(10, Math.max(1, score));
	}
		 void plus(){
			level++;
		}
	
		void displayWinningMessage(int userCount) {
			
		//static int tmp = level;
		if(level!=5)
		{
			JFrame winningFrame = new JFrame("Level Completed"); // Create a new JFrame for the winning message
			winningFrame.setSize(300, 200); // Set the size of the winning frame
			winningFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Set default close operation
			winningFrame.setLocationRelativeTo(null); // Center the winning frame on the screen
			//TODO add return to menu
			JLabel messageLabel = new JLabel("Good Job - Level Completed", SwingConstants.CENTER); // Create a new JLabel for
			ButtonComp nextLevelButton = new ButtonComp("Next Level");
			nextLevelButton.setBounds(50, 100, 75, 50);
			nextLevelButton.setAction(new Action("toGame",level+1), screenManager);
			winningFrame.add(nextLevelButton.getButton());
			ButtonComp menuButton = new ButtonComp("Menu");
			menuButton.setBounds(150, 100, 75, 50);
			menuButton.setAction(new Action("toMenu",0), screenManager);
			winningFrame.add(menuButton.getButton());
			
			// the winning message
			messageLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Set the font of the message
			winningFrame.add(messageLabel); // Add the message label to the winning frame
			winningFrame.setVisible(true); // Make the winning frame visible
			System.out.println("finish");
			System.out.println(calculateScore(userCount - gameBoard.cars.size(),optimal[level]));
			System.out.println(userCount);
			System.out.println("optimal: " + optimal[level]);
		}
			
		else
		{
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
}
