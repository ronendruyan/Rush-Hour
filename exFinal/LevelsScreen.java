package exFinal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class LevelsScreen extends Screen {

	public LevelsScreen(ScreenManager screenManager) {
		super();
		System.out.println("rulesScreen");
		JButton b1 = new JButton("1");
		JButton b2 = new JButton("2");
		JButton b3 = new JButton("3");
		JButton b4 = new JButton("4");
		JButton b5 = new JButton("5");
		b1.setBounds(100, 100, 100, 50);
		b2.setBounds(300, 100, 100, 50);
		b3.setBounds(100, 200, 100, 50);
		b4.setBounds(300, 200, 100, 50);
		b5.setBounds(200, 300, 100, 50);
		b1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("button pressed");
				screenManager.toMenu();
			}
		});
		_components.add(b1);
		_components.add(b2);
		_components.add(b3);
		_components.add(b4);
		_components.add(b5);
	}
}
