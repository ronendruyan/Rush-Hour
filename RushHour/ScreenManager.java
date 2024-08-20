package RushHour;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

//The ScreenManager class is responsible for managing different screens within the application.
public class ScreenManager {
	private Dimension _screenSize; // The size of the screen
	private JFrame _frame; // The main window frame
	private JPanel _panel; // The panel that holds the current screen's components
	private Screen _activeScreen; // The currently active screen
	CreateLevels _level; // Reference to the level creation class

	// Constructor initializes the screen manager with the specified screen size
	public ScreenManager(Dimension screenSize) {
		_screenSize = screenSize;
		_frame = new JFrame();
		_panel = new JPanel();
		initScreen(); // Initializes the screen settings and displays the menu
	}

	// Initializes the main JFrame and JPanel
	private void initScreen() {
		_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		_frame.setSize(_screenSize);
		_frame.setResizable(false);
		_frame.getContentPane().add(_panel);
		_panel.setLayout(null); // Use absolute positioning
		_panel.setPreferredSize(new Dimension(_screenSize));
		_frame.add(_panel);
		_frame.setVisible(true);

		toMenu(); // Displays the menu screen by default
	}

	// Clears all components from the current screen
	public void clearScreen() {
		_panel.removeAll();
	}

	// Transitions to the menu screen
	public void toMenu() {
		clearScreen();
		_frame.setTitle("Menu Screen");
		_activeScreen = new MenuScreen(this);
		_activeScreen.DrawScreen(this);
	}

	// Transitions to the rules screen
	public void toRules() {
		clearScreen();
		_frame.setTitle("Rules Screen");
		_activeScreen = new RulesScreen(this);
		_activeScreen.DrawScreen(this);
	}

	// Transitions to the levels screen
	public void toLevels() {
		clearScreen();
		_frame.setTitle("Levels Screen");
		_activeScreen = new LevelsScreen(this);
		_activeScreen.DrawScreen(this);
	}

	// Transitions to the game screen for the specified level
	public void toGame(int numOfLevel) {
		clearScreen();
		_frame.setTitle("Game Screen: level " + numOfLevel);
		_level = new CreateLevels("levels_data.txt", numOfLevel);
		_activeScreen = new GameScreen(this, _level.getGameBoard(), numOfLevel);
		_activeScreen.DrawScreen(this);
	}

	// Getter for the JFrame object
	public JFrame getFrame() {
		return this._frame;
	}

	// Getter for the JPanel object
	public JPanel getPanel() {
		return this._panel;
	}

	// Redraws the screen, useful after updating the screen's components
	public void redraw() {
		_frame.revalidate();
		_frame.repaint();
	}
}
