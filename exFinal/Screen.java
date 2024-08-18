package exFinal;

import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JPanel;

//abstract class to draw screens
//TODO make a an interface class(maybe for the movement)
abstract public class Screen {
	ArrayList<Component> _components;
	private ButtonComp BG;
	public Screen() {
		_components = new ArrayList<Component>();
		BG = new ButtonComp("background");
		BG.setBounds(0, 0);

	}

	public void DrawScreen(ScreenManager screenManager) {
		JPanel panel = screenManager.getPanel();
		for (Component comp : _components) {
			panel.add(comp);
		}
		panel.add(BG.getButton());
		screenManager.redraw();
		
	}

}
