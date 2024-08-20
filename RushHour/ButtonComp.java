package RushHour;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

//class to help drawing buttons
public class ButtonComp {
	JButton _button;

	// Constructor that creates a JButton with an image or text label
	public ButtonComp(String path) {
		// Load the image from the given path
		ImageIcon texture = new ImageIcon("textures/" + path + ".png");

		// Check if the image was loaded successfully
		if (texture.getIconWidth() == -1 || texture.getIconHeight() == -1) {
			// If the image wasn't found, use the text as the button label
			_button = new JButton(path);
		} else {
			// If the image was found, create a button with the image
			_button = new JButton(texture);

			// Make the button's background transparent
			_button.setContentAreaFilled(false);
			// Remove the border around the button
			_button.setBorderPainted(false);
			// Remove the focus border when the button is clicked
			_button.setFocusPainted(false);
		}
	}

	// Method to set the position and size of the button
	public void setBounds(int width, int height, int x, int y) {
		_button.setBounds(width, height, x, y);
	}

	// Method to set the position of the button using its icon's dimensions
	public void setBounds(int x, int y) {
		_button.setBounds(x, y, _button.getIcon().getIconWidth(), _button.getIcon().getIconHeight());
	}

	// Method to associate an action with the button
	public void setAction(Action action, Object param) {
		// Add an ActionListener that triggers the action when the button is clicked
		_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Perform the action with the provided parameter
				action.preformAction(param);
			}
		});
	}

	// Method to retrieve the JButton object
	public JButton getButton() {
		return _button;
	}
}