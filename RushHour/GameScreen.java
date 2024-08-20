package RushHour;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

//The GameScreen class is responsible for displaying the game board and its components.
public class GameScreen extends Screen {
	private final int _GRID_SIZE = 80; // Size of each cell in the grid
	private final int _GRID_OFFSET_X = 160, _GRID_OFFSET_Y = 2; // Offset for the start of the grid on the screen
	private ScreenManager _screenManager; // Reference to the screen manager
	private int _level; // Current level of the game
	private static final int[] _optimal = { 0, 8, 25, 22, 28, 48 }; // Optimal move counts for levels

	// Constructor initializes the game screen with the given level and game board
	public GameScreen(ScreenManager screenManager, GameBoard gameBoard, int level) {
		super();
		System.out.println("Game Screen");
		ImageIcon gridTexture = new ImageIcon("textures/board_game.png");
		JButton gridDisplay = new JButton(gridTexture);
		gridDisplay.setContentAreaFilled(false);
		gridDisplay.setBorderPainted(false);
		gridDisplay.setFocusPainted(false);
		gridDisplay.setBounds(160, 0, gridDisplay.getIcon().getIconWidth(), gridDisplay.getIcon().getIconHeight());
		gameBoard.setFrame(this);
		this._screenManager = screenManager;
		this._level = level;

		// Add buttons to the screen
		ButtonComp resetButton = new ButtonComp("reset_button");
		resetButton.setBounds(850, 50);
		resetButton.setAction(new Action("toGame", gameBoard.getlevelNum()), screenManager);
		_components.add(resetButton.getButton());

		ButtonComp menuButton = new ButtonComp("menu_button");
		menuButton.setBounds(20, 50);
		menuButton.setAction(new Action("toMenu", 0), screenManager);
		_components.add(menuButton.getButton());
		
		//loop to add the cars to the screen based on gameBoard
		for (Car car : gameBoard.getCars()) {
			_components.add(new CarComp(_GRID_SIZE, _GRID_OFFSET_X, _GRID_OFFSET_Y, gameBoard, car).getButton());
		}
		_components.add(gridDisplay);
	}

	// Calculates the score based on the number of user moves and optimal moves
	private static int calculateScore(int userMoves, int optimalMoves) {
		if (userMoves == optimalMoves) {
			return 100;
		}
		double ratio = (double) userMoves / optimalMoves;
		double score = 100 - 90 * (ratio - 1);
		int roundedScore = (int) Math.round(score);
		return Math.min(100, Math.max(0, roundedScore));
	}

	// Displays the winning message with the score after completing a level
	void displayWinningMessage(int userCount) {
		JFrame winningFrame = new JFrame(_level != 5 ? "Level Completed" : "Game Over");
		winningFrame.setSize(400, 300);
		winningFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		winningFrame.setLocationRelativeTo(null);
		winningFrame.setLayout(null);

		String message = _level != 5 ? "Good Job - Level Completed" : "Game Finished - You Won!";
		JLabel messageLabel = new JLabel(message, SwingConstants.CENTER);
		messageLabel.setFont(new Font("Arial", Font.BOLD, 16));
		messageLabel.setBounds(50, 20, 300, 30);
		winningFrame.add(messageLabel);

		// Action listener to close the frame and perform the button's action
		ActionListener closeFrameAction = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				winningFrame.dispose();
			}
		};

		// Add buttons for restart, menu, and next level (if applicable)
		ButtonComp restartButton = new ButtonComp("reset_button");
		restartButton.setBounds(5, 100);
		restartButton.setAction(new Action("toGame", _level), _screenManager);
		restartButton.getButton().addActionListener(closeFrameAction);
		winningFrame.add(restartButton.getButton());

		ButtonComp menuButton = new ButtonComp("menu_button");
		menuButton.setBounds(130, 100);
		menuButton.setAction(new Action("toMenu", 0), _screenManager);
		menuButton.getButton().addActionListener(closeFrameAction);
		winningFrame.add(menuButton.getButton());

		if (_level != 5) {
			ButtonComp nextLevelButton = new ButtonComp("next_button");
			nextLevelButton.setBounds(255, 100);
			nextLevelButton.setAction(new Action("toGame", _level + 1), _screenManager);
			nextLevelButton.getButton().addActionListener(closeFrameAction);
			winningFrame.add(nextLevelButton.getButton());
		}

		// Display the final score
		int score = calculateScore(userCount, _optimal[_level]);
		JLabel scoreLabel = new JLabel("Final Score: " + score, SwingConstants.CENTER);
		scoreLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		scoreLabel.setBounds(50, 60, 300, 30);
		winningFrame.add(scoreLabel);
		winningFrame.setVisible(true);
	}
}
