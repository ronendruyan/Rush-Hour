package exFinal;

import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JPanel;

abstract public class Screen {
	ArrayList<Component> _components;
	public Screen() {
		_components = new ArrayList<Component>();

	}
	
	public void DrawScreen(ScreenManager screenManager) {
		JPanel panel = screenManager.getPanel();
		for (Component comp : _components) {
			panel.add(comp);
		}
		screenManager.redraw();
	}
	
}
