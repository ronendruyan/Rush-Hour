package exFinal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

//class to help drawing buttons
public class ButtonComp {
	JButton _button;

	public ButtonComp(String path) {
		ImageIcon texture = new ImageIcon("textures/" + path + ".png");
		if (texture.getIconWidth() == -1 || texture.getIconHeight() == -1) {
			// System.out.println("Error: "+path+".png not found.");
			_button = new JButton(path);
		} else {
			_button = new JButton(texture);
			_button.setContentAreaFilled(false);
			_button.setBorderPainted(false);
			_button.setFocusPainted(false);
		}

	}

	public void setBounds(int width, int height, int x, int y) {
		_button.setBounds(width, height, x, y);
	}

	public void setBounds(int x, int y) {
		_button.setBounds(x, y, _button.getIcon().getIconWidth(), _button.getIcon().getIconHeight());
	}

	// experimental
	public void setAction(Action action, Object param) {
		_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Action preformed");
				action.preformAction(param);
			}
		});
	}

	public JButton getButton() {
		return _button;
	}

}
