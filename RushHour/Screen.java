package RushHour;

import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JPanel;

//abstract class to draw screens
abstract public class Screen {
	ArrayList<Component> _components;// array to hold all components to be drawn in this screen
	private ButtonComp _BG;// component to draw the background

	// constructor
	public Screen() {
		_components = new ArrayList<Component>();
		// init background
		_BG = new ButtonComp("background");
		_BG.setBounds(0, 0);

	}

	// Method to draw the components to the given drawing interface
	public void DrawScreen(ScreenManager screenManager) {
		JPanel panel = screenManager.getPanel();
		for (Component comp : _components) {
			panel.add(comp);
		}
		panel.add(_BG.getButton());
		screenManager.redraw();

	}

}
