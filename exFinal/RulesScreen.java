package exFinal;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class RulesScreen extends Screen{
	
	public RulesScreen(ScreenManager screenManager) {
		super();
		System.out.println("rulesScreen");
		String text = new String("this is how you play");
		JLabel label = new JLabel(text, SwingConstants.CENTER);
		label.setFont(new Font("Serif", Font.PLAIN, 24));
		label.setBounds(100, 100, 300, 100);
		_components.add(label);
		
	}

}
