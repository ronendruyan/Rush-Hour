package exFinal;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

//class to manage the different screens of the application
public class ScreenManager {
	Dimension _screenSize;
	JFrame _frame;
	JPanel _panel;
	Screen _activeScreen;
	CreateLevelsClass level;

	public ScreenManager(Dimension screenSize) {
		_screenSize = screenSize;
		_frame = new JFrame();
		_panel = new JPanel();
		initScreen();
	}

	private void initScreen() {
		_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		_frame.setSize(_screenSize);
		_frame.setResizable(false);
		_frame.getContentPane().add(_panel);
		_panel.setLayout(null); // Use absolute positioning
		_panel.setPreferredSize(new Dimension(_screenSize));
		_frame.add(_panel);
		_frame.setVisible(true);

		toMenu();
	}

	public void clearScreen() {
		_panel.removeAll();
		System.out.println("removed");
	}

	public void toMenu() {
		clearScreen();
		_frame.setTitle("Menu Screen");
		_activeScreen = new MenuScreen(this);
		_activeScreen.DrawScreen(this);
	}

	public void toRules() {
		clearScreen();
		_frame.setTitle("Rules Screen");
		_activeScreen = new RulesScreen(this);
		_activeScreen.DrawScreen(this);

	}

	public void toLevels() {
		clearScreen();
		_frame.setTitle("Levels Screen");
		_activeScreen = new LevelsScreen(this);
		_activeScreen.DrawScreen(this);
	}

	//TODO add param
	public void toGame(int numOfLevel) {
		clearScreen();
		_frame.setTitle("Game Screen");
		//TODO call levelCreate 
		level = new CreateLevelsClass("t.txt",numOfLevel);
		_activeScreen = new RushHourGameFrame(this, level.getGameBoard(),numOfLevel);
//		_activeScreen = new RushHourGameFrame(this, gameBoard());
		_activeScreen.DrawScreen(this);
	}

	public JFrame getFrame() {
		return this._frame;
	}

	public JPanel getPanel() {
		return this._panel;
	}

	public void redraw() {
		_frame.revalidate();
		_frame.repaint();
	}
}
