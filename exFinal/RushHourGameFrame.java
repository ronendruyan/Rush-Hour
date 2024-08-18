package exFinal;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

//class to draw the gameFrame
//TODO pipeline make diffrent levels
//TODO move validate moves to backend by translating a grid to the screen
//
public class RushHourGameFrame extends Screen {
	private int gridSize = 80; // Size of each cell in the grid
	private int gridXOffset = 160,gridYOffset=2; // Size of each cell in the grid	
	private ScreenManager screenManager;
	private static int[] optimal = { 0, 8, 25, 22, 28, 48 };
	private int level;

	public RushHourGameFrame(ScreenManager screenManager, testGameBoard gameBoard, int level) {
		super();

		ImageIcon bg = new ImageIcon("textures/board_game.png");
		JButton boardPanel = new JButton(bg);
		boardPanel.setContentAreaFilled(false);
		boardPanel.setBorderPainted(false);
		boardPanel.setFocusPainted(false);
		boardPanel.setBounds(160, 0, boardPanel.getIcon().getIconWidth(), boardPanel.getIcon().getIconHeight());
		gameBoard.setFrame(this);
		this.screenManager = screenManager;
		this.level = level;
		// restart button
		ButtonComp resetButton = new ButtonComp("reset_button");
		resetButton.setBounds(850, 50);
		resetButton.setAction(new Action("toGame", gameBoard.numOfLevel), screenManager);
		_components.add(resetButton.getButton());
		// menu button
		ButtonComp menuButton = new ButtonComp("menu_button");
		menuButton.setBounds(20, 50);
		menuButton.setAction(new Action("toMenu", 0), screenManager);
		_components.add(menuButton.getButton());

		// TODO add level init level change path to car.path
		for (testCar car : gameBoard.getCars()) {
			// System.out.println("1");
			_components.add(new CarComp(gridSize,gridXOffset,gridYOffset, gameBoard, car).getButton());
		}
		_components.add(boardPanel);
	}

	private static int calculateScore(int userMoves, int optimalMoves) {
		// If user finishes in optimal moves, give maximum score (100)
		if (userMoves == optimalMoves) {
			return 100;
		}

		// Calculate the ratio of user moves to optimal moves
		double ratio = (double) userMoves / optimalMoves;

		// If the user uses fewer moves than optimal (unlikely, but for safety)
		if (ratio < 1.0) {
			return 100;
		}

		// Linear scaling: score decreases as the ratio increases
		// score = 100 - 90 * (ratio - 1)
		double score = 100 - 90 * (ratio - 1);

		// Round the score to the nearest whole number
		int roundedScore = (int) Math.round(score);

		// Ensure the score is within the range [0, 100]
		return Math.min(100, Math.max(0, roundedScore));
	}



	void displayWinningMessage(int userCount) {
		JFrame winningFrame = new JFrame(level != 5 ? "Level Completed" : "Game Over");
		winningFrame.setSize(400, 300);
		winningFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		winningFrame.setLocationRelativeTo(null);
		winningFrame.setLayout(null); // Using null layout for absolute positioning

		String message = level != 5 ? "Good Job - Level Completed" : "Game Finished - You Won!";
		JLabel messageLabel = new JLabel(message, SwingConstants.CENTER);
		messageLabel.setFont(new Font("Arial", Font.BOLD, 16));
		messageLabel.setBounds(50, 20, 300, 30); // Positioning the message label
		winningFrame.add(messageLabel);

		// Action listener to close the frame and perform the button's action
		ActionListener closeFrameAction = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				winningFrame.dispose();
			}
		};

		// Adding the restart button
		ButtonComp restartButton = new ButtonComp("reset_button");
		restartButton.setBounds(5, 100); // Centered and regular size
		restartButton.setAction(new Action("toGame", level), screenManager);
		restartButton.getButton().addActionListener(closeFrameAction);
		winningFrame.add(restartButton.getButton());

		ButtonComp menuButton = new ButtonComp("menu_button");
		menuButton.setBounds(130, 100); // Centered and regular size, below restart button
		menuButton.setAction(new Action("toMenu", 0), screenManager);
		menuButton.getButton().addActionListener(closeFrameAction);
		winningFrame.add(menuButton.getButton());

		if (level != 5) {
			ButtonComp nextLevelButton = new ButtonComp("next_button");
			nextLevelButton.setBounds(255, 100); // Centered and regular size, below other buttons
			nextLevelButton.setAction(new Action("toGame", level + 1), screenManager);
			nextLevelButton.getButton().addActionListener(closeFrameAction);
			winningFrame.add(nextLevelButton.getButton());
		}

		// Calculate and display the final score
		int score = calculateScore(userCount, optimal[level]);
		JLabel scoreLabel = new JLabel("Final Score: " + score, SwingConstants.CENTER);
		scoreLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		scoreLabel.setBounds(50, 60, 300, 30); // Positioning the score label
		winningFrame.add(scoreLabel);
		winningFrame.setVisible(true);
	}

}
